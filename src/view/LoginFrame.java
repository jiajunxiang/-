package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.DBTool;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton loginBtn;
	private JButton resetBtn;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/logo.jpg")));
		setResizable(false);
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("仓库管理系统");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logo.jpg")));

		JLabel lblNewLabel_1 = new JLabel("用户名 :");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/userName.png")));

		usernameText = new JTextField();
		usernameText.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("密  码 :");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));

		passwordText = new JPasswordField();

		//添加登陆按钮
		loginBtn = new JButton("登录");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		loginBtn.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));

		//添加重置按钮
		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});

		resetBtn.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/reset.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addComponent(loginBtn)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(resetBtn)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(105, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginBtn)
						.addComponent(resetBtn))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		//设置窗口居中
		this.setLocationRelativeTo(null);
	}
	/**
	 * 登录事件处理
	 * @param evt
	 */
	private void loginActionPerformed(ActionEvent evt) {
		//从输入框获取用户名
		String username=usernameText.getText().trim();
		//从输入框获取密码
		String password=passwordText.getText().trim();
		//用户名不能为空或空字符串,否则结束事件处理
		if(username==null || "".equals(username)){
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		//用户名不能为空或空字符串否则结束事件处理
		if(password==null || "".equals(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		//将从输入框获得信息新建一个对象
		User user=new User(username, password);
		//定义数据库连接
		Connection con=null;
		try {
			//利用数据库工具类获取数据库连接
			con=DBTool.getConnetion();
			//新建一个用户数据访问对象
			UserDao userDao=new UserDao();
			//调用其登录验证方法获取一个用户对象
			User currUser=userDao.login(con, user);
			//判断返回的用户对象
			if(currUser!=null){//不为空,表示登录成功
				//进入主界面,并设置可见
				new MainFrame().setVisible(true);
				//释放当前窗口资源
				dispose();
			}else{ //为空,表示登录不成功
				JOptionPane.showMessageDialog(null, "登录失败(u_u)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登录失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 重置事件处理
	 * @param evt
	 */
	private void resetActionPerformed(ActionEvent evt) {
		usernameText.setText("");
		passwordText.setText("");
	}
}
