package lib;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LibraryR extends JFrame implements ActionListener{
	private String id;
	private JButton but1;
	private JButton but2;
	private JButton but5;
	private Panel panel;
	LibraryR(String str,String id){
		super(str);
		this.id=id;
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
		
		but1 = new JButton("Í¼Êé½èÔÄ");
		but1.setBounds(10, 30, 130, 46);
		this.getContentPane().add(but1);
		but1.addActionListener(this);
		
		but2 = new JButton("Í¼Êé¹é»¹");
		but2.setBounds(10, 90, 130, 46);
		this.getContentPane().add(but2);
		but2.addActionListener(this);
	
		
		but5 = new JButton("ÍË³öµÇÂ¼");
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
				new BorrowBook("Í¼Êé½èÔÄ",id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but2) {
			try {
				new ReturnBook("Í¼Êé¹é»¹",id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==but5) {
			new Login("µÇÂ½");
			this.dispose();
		}
	}

}
