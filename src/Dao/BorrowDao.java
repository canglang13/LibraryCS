package Dao;
import java.sql.*;
public interface BorrowDao {
	public ResultSet getAll();
	public int add(String readerid,Date bdate,Date rdate,String bookid);
	public int delete(String bookid,String readerid);
	public int update(String bookid,String readerid,String data,String culumname);
	public ResultSet getBorrow(String data);
	public ResultSet getNoReturn();
	public ResultSet getSbNoReturn(String readerid);
}
