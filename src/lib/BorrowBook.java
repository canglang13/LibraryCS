package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.BookCopyDaoImpl;
import Dao.BookDaoImpl;
import Dao.BorrowDaoImpl;
import Dao.ReaderDaoImpl;

public class BorrowBook extends JFrame implements ActionListener{
	private String id;
	private JPanel jp=new JPanel();
	private JButton but1=new JButton("借阅");
	private JButton but3=new JButton("搜索");
	private JTextField textField=new JTextField();
	private JTable tab=new JTable();
	private JScrollPane jsp=new JScrollPane();
	private DefaultTableModel model=null;
	private Vector rowData=new Vector();
	private Vector columnName=new Vector();
	
	BorrowBook(String s,String id) throws SQLException{
		super(s);
		this.id=id;
		initialize();
	}
	
	public void initialize() throws SQLException {
		this.setBounds(400, 250, 677, 480);
		this.getContentPane().setLayout(null);
		but1.setBounds(75, 399, 118, 34);
		but1.addActionListener(this);
		but3.setBounds(491, 399, 103, 34);
		but3.addActionListener(this);
		textField.setBounds(282, 403, 177, 28);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		jp.setBounds(0, 0, 665, 389);
		columnName.add("ISBN");
		columnName.add("书名");columnName.add("作者");columnName.add("出版社");columnName.add("出版日期");
		columnName.add("单价");columnName.add("图书类型");columnName.add("现存数量");
		this.add(but1);this.add(but3);
		jp.setLayout(new BorderLayout());
		setData();
		tab=new JTable(rowData,columnName);
		DefaultTableModel model=new DefaultTableModel(rowData,columnName) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		tab.setModel(model);
		jsp=new JScrollPane(tab);
		jp.add(jsp,BorderLayout.CENTER);
		this.add(jp);
		this.setVisible(true);
	}
	
	public void setData() throws SQLException {
		rowData.clear();
		BookDaoImpl b=new BookDaoImpl();
		ResultSet rs=b.getAll();
		Vector row=new Vector();
		while(rs.next()) {
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			for (int i=1;i<=colCount;i++) {
				row.add(rs.getObject(i));
			}
			rowData.add(row.clone());
			row.clear();
		}
	}
	
	public void fresh() throws SQLException {
		setData();
		tab.updateUI();
	}
	
	public void search(String data) throws SQLException {
		rowData.clear();
		BookDaoImpl b=new BookDaoImpl();
		ResultSet rs=b.getBook(data);
		Vector row=new Vector();
		while(rs.next()) {
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			for (int i=1;i<=colCount;i++) {
				row.add(rs.getObject(i));
			}
			rowData.add(row.clone());
			row.clear();
		}
		tab.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==but1) {
			if(tab.getSelectedRow()==-1) {
				new JOptionPane().showMessageDialog(null,"请选择你要借阅的图书");
			}
			else {
				int r=tab.getSelectedRow();
				String isbn=(String) tab.getValueAt(r,0);
				String readerid=null;
				String bookid=null;
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date bdt = new java.util.Date();
				java.sql.Date bd=new java.sql.Date(bdt.getTime());
				java.sql.Date rd=null;
				
				ResultSet rs=new BookCopyDaoImpl().getAll();
				try {
					while (rs.next()) {
						if(rs.getString(2).equals(isbn)&&rs.getInt(3)==0) {
							bookid=rs.getString(1);
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				ResultSet rss=new ReaderDaoImpl().getAll();
				try {
					while (rss.next()) {
						if(id.equals(rss.getString(7))){
							readerid=rss.getString(1);
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				int state=new BorrowDaoImpl().add(readerid, bd, rd, bookid);
				if(state==1) {
					new JOptionPane().showMessageDialog(null,"借书成功");
					try {
						fresh();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();
				}
				else {
					new JOptionPane().showMessageDialog(null,"借阅失败");
				}
			}
		}//end of but1
		if(e.getSource()==but3) {
			String data=textField.getText();
			try {
				search(data);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
