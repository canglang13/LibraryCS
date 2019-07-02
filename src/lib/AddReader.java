package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.BookTypeDaoImpl;
import Dao.ReaderDaoImpl;
import Dao.ReaderTypeDaoImpl;

public class AddReader extends JFrame implements ActionListener{
	ReaderManage father;
	ResultSet rs=new ReaderTypeDaoImpl().getAll();
	private JButton ok;
	private JButton cancel;
	private JTextField readerid;
	private JTextField name;
	private JTextField age;
	private JTextField phone;
	private JTextField dept;
	private JTextField userid;
	private JComboBox type;
	AddReader(String s,ReaderManage f) throws SQLException{
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
		
		JLabel label = new JLabel("读者ID");
		label.setBounds(20, 8, 47, 21);
		this.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("姓名");
		label_1.setBounds(205, 8, 47, 21);
		this.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("年龄");
		label_2.setBounds(21, 40, 47, 21);
		this.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("院系");
		label_3.setBounds(21, 71, 47, 21);
		this.getContentPane().add(label_3);
		
		
		JLabel label_5 = new JLabel("电话");
		label_5.setBounds(205, 40, 47, 21);
		this.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("用户ID");
		label_6.setBounds(205, 73, 47, 21);
		this.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("类型");
		label_7.setBounds(205, 105, 47, 21);
		this.getContentPane().add(label_7);
		
		readerid = new JTextField();
		readerid.setBounds(66, 8, 103, 21);
		this.getContentPane().add(readerid);
		readerid.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(251, 8, 103, 21);
		this.getContentPane().add(name);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(67, 40, 103, 21);
		this.getContentPane().add(age);
		
		userid = new JTextField();
		userid.setColumns(10);
		userid.setBounds(251, 73, 103, 21);
		this.getContentPane().add(userid);
		
		type = new JComboBox(typeData);
		type.setBounds(251, 105, 103, 23);
		this.getContentPane().add(type);
		
		
		dept = new JTextField();
		dept.setColumns(10);
		dept.setBounds(66, 72, 103, 21);
		this.getContentPane().add(dept);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(251, 40, 103, 21);
		this.getContentPane().add(phone);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok) {
			int aage=Integer.parseInt(age.getText());
			int typeid=0;
			String t=(String) type.getSelectedItem();
			try {
				rs.beforeFirst();
				while (rs.next()) {
					if(rs.getString(2).equals(t)) {
						typeid=rs.getInt(1);
						break;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int state=new ReaderDaoImpl().add(readerid.getText(), name.getText()
					, aage, phone.getText(), dept.getText(), typeid, userid.getText());
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
			
		}//end of ok
		if(e.getSource()==cancel) {
			this.dispose();
		}
	}
}
