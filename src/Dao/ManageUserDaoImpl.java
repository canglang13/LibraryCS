package Dao;

import java.sql.ResultSet;

public class ManageUserDaoImpl extends BaseDao implements ManageUserDao{

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select * from MANAGERUSER ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	

}
