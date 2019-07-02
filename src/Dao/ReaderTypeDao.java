package Dao;

import java.sql.ResultSet;

public interface ReaderTypeDao {
	public ResultSet getAll();
	public int add(int typeid,String typename,int num);
	public int delete(int typeid);
	public int update(int typeid,String data);
}
