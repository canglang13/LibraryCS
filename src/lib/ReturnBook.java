package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ReturnBook extends JFrame implements ActionListener{
	
	private String id;
	private JPanel jp=new JPanel();
	private JButton but1=new JButton("归还");
	private JTextField textField=new JTextField();
	private JTable tab=new JTable();
	private JScrollPane jsp=new JScrollPane();
	private DefaultTableModel model=null;
	private Vector rowData=new Vector();
	private Vector columnName=new Vector();
	
	ReturnBook(String s,String id) throws SQLException{
		super(s);
		this.id=id;
		initialize();
	}
	
	public void initialize() throws SQLException {
		this.setBounds(400, 250, 677, 480);
		this.getContentPane().setLayout(null);
		but1.setBounds(75, 399, 118, 34);
		but1.addActionListener(this);
		jp.setBounds(0, 0, 665, 389);
		columnName.add("读者ID");columnName.add("名字");columnName.add("书名");
		columnName.add("ISBN");columnName.add("BOOKID");
		columnName.add("借出时间");columnName.add("归还时间");
		this.add(but1);
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
		ResultSet rs=new BorrowDaoImpl().getSbNoReturn(id);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==but1) {
			if(tab.getSelectedRow()==-1) {
				new JOptionPane().showMessageDialog(null,"请选择你要归还的图书");
			}
			else {
				int r=tab.getSelectedRow();
				String readerid=null;
				String bookid=(String) tab.getValueAt(r,4);
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = new java.util.Date();
				String data=sdf.format(d);
				
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
				
				
				
				int state=new BorrowDaoImpl().update(bookid, readerid, data, "RETURNDATE");
				if(state==1) {
					new JOptionPane().showMessageDialog(null,"还书成功");
					try {
						fresh();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();
				}
				else {
					new JOptionPane().showMessageDialog(null,"还书失败");
				}
			}
		}
	}

}
