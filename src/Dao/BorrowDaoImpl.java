package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BorrowDaoImpl extends BaseDao implements BorrowDao{
	ResultSet rs=null;
	Connection con=null;
	PreparedStatement ps=null;
	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select READERID,NAME,BOOKNAME,ISBN,BOOKID,BORROWDATE,RETURNDATE " + 
				"from BOOK natural join BOOKCOPY natural join (BORROWBOOK natural join READER)";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	@Override
	public int add(String readerid, Date bdate, Date rdate, String bookid) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="insert into BORROWBOOK values(?,?,?,?)";
		Object[] paramas= {readerid,bdate,rdate,bookid};
		state=this.executeUpdate(sql, paramas);
		return state;
	}
	@Override
	public int delete(String bookid,String readerid) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="delete from BORROWBOOK where BOOKID=? and READERID=?";
		Object[] paramas= {bookid,readerid};
		state=this.executeUpdate(sql, paramas);
		return state;
	}
	@Override
	public int update(String bookid,String readerid, String data, String culumname) {
		// TODO Auto-generated method stub
		int state=0;
		if(culumname.equals("BORROWDATE")||culumname.equals("RETURNDATE")) {
			String sql="update BORROWBOOK set "+culumname+"=to_date(?,'yyyy-MM-dd') where (READERID=? and BOOKID=? )  ";

			Object[] paramas= {data,readerid,bookid};
			state=this.executeUpdate(sql, paramas);
		}
		else {

			String sql="update BORROWBOOK set "+culumname+"=? where (READERID=? and BOOKID=? ) ";
			Object[] paramas= {data,readerid,bookid};
			state=this.executeUpdate(sql, paramas);
		}
		return state;
	}
	@Override
	public ResultSet getBorrow(String data) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select READERID,NAME,BOOKNAME,ISBN,BOOKID,BORROWDATE,RETURNDATE " + 
				"from BOOK natural join BOOKCOPY natural join (BORROWBOOK natural join READER) "
				+ "where READERID like ? or NAME like ? or BOOKNAME like ? or BOOKID like ? ";
		Object[] paramas= {"%"+data+"%","%"+data+"%","%"+data+"%","%"+data+"%"};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	
	@Override
	public ResultSet getNoReturn() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select READERID,NAME,BOOKNAME,ISBN,BOOKID,BORROWDATE,RETURNDATE " + 
				"from BOOK natural join BOOKCOPY natural join (BORROWBOOK natural join READER) "
				+ "where RETURNDATE is NULL";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
	@Override
	public ResultSet getSbNoReturn(String readerid) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select READERID,NAME,BOOKNAME,ISBN,BOOKID,BORROWDATE,RETURNDATE " + 
				"from BOOK natural join BOOKCOPY natural join (BORROWBOOK natural join READER) "
				+ "where RETURNDATE is NULL and READERID=? ";
		Object[] paramas= {readerid};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}
}
