package view;

import dao.WarehousingDao;
import model.Warehousing;
import util.DBTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddGoodsFrame extends JInternalFrame {
	//货物名称输入框
    private JTextField goodsNameText;
	//货物数量输入框
	private JTextField goodsNumberText;
	//添加按钮
	private JButton addBtn;
	//重置按钮
	private JButton resetBtn;
	//货物入库数据库访问对象
	private WarehousingDao warehousingdao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoodsFrame frame = new AddGoodsFrame();
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
	public AddGoodsFrame(){
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
		setTitle("货物入库");
		setBounds(320, 150, 487, 342);


		JLabel label1 = new JLabel("货物名称：");
		goodsNameText=new JTextField();
		goodsNameText.setColumns(10);
		goodsNameText.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				addFocusListener(e);
			}
		});


		JLabel label2 = new JLabel("货物数量：");
		goodsNumberText=new JTextField();
		goodsNumberText.setColumns(10);


		//添加按钮
		addBtn = new JButton("添加");
		addBtn.setIcon(new ImageIcon(AddGoodsFrame.class.getResource("/images/add.png")));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		//重置按钮
		resetBtn = new JButton("重置");
		resetBtn.setIcon(new ImageIcon(AddGoodsFrame.class.getResource("/images/reset.png")));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label1)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(goodsNameText, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label2)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(goodsNumberText, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(addBtn)
							.addGap(73)
							.addComponent(resetBtn)))
					.addGap(69))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(goodsNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(goodsNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(addBtn)
						.addComponent(resetBtn))
					.addGap(28))
		);

		getContentPane().setLayout(groupLayout);


	}
	/**
	 *判断库存表中是否存在这种货物
	 */
	private void addFocusListener(FocusEvent evt){
		String name=goodsNameText.getText();
		String number=goodsNumberText.getText();
		if(name.isEmpty()&&number.isEmpty()){
			return;
		} else if (name.isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入货物名称或检查输入格式");
		} else {
			String addStockName = name.split("\\*")[0];
			//新建货物入库实体对象
			Warehousing warehousing = new Warehousing();
			warehousing.setGoodsName(addStockName);
			//定义数据库连接
			Connection con = null;
			try {
				//获取数据库连接
				con = DBTool.getConnetion();
				warehousingdao = new WarehousingDao();
				//按条件查询（这里没有条件，查出所有信息）
				ResultSet rs = warehousingdao.query(con, warehousing);
				if (rs.next() && rs.getString(1).equals("1")) {
					System.out.println("youjilu");
				} else {
					System.out.println("wu");
					int res = warehousingdao.insertStock(con, warehousing);
					if (res != 0) {
						//提示添加成功
						JOptionPane.showMessageDialog(null, "成功(n_n)");
					} else {
						//提示添加失败
						JOptionPane.showMessageDialog(null, "失败(u_u)");
					}
				}
			} catch (SQLException e) {
				//记录日志
				e.printStackTrace();
				throw new RuntimeException("失败", e);
			} finally {
				//关闭数据库连接
				DBTool.close(con);
			}
		}
	}
	/**
	 * 添加事件处理
	 * @param evt
	 */
	private void addActionPerformed(ActionEvent evt) {
		Date queueDate = new Date();
        SimpleDateFormat queueDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = queueDateFormat.format(queueDate);
		//获取输入框的值
		String goodsName = goodsNameText.getText();
		String []add=goodsName.split("\\*");
		String add_name=add[0];
		int add_category=Integer.parseInt(add[1]);
		int add_number=Integer.parseInt(goodsNumberText.getText());

		if (goodsName == null || "".equals(goodsName.trim())) {
			JOptionPane.showMessageDialog(null, "货物名称不能为空！");
			return;
		}
		if (goodsNumberText.getText() == null || "".equals(goodsNumberText.getText().trim())) {
			JOptionPane.showMessageDialog(null, "货物数量不能为空！");
			return;
		}
		//新建货物入库实体对象
		Warehousing warehousing = new Warehousing(nowDate,add_name,add_category,add_number);
		//定义数据库连接
		Connection con = null;
		try {
			//获取数据库连接
			con = DBTool.getConnetion();
			//初始化货物入库实体对象
			warehousingdao = new WarehousingDao();
			//调用图书类别dao对象的添加方法
			int res = warehousingdao.add(con, warehousing);
			if (res != 0) {
				//提示添加成功
				JOptionPane.showMessageDialog(null, "入库成功(n_n)");
				//清空输入框
				goodsNameText.setText("");
				goodsNumberText.setText("");
			} else {
				//提示添加失败
				JOptionPane.showMessageDialog(null, "入库失败(u_u)");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("入库失败", e);
		} finally {
			//关闭数据库
			DBTool.close(con);
		}
	}
	/**
	 * 重置事件处理
	 * @param evt
	 */
	private void resetActionPerformed(ActionEvent evt) {
		//置空图书类别名称输入框
		goodsNameText.setText("");
		//置空图书类别描述输入框
		goodsNumberText.setText("");
	}
}