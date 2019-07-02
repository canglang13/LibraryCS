package Dao;

import java.sql.ResultSet;

public class ReaderDaoImpl extends BaseDao implements ReaderDao{

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select READERID,NAME,AGE,PHONE,DEPT,TYPENAME,USERID " + 
				"from READER natural join READERTYPE "
				+ "where TYPEID=ID";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}

	@Override
	public int add(String readerid, String name, int age, String phone, String dept, int typeid, String userid) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="insert into READER values(?,?,?,?,?,?,?)";
		Object[] paramas= {readerid,name,age,phone,dept,typeid,userid};
		state=this.executeUpdate(sql, paramas);
		String ss="insert into READERUSER values(?,'defautname','0000')";
		Object[] p= {userid};
		if(state==1) {
			this.executeUpdate(ss, p);
		}
		return state;
	}

	@Override
	public int delete(String readerid) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="delete from READER where READERID=?";
		Object[] paramas= {readerid};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public int update(String readerid, String data, String culumname) {
		// TODO Auto-generated method stub
		int state=0;
		//System.out.println(culumname);
		String sql="update READER set "+culumname+"=? where READERID=? ";
		Object[] paramas= {data,readerid};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public ResultSet getReader(String data) {
		ResultSet rs=null;
		String sql="select READERID,NAME,AGE,PHONE,DEPT,TYPENAME,USERID " + 
				"from READER natural join READERTYPE "
				+ "where TYPEID=ID and (READERID like ? or NAME like ? or DEPT like ? or TYPENAME like ?) ";
		Object[] paramas= {"%"+data+"%","%"+data+"%","%"+data+"%","%"+data+"%"};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	

}
