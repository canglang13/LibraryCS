package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.BookDaoImpl;
import Dao.BookTypeDaoImpl;
import Dao.ReaderTypeDaoImpl;

public class ImforManage extends JFrame implements ActionListener{
	private JPanel book=new JPanel();
	private JPanel reader=new JPanel();
	private JButton but1=new JButton("添加图书类型");
	private JButton but2=new JButton("删除");
	private JButton but3=new JButton("添加读者类型");
	private JButton but4=new JButton("删除");
	private JTable tab1=new JTable();
	private JTable tab2=new JTable();
	private JScrollPane jsp1=new JScrollPane();
	private JScrollPane jsp2=new JScrollPane();
	private DefaultTableModel model1=null;
	private DefaultTableModel model2=null;
	private Vector rowData1=new Vector();
	private Vector rowData2=new Vector();
	private Vector columnName1=new Vector();
	private Vector columnName2=new Vector();
	
	ImforManage(String s) throws SQLException{
		super(s);
		initialize();
	}
	
	public void initialize() throws SQLException {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 250, 553, 386);
		this.getContentPane().setLayout(null);
		book.setBounds(0, 10, 266, 273);
		this.add(book);
		reader.setBounds(276, 10, 266, 273);
		this.add(reader);
		but1.setBounds(20, 293, 120, 35);
		this.add(but1);but1.addActionListener(this);
		but2.setBounds(144, 293, 99, 35);
		this.add(but2);but2.addActionListener(this);
		but3.setBounds(295, 293, 120, 35);
		this.add(but3);but3.addActionListener(this);
		but4.setBounds(419, 293, 99, 35);
		this.add(but4);but4.addActionListener(this);
		columnName1.add("ID");columnName1.add("图书类型");
		columnName2.add("ID");columnName2.add("读者类型");columnName2.add("最大借阅数量");
		book.setLayout(new BorderLayout());reader.setLayout(new BorderLayout());
		setData1();setData2();
		tab1=new JTable(rowData1,columnName1);tab2=new JTable(rowData2,columnName2);
		DefaultTableModel model1=new DefaultTableModel(rowData1,columnName1) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		DefaultTableModel model2=new DefaultTableModel(rowData2,columnName2) {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		tab1.setModel(model1);tab2.setModel(model2);
		jsp1=new JScrollPane(tab1);jsp2=new JScrollPane(tab2);
		book.add(jsp1,BorderLayout.CENTER);reader.add(jsp2,BorderLayout.CENTER);
		this.add(book);this.add(reader);
		this.setVisible(true);
	}
	
	public void setData1() throws SQLException {
		rowData1.clear();
		ResultSet rs=new BookTypeDaoImpl().getAll();
		Vector row=new Vector();
		while(rs.next()) {
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			for (int i=1;i<=colCount;i++) {
				row.add(rs.getObject(i));
			}
			rowData1.add(row.clone());
			row.clear();
		}
	}
	public void setData2() throws SQLException {
		rowData2.clear();
		ResultSet rs=new ReaderTypeDaoImpl().getAll();
		Vector row=new Vector();
		while(rs.next()) {
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			for (int i=1;i<=colCount;i++) {
				row.add(rs.getObject(i));
			}
			rowData2.add(row.clone());
			row.clear();
		}
	}
	
	public void fresh1() throws SQLException {
		setData1();
		tab1.updateUI();
	}
	public void fresh2() throws SQLException {
		setData2();
		tab2.updateUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==but1) {
			new AddBookType("添加",this);
		}
		if(e.getSource()==but2) {
			if(tab1.getSelectedRow()==-1) {
				new JOptionPane().showMessageDialog(null,"请选择你要删除的数据");
			}
			else {
				int[] r=tab1.getSelectedRows();
				for (int i=0;i<r.length;i++) {
					String idd=tab1.getValueAt(r[i],0).toString();
					int id=Integer.parseInt(idd);
					new BookTypeDaoImpl().delete(id);
				}
				new JOptionPane().showMessageDialog(null,"删除成功");
				try {
					fresh1();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}//end of but2
		if(e.getSource()==but3) {
			new AddReaderType("添加",this);
		}
		if(e.getSource()==but4) {
			if(tab2.getSelectedRow()==-1) {
				new JOptionPane().showMessageDialog(null,"请选择你要删除的数据");
			}
			else {
				int[] r=tab2.getSelectedRows();
				for (int i=0;i<r.length;i++) {
					String idd=tab2.getValueAt(r[i],0).toString();
					int id=Integer.parseInt(idd);
					new ReaderTypeDaoImpl().delete(id);
				}
				new JOptionPane().showMessageDialog(null,"删除成功");
				try {
					fresh2();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
