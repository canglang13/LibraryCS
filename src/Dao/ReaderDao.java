package Dao;

import java.sql.ResultSet;

public interface ReaderDao {
	public ResultSet getAll();
	public int add(String readerid,String name,int age,String phone,String dept,int typeid,String userid);
	public int delete(String readerid);
	public int update(String readerid,String data,String culumname);
	public ResultSet getReader(String data);
}
