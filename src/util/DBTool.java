package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * @author green
 *
 */
public class DBTool {
	private static String driver="com.mysql.cj.jdbc.Driver";  //数据库驱动
	private static String url="jdbc:mysql://localhost:3306/gms?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  //数据库连接地址
	private static String user="root"; //数据库连接用户
	private static String password="123123";  //数据库连接密码
	static {
		try {
			//加载数据库驱动类到程序中
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("加载驱动失败",e);
		}
	}

	/**
	 * 获取数据库连接
	 * @return 数据库连接对象
	 * @throws SQLException 提醒调用者捕获异常,并在finally中关闭关闭异常
	 */
	public static Connection getConnetion() throws SQLException{
		//通过DriverManager获得数据库连接
		return DriverManager.getConnection(url, user, password);
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 */
	public static void close(Connection con){
		if(con!=null){ //如果数据连接不为空
			try {
				//关闭数据库连接
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("数据库关闭失败",e);
			}
		}
	}
	/**
	 * 测试数据库连接工具是否可用
	 * @param args
	 */
//	public static void main(String[] args) {
//		Connection con=null;
//		try {
//			con=DBTool.getConnetion();
//			System.out.println("数据库连接成功!");
//		} catch (SQLException e) {
//			System.out.println("数据库连接失败!");
//			e.printStackTrace();
//		}finally{
//			DBTool.close(con);
//		}
//	}
}
