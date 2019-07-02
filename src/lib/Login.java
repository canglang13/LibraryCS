package lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dao.ManageUserDaoImpl;
import Dao.ReaderUserDaoImpl;

public class Login extends JFrame implements ActionListener{
	private int flag=0;
	private String id;
	private char[] password;
	private JTextField id_field=new JTextField(null);
	private JPasswordField password_field=new JPasswordField(null);
	private JButton manage=new JButton("π‹¿Ì‘±µ«¬Ω");
	private JButton reader=new JButton("∂¡’ﬂµ«¬Ω");
	private JLabel lab1= new JLabel("’À∫≈");
	private JLabel lab2= new JLabel("√‹¬Î");
	
	Login(String s){
		super(s);
		initialize();
	}

	public void initialize() {
		this.setBounds(500,300,401,231);
		this.getContentPane().setLayout(null);
		id_field.setBounds(106, 32, 239, 31);
		password_field.setBounds(104, 73, 241, 31);
		manage.setBounds(62, 141, 102, 31);
		reader.setBounds(195, 141, 102, 31);
		lab1.setBounds(40, 36, 62, 23);
		lab2.setBounds(40, 81, 54, 15);
		this.add(lab1);
		this.add(lab2);
		this.add(id_field);
		this.add(password_field);
		this.add(manage);
		this.add(reader);
		manage.addActionListener(this);
		reader.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean chargeM() throws SQLException {
		String ps=String.valueOf(password);
		ResultSet rs=new ManageUserDaoImpl().getAll();
		while (rs.next()) {
			if(rs.getString(1).equals(id)&&rs.getString(3).equals(ps)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean chargeR() throws SQLException {
		String ps=String.valueOf(password);
		ResultSet rs=new ReaderUserDaoImpl().getAll();
		while (rs.next()) {
			if(rs.getString(1).equals(id)&&rs.getString(3).equals(ps)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==manage) {
			id=id_field.getText();
			password=password_field.getPassword();
			try {
				if(chargeM()) {
					new LibraryM("Õº Èπ‹¿ÌœµÕ≥");
					this.dispose();
				}
				else {
					new JOptionPane().showMessageDialog(null,"’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==reader) {
			id=id_field.getText();
			password=password_field.getPassword();
			try {
				if(chargeR()) {
					new LibraryR(" ˝◊÷Õº Èπ›",id);
					this.dispose();
				}
				else {
					new JOptionPane().showMessageDialog(null,"’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
