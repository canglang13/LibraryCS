package Dao;
import java.sql.*;
import java.sql.Date;
public class BookDaoImpl extends BaseDao implements BookDao{
	ResultSet rs=null;
	Connection con=null;
	PreparedStatement ps=null;
	
	public ResultSet getAll() {
		ResultSet rs=null;
		String sql="select ISBN,BOOKNAME,AUTHOR,PUBLISH,PUBLISHDATE,UNITPRICE,TYPENAME,NUM from BOOK  natural join BOOKTYPE natural join (select ISBN,count(BOOKID)as NUM from BOOKCOPY where STATE != 1 group by ISBN )where TYPEID=ID ";
		Object[] paramas= {};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}

	@Override
	public int delete(String isbn) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="delete from BOOK where isbn=?";
		Object[] paramas= {isbn};
		state=this.executeUpdate(sql, paramas);
		return state;
	}

	@Override
	public int update(String isbn, String data, String culumname) {
		// TODO Auto-generated method stub
		int state=0;
		if(culumname.equals("PUBLISHDATE")) {
			String sql="update BOOK set "+culumname+"=to_date(?,'yyyy-MM-dd') where ISBN=? ";
			Object[] paramas= {data,isbn};
			state=this.executeUpdate(sql, paramas);
		}
		else {
			String sql="update BOOK set "+culumname+"=? where ISBN=? ";
			Object[] paramas= {data,isbn};
			state=this.executeUpdate(sql, paramas);
		}
		return state;
	}

	@Override
	public ResultSet getBook(String data) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql="select ISBN,BOOKNAME,AUTHOR,PUBLISH,PUBLISHDATE,UNITPRICE,TYPENAME,NUM  " + 
				"from BOOK  natural join BOOKTYPE natural join (select ISBN,count(BOOKID)as NUM from BOOKCOPY where STATE!=1 group by ISBN) " + 
				"where TYPEID=ID and (ISBN like ? or BOOKNAME like ? or AUTHOR like ? or TYPENAME like ?) " ;
		Object[] paramas= {"%"+data+"%","%"+data+"%","%"+data+"%","%"+data+"%"};
		rs=this.getResultSet(sql,paramas);
		return rs;
	}

	@Override
	public int add(String isbn, String name, String author, String publish, Date pdate, Float price, int type,int num) {
		// TODO Auto-generated method stub
		int state=0;
		String sql="insert into BOOK values(?,?,?,?,?,?,?)";
		Object[] paramas= {isbn,name,author,publish,pdate,price,type};
		state=this.executeUpdate(sql, paramas);
		for (int i=1;i<=num;i++) {
			String ss="insert into BOOKCOPY values(?,?,?) ";
			Object[] pp= {isbn+String.valueOf(i),isbn,0};
			this.executeUpdate(ss, pp);
		}
		return state;
	}
}
