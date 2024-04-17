package view;

import dao.OutboundDao;
import model.Outbound;
import util.DBTool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class DelGoodsMangerFrame extends JInternalFrame {
	private JTextField first_goodsDateText;
    private JTextField idText;
    private JTextField goodsNameText;
	private JTextField goodsCategoryText;
	private JTextField goodsDateText;
	private JTextField goodsNumberText;
	private JTable GoodsTable;
	private OutboundDao outboundDao;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelGoodsMangerFrame frame = new DelGoodsMangerFrame();
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
    public DelGoodsMangerFrame(){
        //改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
        setIconifiable(true);
		setClosable(true);
		setTitle("出货管理");
		setBounds(135, 80, 744, 528);

        JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(GroupLayout.Alignment.LEADING, groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addContainerGap())
		);
		/*
		表单操作面板
		 */
        JLabel label_id = new JLabel("货物编号：");
		idText = new JTextField();
		idText.setColumns(6);

		JLabel label_name = new JLabel("货物名称：");
		goodsNameText = new JTextField();
		goodsNameText.setColumns(10);

		JLabel label_category = new JLabel("货物规格：");
		goodsCategoryText = new JTextField();
		goodsCategoryText.setColumns(10);

		JLabel label_date = new JLabel("出库时间：");
		goodsDateText = new JTextField();
		goodsDateText.setColumns(10);

		JLabel label_number = new JLabel("货物数量：");
		goodsNumberText = new JTextField();
		goodsNumberText.setColumns(10);

		/*
		底部功能板块
		 */
		//修改按钮
		JButton modifyBtn = new JButton("修改");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyGoodsActionPerformed(e);
			}
		});
		modifyBtn.setIcon(new ImageIcon(DelGoodsMangerFrame.class.getResource("/images/modify.png")));
		//删除按钮
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteGoodsActionPerformed(e);
			}
		});
		deleteBtn.setIcon(new ImageIcon(DelGoodsMangerFrame.class.getResource("/images/delete.png")));
		/*
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(modifyBtn)
							.addGap(18)
							.addComponent(deleteBtn))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_name)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(goodsNameText))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_id)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(idText, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
							.addGap(26)
							.addComponent(label_number)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(goodsNumberText, 81, 81, 81)
							.addGap(26)
							.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_date)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(goodsDateText, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_category)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(goodsCategoryText, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label_id)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_date)
						.addComponent(goodsDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label_name)
						.addComponent(goodsNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_number)
						.addComponent(goodsNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_category)
						.addComponent(goodsCategoryText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(modifyBtn)
						.addComponent(deleteBtn)))
		);
		 */
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_name)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(goodsNameText, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_id)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(idText)))
							.addGap(24)
							.addComponent(label_number)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(goodsNumberText, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(label_date)
								.addComponent(label_category))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(goodsCategoryText)
								.addComponent(goodsDateText, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(96)
							.addComponent(modifyBtn)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
							.addComponent(deleteBtn)
							.addGap(96))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label_id)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(goodsDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_date))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label_name)
						.addComponent(goodsNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(goodsCategoryText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_category)
						.addComponent(goodsNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_number))
					.addGap(70)
					.addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(modifyBtn)
						.addComponent(deleteBtn))
					.addContainerGap())
				);

		panel_1.setLayout(gl_panel_1);

		//表格
		GoodsTable = new JTable();
		//表格鼠标按下事件
		GoodsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		GoodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"编号", "货物名称", "货物规格", "货物数量", "出库时间"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		GoodsTable.getColumnModel().getColumn(0).setPreferredWidth(56);
		GoodsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		GoodsTable.getColumnModel().getColumn(2).setPreferredWidth(63);
		GoodsTable.getColumnModel().getColumn(3).setPreferredWidth(63);
		GoodsTable.getColumnModel().getColumn(4).setPreferredWidth(61);
		scrollPane.setViewportView(GoodsTable);

		JLabel labelDate = new JLabel("出库时间：");
		first_goodsDateText = new JTextField();
		first_goodsDateText.addFocusListener(new DelGoodsMangerFrame.JTextFieldHintListener(first_goodsDateText, "格式：yyyy-mm-dd"));
		first_goodsDateText.setColumns(10);
		//图书查询按钮
		JButton s_searchBtn = new JButton("查询");
		s_searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		s_searchBtn.setIcon(new ImageIcon(DelGoodsMangerFrame.class.getResource("/images/search.png")));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(labelDate)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(first_goodsDateText, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(380)
					.addComponent(s_searchBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(labelDate)
						.addComponent(first_goodsDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_searchBtn))
					.addGap(16))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		//初始化表格显示，显示所有的书籍
		fillGoodsTable(new Outbound());
    }
	/**
	 * 修改事件
	 * @param evt
	 */
	private void modifyGoodsActionPerformed(ActionEvent evt) {

		String id=idText.getText();
		String goodsName=goodsNameText.getText();
		String goodsCategory=goodsCategoryText.getText();
		String goodsNumber=goodsNumberText.getText();
		String goodsDate=goodsDateText.getText();

		if(id==null || "".equals(id)){ //为空
			JOptionPane.showMessageDialog(null, "请选中要删除的行！");  //给用户提示
			return;
		}
		if(goodsName==null || "".equals(goodsName)){ //为空
			JOptionPane.showMessageDialog(null, "货物名称不能为空！");  //给用户提示
			return;
		}
		if(goodsCategory==null || "".equals(goodsCategory)){ //为空
			JOptionPane.showMessageDialog(null, "货物规格不能为空！");  //给用户提示
			return;
		}
		if(goodsNumber==null || "".equals(goodsNumber)){ //为空
			JOptionPane.showMessageDialog(null, "货物数量不能为空！");  //给用户提示
			return;
		}
		if(goodsDate==null || "".equals(goodsDate)){ //为空
			JOptionPane.showMessageDialog(null, "出库时间不能为空！");  //给用户提示
			return;
		}
		//从获取的货物信息创建货物对象
		Outbound outbound=new Outbound(Integer.parseInt(id), goodsDate,goodsName, Integer.parseInt(goodsCategory), Integer.parseInt(goodsNumber));
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con= DBTool.getConnetion();
			//初始化图书数据访问对象
			outboundDao=new OutboundDao();
			//执行图书访问对象的修改方法，并获得修改的记录数
			int res=outboundDao.update(con, outbound);
			if(res==1){ //为1
				JOptionPane.showMessageDialog(null,"修改成功n_n");
				//刷新图书表格显示
				fillGoodsTable(new Outbound());
				//重置操作栏
				resetValue();
			}else{ //为0
				JOptionPane.showMessageDialog(null,"修改失败u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("修改失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 删除事件
	 * @param evt
	 */
	private void deleteGoodsActionPerformed(ActionEvent evt) {
		String id=idText.getText();
		//判断是否id是否为空
		if(id==null || "".equals(id)){ //为空
			JOptionPane.showMessageDialog(null, "请选中要删除的行！");  //给用户提示
			return;
		}
		//定义数据库连接对象
		Connection con=null;
		try {
			//初始化数据库连接对象
			con=DBTool.getConnetion();
			//初始化图书数据访问对象
			outboundDao=new OutboundDao();
			//执行图书访问对象的删除方法并返回删除的记录数
			int res=outboundDao.delete(con, Integer.parseInt(id));
			if(res==1){ //为1
				JOptionPane.showMessageDialog(null, "删除成功n_n");
				//刷新图书表格显示
				fillGoodsTable(new Outbound());
				//重置操作栏
				resetValue();
			}else{ //为其他
				JOptionPane.showMessageDialog(null, "删除失败u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("删除失败",e);
		}finally{
			//记得关闭数据库（******）
			DBTool.close(con);
		}
	}
	/**
	 * 重置操作栏的所有值
	 */
	private void resetValue(){
		idText.setText("");
		goodsNameText.setText("");
		goodsCategoryText.setText("");
		goodsNumberText.setText("");
		goodsDateText.setText("");
	}
	/**
	 * 表格鼠标按下事件处理
	 * @param evt
	 */
	private void tableMousePressed(MouseEvent evt) {
		//获取图书表格选中的行的行号
		int row=GoodsTable.getSelectedRow();
		idText.setText((Integer)GoodsTable.getValueAt(row,0)+"");
		goodsNameText.setText((String)GoodsTable.getValueAt(row, 1));
		goodsCategoryText.setText((Integer)GoodsTable.getValueAt(row, 2)+"");
		goodsNumberText.setText((Integer)GoodsTable.getValueAt(row, 3)+"");
		goodsDateText.setText((String)GoodsTable.getValueAt(row, 4));
	}
	/**
	 * 文本框默认提示
	 */
	public class JTextFieldHintListener implements FocusListener {
		private String hintText;
		private JTextField textField;
		public JTextFieldHintListener(JTextField jTextField,String hintText) {
			this.textField = jTextField;
			this.hintText = hintText;
			jTextField.setText(hintText);  //默认直接显示
			jTextField.setForeground(Color.GRAY);
		}

		@Override
		public void focusGained(FocusEvent e) {
			//获取焦点时，清空提示内容
			String temp = textField.getText();
			if(temp.equals(hintText)) {
				textField.setText("");
				textField.setForeground(Color.BLACK);
			}

		}

		@Override
		public void focusLost(FocusEvent e) {
			//失去焦点时，没有输入内容，显示提示内容
			String temp = textField.getText();
			if(temp.equals("")) {
				textField.setForeground(Color.GRAY);
				textField.setText(hintText);
			}

		}

	}
	/**
	 * 查询事件处理
	 * @param evt 事件对象
	 */
	private void searchActionPerformed(ActionEvent evt) {
		//获取查询的条件
		String goodsDate=first_goodsDateText.getText();
		Outbound outbound=new Outbound();
		outbound.setGoodsDate(goodsDate);
		//调用table填充函数，根据查询结果重新填充表格
		fillGoodsTable(outbound);
	}
	/**
	 * 初始化表格，列出所有的信息
	 * @param outbound
	 */
	private void fillGoodsTable(Outbound outbound){
		//获取表格的模型
		DefaultTableModel dtm=(DefaultTableModel) GoodsTable.getModel();
		//填充表格时设置成0行（相当于归零处理）
		dtm.setRowCount(0);
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();

			outboundDao=new OutboundDao();
			//按条件查询（这里没有条件，查出所有信息）
			ResultSet rs=outboundDao.search(con, outbound);
			//遍历查询结果
			while(rs.next()){
				//定义一个集合，由于存储图书信息
				Vector v=new Vector();
				v.add(rs.getInt("outbound_record_id"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getInt("goods_category"));
				v.add(rs.getInt("outbound_quantity"));
				v.add(rs.getString("create_time"));
				//添加表格新行
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("初始化表格失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
}
