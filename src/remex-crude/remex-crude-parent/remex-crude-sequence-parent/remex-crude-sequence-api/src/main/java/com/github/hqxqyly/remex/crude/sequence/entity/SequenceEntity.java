package com.github.hqxqyly.remex.crude.sequence.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.hqxqyly.remex.fast.api.structure.entity.AgileEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("序列entity")
@TableName("t_sequence")
public class SequenceEntity extends AgileEntity {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("序列类型")
    protected String seqType;
    
    @ApiModelProperty("序列类型")
    protected String seqName;
    
    @ApiModelProperty("序列前缀")
    protected String prefix;
    
    @ApiModelProperty("序列值")
    protected Long seq;
    
    @ApiModelProperty("递增值")
    protected Integer increase;
    
    @ApiModelProperty("序列数字长度")
    protected Integer seqNumLength;
    
    @ApiModelProperty("是否开启日期序列，0：否；1：是；")
    protected Integer isDateSeq;
    
    @ApiModelProperty("日期格式化格式")
    protected String dateFormatPattern;
    
    @ApiModelProperty("最后日期")
    protected Timestamp lastDate;

	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Integer getIncrease() {
		return increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}

	public Integer getSeqNumLength() {
		return seqNumLength;
	}

	public void setSeqNumLength(Integer seqNumLength) {
		this.seqNumLength = seqNumLength;
	}

	public Integer getIsDateSeq() {
		return isDateSeq;
	}

	public void setIsDateSeq(Integer isDateSeq) {
		this.isDateSeq = isDateSeq;
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public Timestamp getLastDate() {
		return lastDate;
	}

	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
}
