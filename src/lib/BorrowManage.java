package lib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.BookDaoImpl;
import Dao.BorrowDaoImpl;

public class BorrowManage extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();
	private JButton but1=new JButton("���");
	private JButton but2=new JButton("ɾ��");
	private JButton but3=new JButton("δ�黹��¼");
	private JButton but4=new JButton("����");
	private JTextField textField=new JTextField();
	private JTable tab=new JTable();
	private JScrollPane jsp=new JScrollPane();
	private DefaultTableModel model=null;
	private Vector rowData=new Vector();
	private Vector columnName=new Vector();
	
	BorrowManage(String s) throws SQLException{
		super(s);
		initialize();
	}
	
	public void initialize() throws SQLException{
		this.setBounds(400, 250, 677, 480);
		this.getContentPane().setLayout(null);
		but1.setBounds(10, 399, 87, 34);
		but1.addActionListener(this);
		but2.setBounds(123, 399, 87, 34);
		but2.addActionListener(this);
		but3.setBounds(234, 399, 87, 34);
		but3.addActionListener(this);
		but4.setBounds(539, 399, 103, 34);
		but4.addActionListener(this);
		textField.setBounds(352, 403, 177, 28);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		jp.setBounds(0, 0, 665, 389);
		columnName.add("����ID");columnName.add("����");columnName.add("����");
		columnName.add("ISBN");columnName.add("BOOKID");
		columnName.add("���ʱ��");columnName.add("�黹ʱ��");
		this.add(but1);this.add(but2);this.add(but3);this.add(but4);
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
		mouse();
		this.setVisible(true);
	}
	
	public void setData() throws SQLException {
		rowData.clear();
		ResultSet rs=new BorrowDaoImpl().getAll();
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
	
	public void noReturn() throws SQLException {
		rowData.clear();
		ResultSet rs=new BorrowDaoImpl().getNoReturn();
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
	
	public void search(String data) throws SQLException {
		rowData.clear();
		ResultSet rs=new BorrowDaoImpl().getBorrow(data);
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
			try {
				new AddBorrow("�����Ϣ",this);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but2) {
			if(tab.getSelectedRow()==-1) {
				new JOptionPane().showMessageDialog(null,"��ѡ����Ҫɾ��������");
			}
			else {
				boolean flag=false;
				int[] r=tab.getSelectedRows();
				for (int i=0;i<r.length;i++) {
					//String rdate=tab.getValueAt(r[i],6).toString();
					if(tab.getValueAt(r[i],6)==null) {
						flag=true;
					}
				}
				if(flag) {
					new JOptionPane().showMessageDialog(null,"����ɾ��δ�黹��ͼ��");
				}
				else {
					for (int i=0;i<r.length;i++) {
						String bookid=(String) tab.getValueAt(r[i],4);
						String readerid=(String) tab.getValueAt(r[i], 0);
						new BorrowDaoImpl().delete(bookid,readerid);
					}
					new JOptionPane().showMessageDialog(null,"ɾ���ɹ�");
					try {
						fresh();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}//end of but2
		if(e.getSource()==but3) {
			try {
				noReturn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but4) {
			String data=textField.getText();
			try {
				search(data);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void mouse() {
		tab.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// System.out.println("6666");
					// String text=(String) tab.getValueAt(tab.getSelectedRow(),
					// tab.getSelectedColumn());
					int row = tab.getSelectedRow();
					int col = tab.getSelectedColumn();
					JOptionPane jop = new JOptionPane();
					String newText=null;
					newText = jop.showInputDialog(null, "�޸�Ϊ��");
					if (newText!=null) {
						String bookid = null;
						String readerid=null;
						String columname = null;
						ResultSet rs = new BorrowDaoImpl().getAll();
						try {
							ResultSetMetaData rsmd = rs.getMetaData();
							rs.absolute(row + 1);
							bookid = rs.getString(5);
							readerid=rs.getString(1);
							columname = rsmd.getColumnName(col + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int state = new BorrowDaoImpl().update(bookid,readerid, newText, columname);
						if (state == 1) {
							new JOptionPane().showMessageDialog(null, "�޸ĳɹ�");
							try {
								fresh();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							new JOptionPane().showMessageDialog(null, "�޸�ʧ��");
						}
					} 
				}//end of doubleclick
			}//end of mouseClicked(MouseEvent e)
		});//
	}
}
