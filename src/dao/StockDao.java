package dao;

import model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 库存dao类
 * @author jjx
 *
 */
public class StockDao {
    /**
	 * 库存添加
	 * @param con 数据库连接对象
	 * @param stock 要添加的货物类别
	 * @return 返回数据库操作的记录数
	 * @throws SQLException 异常
	 */
    public int add(Connection con, Stock stock) throws SQLException{
		//拼写sql插入语句
		String sql="insert into stock values(?,?)";
		//创建预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1, stock.getName());
		ps.setInt(2, stock.getNumber());
		//返回数据库操作的记录数
		return ps.executeUpdate();
	}
	/**
	 * 库存列表
	 * @param con 数据库连接对象
	 * @param stock 要添加的货物类别
	 * @return 返回查询的结果集
	 * @throws SQLException
	 */
	public ResultSet list(Connection con, Stock stock) throws SQLException{
		/*
		 * 思路：当jdbc查询数据库有多个条件从外部输入时，这是最好创建一个字符串缓冲类来添加条件到sql语句中。
		 * 同时，不知道有哪些条件是第一条件，无法确定where关键字的所在，于是添加条件都用（and 条件）
		 * 最后字符串转换成字符串时在将第一个and替换成where
		 */
		//定义一个库存名称
		String goodsName=null;
		if(stock!=null){ //如果类别对象不为空的话，就获取它的类别名称
			goodsName=stock.getName();
		}
		//创建一个字符串缓冲类
		StringBuilder sb=new StringBuilder("select name from stock");
		//添加查询语句的条件（and + 条件）
		if(!(goodsName==null || "".equals(goodsName.trim()))){
			//jdbc中，数据库字符串要用单引号括起来
			sb.append(" and name like '%"+stock.getName()+"%'");
		}
		//将字符串缓冲对象转换成字符串，同时把第一个and替换成where
		String sql=sb.toString().replaceFirst("and", "where");
		//获取预编译对象
		PreparedStatement ps=con.prepareStatement(sql);
		//返回ps执行查询之后的结果集
		return ps.executeQuery();
	}



	/**
	 * 库存查询
	 * @param con 数据库连接对象
	 * @param stock 要添加的货物类别
	 * @return 返回查询的结果集
	 * @throws SQLException
	 */
	public ResultSet search(Connection con, Stock stock) throws SQLException{
		/*
		 * 思路：当jdbc查询数据库有多个条件从外部输入时，这是最好创建一个字符串缓冲类来添加条件到sql语句中。
		 * 同时，不知道有哪些条件是第一条件，无法确定where关键字的所在，于是添加条件都用（and 条件）
		 * 最后字符串转换成字符串时在将第一个and替换成where
		 */
		//定义一个库存名称
		String goodsName=null;
		if(stock!=null){ //如果类别对象不为空的话，就获取它的类别名称
			goodsName=stock.getName();
		}
		//创建一个字符串缓冲类
		StringBuilder sb=new StringBuilder("select number from stock");
		//添加查询语句的条件（and + 条件）
		if(!(goodsName==null || "".equals(goodsName.trim()))){
			//jdbc中，数据库字符串要用单引号括起来
			sb.append(" and name like '%"+stock.getName()+"%'");
		}
		//将字符串缓冲对象转换成字符串，同时把第一个and替换成where
		String sql=sb.toString().replaceFirst("and", "where");
		//获取预编译对象
		PreparedStatement ps=con.prepareStatement(sql);
		//返回ps执行查询之后的结果集
		return ps.executeQuery();
	}


	/**
	 * 库存修改
	  * @param con 数据库连接对象
	 * @param stock 要修改的货物类别
	 * @return 返回数据库更新的记录数
	 * @throws SQLException
	 */
	public int update(Connection con, Stock stock) throws SQLException{
		//拼写sql更新语句
		String sql="update stock set number=? where name=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setInt(1, stock.getNumber());
		ps.setString(2, stock.getName());
		//赶回ps更新数据库的记录数
		return ps.executeUpdate();
	}
	/**
	 * 删除数据库记录
	 * @param con 数据库连接对象
	 * @param name 传入删除记录的name
	 * @return 返回删除的记录数
	 * @throws SQLException 抛出数据库异常
	 */
	public int delete(Connection con,String name) throws SQLException{
		//拼写sql删除语句
		String sql="delete from stock where name=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1, name);
		//执行ps更行操作，并返回改变的数据记录条数
		return ps.executeUpdate();
	}
}
