package Dao;

import java.sql.ResultSet;

public class ReaderUserDaoImpl extends BaseDao implements ReaderUserDao{

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select * from READERUSER ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	

}
