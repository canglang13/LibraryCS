package Dao;

import java.sql.ResultSet;

public class ReaderTypeDaoImpl extends BaseDao implements ReaderTypeDao{

	@Override
	public ResultSet getAll() {
		ResultSet rs=null;
		String sql="select * from READERTYPE ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}

	@Override
	public int add(int typeid, String typename,int num) {
		int state=0;
		String sql="insert into READERTYPE values(?,?,?) ";
		Object[] paramas= {typeid,typename,num};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public int delete(int typeid) {
		int state=0;
		String sql="delete from READERTYPE where ID=? ";
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
