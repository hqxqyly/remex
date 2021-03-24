package com.github.hqxqyly.remex.boot.poi.client;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.hqxqyly.remex.boot.constant.BConst;
import com.github.hqxqyly.remex.boot.poi.constant.PoiType;
import com.github.hqxqyly.remex.boot.poi.constant.TableTheme;
import com.github.hqxqyly.remex.boot.poi.data.WriteDataFetchAll;
import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataCellHandle;
import com.github.hqxqyly.remex.boot.poi.interfaces.IWriteDataFetch;
import com.github.hqxqyly.remex.boot.poi.utils.ColParamFactory;
import com.github.hqxqyly.remex.boot.utils.Assist;

/**
 * excel导出处理器
 * 
 * @author Qiaoxin.Hong
 *
 * @param <T>
 */
public class ExcelExportClient<T> {
	
	/** 导出的输出流 */
    protected OutputStream outputStream;
	
	/** poi类型 */
    protected PoiType poiType = PoiType.XSSF;
    
    /** excel列标题集 */
    protected List<String> colNames;
    
    /** 默认列宽度 */
    protected int colDefaultWidth = 14;
    
    /** excel列宽度集，默认乘256 */
    protected List<Integer> colWidths;
    
    /** excel样式 */
    protected TableTheme tableTheme = TableTheme.STYLE_THREE;
    
    /** excel导出数据提取器 */
    protected IWriteDataFetch<T> writeDataFetch;
    
    /** excel导入列数据处理 */
    protected IWriteDataCellHandle<T> writeDataCellHandle;

	/**
	 * 写入
	 */
    public void write() {
    	Assist.notNull(outputStream, "outputStream cannot be null");
    	Assist.notEmpty(colNames, "colNames cannot be null");
    	Assist.notNull(poiType, "poiType cannot be null");
    	Assist.notNull(writeDataFetch, "writeDataFetch cannot be null");
    	Assist.notNull(tableTheme, "tableTheme cannot be null");
    	
    	try {
    		//创建workbook
    		Workbook workbook = createWorkbook();
    		//创建sheet
    		Sheet sheet = workbook.createSheet();
    		//默认列宽度
    		sheet.setDefaultColumnWidth(colDefaultWidth);
    		//自定义列宽度
    		Assist.forEachOrdered(colWidths, (columnIndex, width) -> setColumnWidth(sheet, columnIndex, width));
    		
    		//当前行下标的滚动标识
    		int rowRollIndex = 0;
    		//数据提取索引
    		int fetchIndex = 0;
    		//列数量
    		int colCount = getColRealCount();
    		
    		//列标题的相关处理
    		Row colTitleRow = sheet.createRow(rowRollIndex);
    		Assist.forEachOrdered(colNames, (index, colName) -> {
    			Cell colTitleCell = colTitleRow.createCell(index);
    			colTitleCell.setCellValue(colName);
    			colTitleCell.setCellStyle(tableTheme.getColTitleCellStyle(workbook));
    		});
    		tableTheme.cellRowSettings(colTitleRow);
    		rowRollIndex++; //行下标的滚动标识向前推一行
    		
    		while (true) {
    			//提取数据
    			List<T> dataList = writeDataFetch.fetch(fetchIndex, rowRollIndex);
    			
    			if (Assist.isNotEmpty(dataList)) {
    				//行循环
    				for (int i = 0; i < dataList.size(); i++) {
    					//创建行
    					Row row = sheet.createRow(rowRollIndex);
    					
    					//当前行数据
    					T curItem = dataList.get(i);
    					//当前列下标的滚动标识
    					int cellRollIndex = 0;
    					
    					//列循环
    					for (int j = 0; j < colCount; j++) {
    						//创建单元格
    						Cell cell = row.createCell(j);
    						
    						cell.setCellStyle(tableTheme.getCellStyle(workbook));
    						//获取到单元格数据
    						Object val = writeDataCellHandle == null ? curItem : writeDataCellHandle.handleCell(curItem, i, j);
    						
    						if (val == null) {
    							cell.setCellValue(BConst.EMPTY);
    						} else if (val instanceof Date) {
    							cell.setCellValue((Date) val);
    						} else if (val instanceof Number) {
    							Number number = (Number) val;
    							cell.setCellValue(number.doubleValue());
    						} else {
    							cell.setCellValue(val.toString());
    						}
    						
    						//列下标的滚动标识向前推一行
    						cellRollIndex++;
    					}
    					tableTheme.cellRowSettings(row);
    					
    					//行处理结束后置处理
    					writeDataCellHandle.rowOverAfter(curItem, i, cellRollIndex - 1);
    					
    					//行下标的滚动标识向前推一行
    					rowRollIndex++; 
    				}
    			}
    			
    			//是否结束提取
    			boolean isOverFetch = writeDataFetch.over(fetchIndex, dataList);
    			if (isOverFetch) break;
    			
    			//数据提取索引+1
    			fetchIndex++;
    		}
    		
    		//导出
    		workbook.write(outputStream);
		} catch (Exception e) {
			throw Assist.transferException("poi excel write error", e);
		}
	}
	
	/**
     * 根据poi类型创建Workbook
     *
     * @return
     */
    protected Workbook createWorkbook() {
        return PoiType.HSSF.equals(poiType) ? new HSSFWorkbook() : new XSSFWorkbook();
    }
    
    /**
     * 设置列宽度，width默认*256
     *
     * @param sheet
     * @param columnIndex
     * @param width
     */
    protected void setColumnWidth(Sheet sheet, Integer columnIndex, Integer width) {
        sheet.setColumnWidth(columnIndex, width * 256);
    }
    
    /**
     * 取得excel的实际列数量
     *
     * @return
     */
    public int getColRealCount() {
        return colNames.size();
    }
    
    /**
     * 设置数据列表
     * @param dataList
     * @return
     */
    public ExcelExportClient<T> setDataList(List<T> dataList) {
    	setWriteDataFetch(new WriteDataFetchAll<T>(dataList));
        return this;
    }
    
    /**
     * 设置列参数
     * @param colParamFactory
     * @return
     */
    public ExcelExportClient<T> setColParam(ColParamFactory colParamFactory) {
    	if (colParamFactory != null) {
    		setColNames(colParamFactory.getColNameList());
    		setColWidths(colParamFactory.getColWidthList());
    	}
    	return this;
    }
    
    public TableTheme getTableTheme() {
        return tableTheme;
    }

    public ExcelExportClient<T> setTableTheme(TableTheme tableTheme) {
    	if (tableTheme != null)
    		this.tableTheme = tableTheme;
        return this;
    }
    
    public ExcelExportClient<T> setColNames(List<String> colNames) {
        this.colNames = colNames;
        return this;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public ExcelExportClient<T> setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public PoiType getPoiType() {
        return poiType;
    }

    public ExcelExportClient<T> setPoiType(PoiType poiType) {
        this.poiType = poiType;
        return this;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public int getColDefaultWidth() {
        return colDefaultWidth;
    }

    public ExcelExportClient<T> setColDefaultWidth(int colDefaultWidth) {
        this.colDefaultWidth = colDefaultWidth;
        return this;
    }

    public List<Integer> getColWidths() {
        return colWidths;
    }

    public ExcelExportClient<T> setColWidths(List<Integer> colWidths) {
        this.colWidths = colWidths;
        return this;
    }

    public IWriteDataCellHandle<T> getWriteDataCellHandle() {
        return writeDataCellHandle;
    }

    public ExcelExportClient<T> setWriteDataCellHandle(IWriteDataCellHandle<T> writeDataCellHandle) {
        this.writeDataCellHandle = writeDataCellHandle;
        return this;
    }

	public IWriteDataFetch<T> getWriteDataFetch() {
		return writeDataFetch;
	}

	public ExcelExportClient<T> setWriteDataFetch(IWriteDataFetch<T> writeDataFetch) {
		this.writeDataFetch = writeDataFetch;
		return this;
	}
}
