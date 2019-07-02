package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookCopyDaoImpl extends BaseDao implements BookCopyDao{

	ResultSet rs=null;
	Connection con=null;
	PreparedStatement ps=null;
	
	public ResultSet getAll() {
		ResultSet rs=null;
		String sql="select * from Bookcopy ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
}
