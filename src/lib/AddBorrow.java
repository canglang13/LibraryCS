package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.BorrowDaoImpl;

public class AddBorrow extends JFrame implements ActionListener{
	BorrowManage father;
	private JTextField readerid;
	private JTextField bookid;
	private JTextField bdate;
	private JTextField rdate;
	private JButton ok;
	private JButton cancel;
	
	AddBorrow(String s,BorrowManage f) throws SQLException{
		super(s);
		father=f;
		initialize();
	}
	
	public void initialize() {
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
		
		JLabel lblBookid = new JLabel("BOOKID");
		lblBookid.setBounds(92, 10, 47, 21);
		this.getContentPane().add(lblBookid);
		
		JLabel label_4 = new JLabel("\u501F\u51FA\u65E5\u671F");
		label_4.setBounds(81, 72, 58, 21);
		this.getContentPane().add(label_4);
		
		JLabel lblid = new JLabel("\u8BFB\u8005ID");
		lblid.setBounds(92, 41, 47, 21);
		this.getContentPane().add(lblid);
		
		JLabel label = new JLabel("\u5F52\u8FD8\u65E5\u671F");
		label.setBounds(81, 103, 58, 21);
		this.getContentPane().add(label);
		
		bookid = new JTextField();
		bookid.setColumns(10);
		bookid.setBounds(149, 10, 133, 21);
		this.getContentPane().add(bookid);
		
		readerid = new JTextField();
		readerid.setColumns(10);
		readerid.setBounds(149, 41, 133, 21);
		this.getContentPane().add(readerid);
		
		bdate = new JTextField();
		bdate.setColumns(10);
		bdate.setBounds(149, 72, 133, 21);
		this.getContentPane().add(bdate);
		
		rdate = new JTextField();
		rdate.setColumns(10);
		rdate.setBounds(149, 103, 133, 21);
		this.getContentPane().add(rdate);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok) {
			String bid=bookid.getText();
			String rid=readerid.getText();
			String bd=bdate.getText();
			String rd=rdate.getText();
			if(bid.equals("")||rid.equals("")||bd.equals("")) {
				new JOptionPane().showMessageDialog(null,"BOOKID、读者ID、借出日期不能为空");
			}
			else {
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date bdt = null;
				java.util.Date rdt = null;
				try {
					bdt = sdf.parse(bd);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					new JOptionPane().showMessageDialog(null,e1.getMessage());
				}
				java.sql.Date bdte=new java.sql.Date(bdt.getTime());
				java.sql.Date rdte=null;
				if(!rd.equals("")) {
					try {
						rdt = sdf.parse(rd);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						new JOptionPane().showMessageDialog(null,e1.getMessage());
					}
					rdte=new java.sql.Date(rdt.getTime());
				}
				int state=new BorrowDaoImpl().add(rid,bdte,rdte,bid);
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
		}//end of ok
		if(e.getSource()==cancel) {
			this.dispose();
		}
	}
}
