package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.BookDaoImpl;
import Dao.BookTypeDaoImpl;

public class AddBook extends JFrame implements ActionListener{
	BookManage father;
	ResultSet rs=new BookTypeDaoImpl().getAll();
	private JButton ok;
	private JButton cancel;
	private JTextField isbn;
	private JTextField name;
	private JTextField author;
	private JTextField num;
	private JTextField pdate;
	private JTextField publish;//
	private JTextField price;
	private JComboBox type;
	AddBook(String s,BookManage f) throws SQLException{
		super(s);
		father=f;
		initialize();
	}
	
	public void initialize() throws SQLException {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Vector typeData=new Vector();
		while (rs.next()) {
			//System.out.println(rs.getString(1));
			String d=rs.getString(2);
			typeData.add(d);
		}
		
		this.setBounds(400, 250, 401, 231);
		this.getContentPane().setLayout(null);
		ok = new JButton("确认");
		ok.setBounds(76, 155, 82, 31);
		this.getContentPane().add(ok);
		ok.addActionListener(this);
		
		cancel = new JButton("取消");
		cancel.setBounds(213, 155, 82, 31);
		this.getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		JLabel label = new JLabel("ISBN");
		label.setBounds(20, 8, 36, 21);
		this.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("书名");
		label_1.setBounds(205, 8, 36, 21);
		this.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("作者");
		label_2.setBounds(21, 40, 36, 21);
		this.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("出版社");
		label_3.setBounds(14, 71, 47, 21);
		this.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("出版日期");
		label_4.setBounds(10, 106, 58, 21);
		this.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("单价");
		label_5.setBounds(205, 40, 36, 21);
		this.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("数量");
		label_6.setBounds(205, 73, 36, 21);
		this.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("类型");
		label_7.setBounds(205, 105, 36, 21);
		this.getContentPane().add(label_7);
		
		isbn = new JTextField();
		isbn.setBounds(66, 8, 103, 21);
		this.getContentPane().add(isbn);
		isbn.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(251, 8, 103, 21);
		this.getContentPane().add(name);
		
		author = new JTextField();
		author.setColumns(10);
		author.setBounds(67, 40, 103, 21);
		this.getContentPane().add(author);
		
		num = new JTextField();
		num.setColumns(10);
		num.setBounds(251, 73, 103, 21);
		this.getContentPane().add(num);
		
		type = new JComboBox(typeData);
		type.setBounds(251, 105, 103, 23);
		this.getContentPane().add(type);
		
		pdate = new JTextField();
		pdate.setColumns(10);
		pdate.setBounds(66, 106, 103, 21);
		this.getContentPane().add(pdate);
		
		publish = new JTextField();
		publish.setColumns(10);
		publish.setBounds(66, 72, 103, 21);
		this.getContentPane().add(publish);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(251, 40, 103, 21);
		this.getContentPane().add(price);
		
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = null;
			try {
				date = sdf.parse(pdate.getText());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				new JOptionPane().showMessageDialog(null,e1.getMessage());
			}
			java.sql.Date dd=new java.sql.Date(date.getTime());
			float p=Float.parseFloat(price.getText());
			int n=Integer.parseInt(num.getText());
			String t=(String) type.getSelectedItem();
			int ti=0;
			try {
				rs.beforeFirst();
				while (rs.next()) {
					if(rs.getString(2).equals(t)) {
						ti=rs.getInt(1);
						break;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int state=new BookDaoImpl().add(isbn.getText(), name.getText(), author.getText(), publish.getText(), dd, p, ti, n);
			if(state==1) {
				new JOptionPane().showMessageDialog(null,"添加成功");
				try {
					father.fresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
			}
			else {
				new JOptionPane().showMessageDialog(null,"添加失败");
			}
		}
		if(e.getSource()==cancel) {
			this.dispose();
		}
	}
}
