package Dao;

import java.sql.ResultSet;

public class BookTypeDaoImpl extends BaseDao implements BookTypeDao {

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select * from BOOKTYPE ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}

	@Override
	public int add(int typeid, String typename) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="insert into BOOKTYPE values(?,?) ";
		Object[] paramas= {typeid,typename};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public int delete(int typeid) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="delete from BOOKTYPE where ID=? ";
		Object[] paramas= {typeid};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public int update(int typeid, String data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
