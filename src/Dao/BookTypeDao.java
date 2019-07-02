package Dao;

import java.sql.ResultSet;

public interface BookTypeDao {
	public ResultSet getAll();
	public int add(int typeid,String typename);
	public int delete(int typeid);
	public int update(int typeid,String data);
}
