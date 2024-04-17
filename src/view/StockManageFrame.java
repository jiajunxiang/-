package view;


import dao.StockDao;
import model.Stock;
import util.DBTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class StockManageFrame extends JInternalFrame {
	private JLabel labelName;
	private JLabel labelNumber;
	private JComboBox nameCombox;
	private JTextField numberText;
	private JButton modifyBtn;
	private JButton deleteBtn;
	private StockDao stockDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockManageFrame frame = new StockManageFrame();
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
	public StockManageFrame() {
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

		setClosable(true);
		setIconifiable(true);
		setTitle("库存管理");
		setBounds(320, 150, 487, 342);

		labelName = new JLabel("货物名称：");
		nameCombox = new JComboBox();
		nameCombox.addItem("请选择...");
		nameCombox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
                {
					//这里写你的任务 ，比如取到现在的值
					String nameSelceted=nameCombox.getSelectedItem().toString();
					searchNumber(nameSelceted);
				}
			}
		});

		labelNumber = new JLabel("货物数量：");
		numberText = new JTextField();
		numberText.setColumns(10);

		//修改按钮
		modifyBtn = new JButton("修改");
		modifyBtn.setIcon(new ImageIcon(StockManageFrame.class.getResource("/images/modify.png")));
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		//删除按钮
		deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBookActionPerformed(e);
			}
		});
		deleteBtn.setIcon(new ImageIcon(StockManageFrame.class.getResource("/images/delete.png")));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(31)
								.addComponent(labelName)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(modifyBtn)
												.addGap(73)
												.addComponent(deleteBtn))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(nameCombox, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
												.addGap(35)
												.addComponent(labelNumber)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(numberText, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(42)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(labelNumber)
										.addComponent(numberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelName)
										.addComponent(nameCombox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(93)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(modifyBtn)
										.addComponent(deleteBtn))
								.addContainerGap(254, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		fillNameComBox();

	}

	/**
	 * 根据货物名称查询数量
	 * @param name
	 */
	private void searchNumber(String name) {
		//定义数据库连接
		Connection con = null;
		Stock stock=null;
		try {
			//获取数据库连接
			con = DBTool.getConnetion();
			//初始化图书数据访问对象
			stockDao = new StockDao();
			stock=new Stock();
			stock.setName(name);
			//执行图书访问对象的修改方法，并获得修改的记录数
			ResultSet res = stockDao.search(con, stock);
			while(res.next()){
				numberText.setText(res.getString("number"));
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("查找失败", e);
		} finally {
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 初始化货物名称下拉框
	 */
	private void fillNameComBox() {
		//定义数据库连接
		Connection con = null;
		Stock s_stock=null;
		try{
			con = DBTool.getConnetion();
			stockDao=new StockDao();
			ResultSet rs=stockDao.list(con, new Stock());
			while(rs.next()){
//				System.out.println(rs.getString("name"));
				s_stock=new Stock();
				s_stock.setName(rs.getString("name"));
				nameCombox.addItem(s_stock.getName());
			}
		}catch(Exception e){
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("初始化下拉框失败",e);
		}finally{
			//关闭数据库
			DBTool.close(con);
		}
	}

	/**
	 * 修改事件
	 * @param evt
	 */
	private void modifyActionPerformed(ActionEvent evt) {
		String nameSelceted=nameCombox.getSelectedItem().toString();
		String newNumber = numberText.getText();
		Stock stock = new Stock(nameSelceted, Integer.parseInt(newNumber));
		//定义数据库连接
		Connection con = null;
		try {
			//获取数据库连接
			con = DBTool.getConnetion();
			//初始化图书数据访问对象
			stockDao = new StockDao();
			//执行图书访问对象的修改方法，并获得修改的记录数
			int res = stockDao.update(con, stock);
			if (res == 1) { //为1
				JOptionPane.showMessageDialog(null, "修改成功n_n");
				//重置操作栏
				resetValue();
			} else { //为0
				JOptionPane.showMessageDialog(null, "修改失败u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("修改失败", e);
		} finally {
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 删除事件
	 * @param evt
	 */
	private void deleteBookActionPerformed(ActionEvent evt) {
		String stockName=nameCombox.getSelectedItem().toString();
		//判断是否为空
		if(stockName==null || "".equals(stockName)){ //为空
			JOptionPane.showMessageDialog(null, "请选中要删除的数据！");  //给用户提示
			return;
		}
		//定义数据库连接对象
		Connection con=null;
		try {
			//初始化数据库连接对象
			con=DBTool.getConnetion();
			//初始化图书数据访问对象
			stockDao=new StockDao();
			//执行图书访问对象的删除方法并返回删除的记录数
			int res=stockDao.delete(con, stockName);
			if(res==1){ //为1
				JOptionPane.showMessageDialog(null, "删除成功n_n");
				//重置操作栏
				resetValue();
				//重新加载
				fillNameComBox();
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
	 * 重置事件处理
	 */
	private void resetValue() {
		if(this.nameCombox.getItemCount()>0){
			this.nameCombox.setSelectedIndex(0);
		}
		numberText.setText("");
	}
}
