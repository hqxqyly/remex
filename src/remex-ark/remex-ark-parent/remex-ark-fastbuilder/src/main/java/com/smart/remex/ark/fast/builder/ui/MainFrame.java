package com.smart.remex.ark.fast.builder.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.smart.remex.ark.fast.builder.dto.BuildParamDto;
import com.smart.remex.ark.fast.builder.service.BuildService;
import com.smart.remex.ark.fast.builder.utils.StringUtils;

/**
 * 主界面
 * 
 * @author Qiaoxin.Hong
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private BuildService buildService = new BuildService();
	
	private JPanel contentPane;
	private JTextField txtDomainName;
	private JTextField txtProjectName;
	private JTextField txtFilePath;
	private JTextField txtJdbcDriver;
	private JTextField txtJdbcUrl;
	private JTextField txtJdbcUsername;
	private JTextField txtJdbcPassword;
	private JTextField txtServiceName;
	
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 16));
		setTitle("RemexFast项目构建工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBuildSingle = new JButton("构建single");
		btnBuildSingle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					BuildParamDto dto = new BuildParamDto();
					fillCreate(dto);
					buildService.buildSingle(dto);
					
					JOptionPane.showMessageDialog(contentPane, "构建成功", "操作提示", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					String message = e.getMessage();
					JOptionPane.showMessageDialog(contentPane, message,	"操作提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBottom.add(btnBuildSingle);
		
		JButton btnBuildCloud = new JButton("构建cloud");
		btnBuildCloud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					BuildParamDto dto = new BuildParamDto();
					fillCreate(dto);
					buildService.buildCloud(dto);
					
					JOptionPane.showMessageDialog(contentPane, "构建成功", "操作提示", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					String message = e.getMessage();
					JOptionPane.showMessageDialog(contentPane, message,	"操作提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBottom.add(btnBuildCloud);
		
		JPanel panelContent = new JPanel();
		contentPane.add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblDomainName = new JLabel("域名：");
		panelContent.add(lblDomainName, "2, 2, right, default");
		
		txtDomainName = new JTextField();
		txtDomainName.setText("com.qyly");
		panelContent.add(txtDomainName, "4, 2, fill, default");
		txtDomainName.setColumns(10);
		
		JLabel lblProjectName = new JLabel("项目名：");
		panelContent.add(lblProjectName, "6, 2, right, default");
		
		txtProjectName = new JTextField();
		txtProjectName.setText("demo");
		panelContent.add(txtProjectName, "8, 2, fill, default");
		txtProjectName.setColumns(10);
		
		JLabel lblServiceName = new JLabel("服务名：");
		panelContent.add(lblServiceName, "2, 4, right, default");
		
		txtServiceName = new JTextField();
		txtServiceName.setText("demo");
		panelContent.add(txtServiceName, "4, 4, fill, default");
		txtServiceName.setColumns(10);
		
		JLabel lblFilePath = new JLabel("输出地址：");
		panelContent.add(lblFilePath, "2, 6, right, default");
		
		txtFilePath = new JTextField();
		txtFilePath.setText("D:\\remexFastBuilder");
		panelContent.add(txtFilePath, "4, 6, 5, 1, fill, default");
		txtFilePath.setColumns(10);
		
		JLabel lblJdbcDriver = new JLabel("jdbc驱动类");
		panelContent.add(lblJdbcDriver, "2, 14, right, default");
		
		txtJdbcDriver = new JTextField();
		txtJdbcDriver.setText("com.mysql.cj.jdbc.Driver");
		panelContent.add(txtJdbcDriver, "4, 14, fill, default");
		txtJdbcDriver.setColumns(10);
		
		JLabel lblJdbcUrl = new JLabel("jdbc url");
		panelContent.add(lblJdbcUrl, "6, 14, right, default");
		
		txtJdbcUrl = new JTextField();
		txtJdbcUrl.setText("jdbc:mysql://127.0.0.1:3306/demo");
		panelContent.add(txtJdbcUrl, "8, 14, fill, default");
		txtJdbcUrl.setColumns(10);
		
		JLabel lblJdbcUsername = new JLabel("jdbc用户名");
		panelContent.add(lblJdbcUsername, "2, 16, right, default");
		
		txtJdbcUsername = new JTextField();
		txtJdbcUsername.setText("root");
		panelContent.add(txtJdbcUsername, "4, 16, fill, default");
		txtJdbcUsername.setColumns(10);
		
		JLabel lblJdbcPassword = new JLabel("jdbc密码");
		panelContent.add(lblJdbcPassword, "6, 16, right, default");
		
		txtJdbcPassword = new JTextField();
		txtJdbcPassword.setText("123456");
		panelContent.add(txtJdbcPassword, "8, 16, fill, default");
		txtJdbcPassword.setColumns(10);
	}

	private void fillCreate(BuildParamDto dto) {
		dto.setDomainName(StringUtils.defaultString(txtDomainName.getText()));
		dto.setProjectName(StringUtils.defaultString(txtProjectName.getText()));
		dto.setServiceName(StringUtils.defaultString(txtServiceName.getText()));
		if (StringUtils.isNotBlank(dto.getServiceName())) {
			dto.setServiceClassName(dto.getServiceName().substring(0, 1).toUpperCase() + dto.getServiceName().substring(1));
		}
		
		dto.setFilePath(StringUtils.defaultString(txtFilePath.getText()));
		dto.setJdbcDriver(StringUtils.defaultString(txtJdbcDriver.getText()));
		dto.setJdbcUrl(StringUtils.defaultString(txtJdbcUrl.getText()));
		dto.setJdbcUsername(StringUtils.defaultString(txtJdbcUsername.getText()));
		dto.setJdbcPassword(StringUtils.defaultString(txtJdbcPassword.getText()));
	}
}
