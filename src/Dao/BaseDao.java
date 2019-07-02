package Dao;
import java.util.*;
import java.sql.*;
import java.io.*;
public class BaseDao {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	Connection con=null;
	
	static {
		init();
	}
	public static void init() {
		Properties params=new Properties();
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			params.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		//根据指定的获取对应的值
		driver=params.getProperty("driver");
		url=params.getProperty("url");
		user=params.getProperty("username");
		password=params.getProperty("password");
		
	}
	
	
	public Connection getConnection() {
		if(con==null) {
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url, user, password);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return con;//返回链接对象
	}
	
	public void closeAll(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql,Object[] obj) {
		con=getConnection();
		PreparedStatement ps=null;
		int num=0;
		try {
			ps=con.prepareStatement(sql);
			if(ps!=null) {
				for(int i=0;i<obj.length;i++) {
					ps.setObject(i+1, obj[i]);//为预编译的sql设置参数
				}
				num=ps.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	public ResultSet getResultSet(String sql,Object[] obj) {
		con=getConnection();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			if(ps!=null) {
				for(int i=0;i<obj.length;i++) {
					ps.setObject(i+1, obj[i]);//为预编译的sql设置参数
				}
			}
			rs=ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
}
