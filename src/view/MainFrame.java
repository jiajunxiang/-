package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//定义内容窗格
	private JPanel contentPane;
	//定义桌面窗格
	private JDesktopPane table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}


		setTitle("仓库管理系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 1060, 700);




		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("开始");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menuBar.add(menu);
//
//		JMenu mnNewMenu = new JMenu("货物信息管理");
//		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
//		menu.add(mnNewMenu);

		//安全退出
		JMenuItem menuItem_4 = new JMenuItem("安全退出");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出退出确认提示框
				int res=JOptionPane.showConfirmDialog(null, "确定要退出吗？");
				//确定退出
				if(res==JOptionPane.OK_OPTION){
					dispose();
				}
				//否则继续留在该界面
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		menu.add(menuItem_4);


		/*
		进货管理菜单项
		 */
		JMenu menu_jinhuo= new JMenu("进货管理");
		menu_jinhuo.setIcon(new ImageIcon(MainFrame.class.getResource("/images/jinhuo.png")));
		menuBar.add(menu_jinhuo);

		JMenuItem menuItem_ru= new JMenuItem("货物入库");
		menuItem_ru.setIcon(new ImageIcon(MainFrame.class.getResource("/images/ware.png")));
		menu_jinhuo.add(menuItem_ru);
		menuItem_ru.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddGoodsFrame addGoodsFrame =new AddGoodsFrame();
				addGoodsFrame.setVisible(true);
				table.add(addGoodsFrame);
			}
		});
		JMenuItem menuItem_RuDetail= new JMenuItem("入库明细");
		menuItem_RuDetail.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menu_jinhuo.add(menuItem_RuDetail);
		menuItem_RuDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddGoodsMangerFrame addGoodsMangerFrame=new AddGoodsMangerFrame();
				addGoodsMangerFrame.setVisible(true);
				table.add(addGoodsMangerFrame);
			}
		});
		/*
		出货管理菜单项
		 */
		JMenu menu_chuhuo= new JMenu("出货管理");
		menu_chuhuo.setIcon(new ImageIcon(MainFrame.class.getResource("/images/chuhuo.png")));
		menuBar.add(menu_chuhuo);
		JMenuItem menuItem_chu= new JMenuItem("货物出库");
		menuItem_chu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/out.png")));
		menu_chuhuo.add(menuItem_chu);
		menuItem_chu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelGoodsFrame delGoodsFrame=new DelGoodsFrame();
				delGoodsFrame.setVisible(true);
				table.add(delGoodsFrame);
			}
		});
		JMenuItem menuItem_ChuDetail= new JMenuItem("出库明细");
		menuItem_ChuDetail.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menu_chuhuo.add(menuItem_ChuDetail);
		menuItem_ChuDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelGoodsMangerFrame delGoodsMangerFrame=new DelGoodsMangerFrame();
				delGoodsMangerFrame.setVisible(true);
				table.add(delGoodsMangerFrame);
			}
		});
		/*
		库存管理菜单项
		 */
		JMenu menu_kucun= new JMenu("库存管理");
		menu_kucun.setIcon(new ImageIcon(MainFrame.class.getResource("/images/kucun.png")));
		menuBar.add(menu_kucun);
		JMenuItem menuItem_add= new JMenuItem("库存添加");
		menuItem_add.setIcon(new ImageIcon(MainFrame.class.getResource("/images/ware.png")));
		menu_kucun.add(menuItem_add);
		menuItem_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StockFrame stockFrame =new StockFrame();
				stockFrame.setVisible(true);
				table.add(stockFrame);
			}
		});
		JMenuItem menuItem_StockDetail= new JMenuItem("库存明细");
		menuItem_StockDetail.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menu_kucun.add(menuItem_StockDetail);
		menuItem_StockDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StockManageFrame stockManageFrame=new StockManageFrame();
				stockManageFrame.setVisible(true);
				table.add(stockManageFrame);
			}
		});




		JMenu menu_2 = new JMenu("关于我们");
		menu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(menu_2);

		menu_2.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {

				JOptionPane.showMessageDialog(null, "欢迎使用仓库管理系统", "关于我们", JOptionPane.INFORMATION_MESSAGE);
//				//新建一个图书内部窗体

//				AboutUsFrame frame=new AboutUsFrame();
//				//显示图书内部窗体
//				frame.setVisible(true);
//				JOptionPane.getFrameForComponent(frame);
//				//将图书内部窗体显示到主界面桌面窗格中
//				table.add(frame);
			}

			@Override
			public void menuDeselected(MenuEvent e) {

			}

			@Override
			public void menuCanceled(MenuEvent e) {

			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//定义主界面桌面窗格界面，用于装载内部窗体
		table = new JDesktopPane();
		table.setBackground(Color.LIGHT_GRAY);
		contentPane.add(table, BorderLayout.CENTER);
		//设置窗口最大化
//		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JButton button1 = new JButton("入库");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoodsFrame addGoodsFrame =new AddGoodsFrame();
				addGoodsFrame.setVisible(true);
				table.add(addGoodsFrame);
			}
		});
		button1.setBounds(195, 217, 275, 100);
		table.add(button1);

		JButton button2 = new JButton("出库");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelGoodsFrame delGoodsFrame=new DelGoodsFrame();
				delGoodsFrame.setVisible(true);
				table.add(delGoodsFrame);
			}
		});
		button2.setBounds(580, 217, 275, 100);
		table.add(button2);
	}
}

