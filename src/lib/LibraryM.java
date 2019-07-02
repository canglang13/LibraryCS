package lib;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LibraryM extends JFrame implements ActionListener{
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private Panel panel;
	LibraryM(String str){
		super(str);
		initialize();
	}
	public void initialize() {
		this.setBounds(400, 250, 628, 418);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.getContentPane().setLayout(null);
		
		but1 = new JButton("图书信息管理");
		but1.setBounds(10, 30, 130, 46);
		this.getContentPane().add(but1);
		but1.addActionListener(this);
		
		but2 = new JButton("借阅信息管理");
		but2.setBounds(10, 90, 130, 46);
		this.getContentPane().add(but2);
		but2.addActionListener(this);
		
		but3 = new JButton("用户信息管理");
		but3.setBounds(10, 151, 130, 46);
		this.getContentPane().add(but3);
		but3.addActionListener(this);
		
		but4 = new JButton("基础信息维护");
		but4.setBounds(10, 213, 130, 46);
		this.getContentPane().add(but4);
		but4.addActionListener(this);
		
		but5 = new JButton("退出登录");
		but5.setBounds(10, 276, 130, 46);
		this.getContentPane().add(but5);
		but5.addActionListener(this);
		
		panel = new Panel();
		panel.setBounds(132, 10, 474, 363);
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==but1) {
			try {
				new BookManage("图书信息管理");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but2) {
			try {
				new BorrowManage("借阅信息管理");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but3) {
			try {
				new ReaderManage("读者信息管理");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but4) {
			try {
				new ImforManage("读者信息管理");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but5) {
			new Login("登陆");
			this.dispose();
		}
	}

}
