package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.BookTypeDaoImpl;
import Dao.ReaderTypeDaoImpl;

public class AddReaderType extends JFrame implements ActionListener{
	ImforManage father;
	private JButton ok;
	private JButton cancel;
	private JTextField id;
	private JTextField name;
	private JTextField num;
	
	AddReaderType(String s,ImforManage f){
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
		
		JLabel lblBookid = new JLabel("ID");
		lblBookid.setBounds(92, 10, 47, 21);
		this.getContentPane().add(lblBookid);
		
		JLabel label_4 = new JLabel("借阅数量");
		label_4.setBounds(81, 72, 58, 21);
		this.getContentPane().add(label_4);
		
		JLabel lblid = new JLabel("类型");
		lblid.setBounds(92, 41, 47, 21);
		this.getContentPane().add(lblid);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(149, 10, 133, 21);
		this.getContentPane().add(id);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(149, 41, 133, 21);
		this.getContentPane().add(name);
		
		num = new JTextField();
		num.setColumns(10);
		num.setBounds(149, 72, 133, 21);
		this.getContentPane().add(num);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok) {
			int typeid=Integer.parseInt(id.getText());
			int n=Integer.parseInt(num.getText());
			int state=new ReaderTypeDaoImpl().add(typeid, name.getText(), n);
			if(state==1) {
				new JOptionPane().showMessageDialog(null,"添加成功");
				try {
					father.fresh2();
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
