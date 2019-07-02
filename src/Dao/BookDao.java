package Dao;
import java.sql.*;
import java.sql.Date;
public interface BookDao {
	public ResultSet getAll();
	public int add(String isbn,String name,String author,String publish,Date pdate,Float price,int type,int num);
	public int delete(String isbn);
	public int update(String isbn,String data,String culumname);
	public ResultSet getBook(String data);
}
