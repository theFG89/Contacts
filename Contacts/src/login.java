import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login {
	
	JTextField us;
	JPasswordField psw;
	JButton access;
	utente ut;
	JFrame frame;
	public login(){
		
	
		frame = new JFrame("Accesso");
		frame.setSize(300, 150);
		frame.setLocation(600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);


		us = new JTextField(30);
		us.setBounds(100, 10, 160, 25);
		panel.add(us);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);


		psw = new JPasswordField(30);
		psw.setBounds(100, 40, 160, 25);
		panel.add(psw);

		access = new JButton("login");
		access.setBounds(10, 80, 80, 25);
		panel.add(access);


		frame.setVisible(true);
		ut = new utente();

		DoLogin lg = new DoLogin();
		access.addActionListener(lg);
	}
	
	
	private class  DoLogin implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(ut.getUser().equals(us.getText().toString())  &&
			   ut.getPassword().equals(psw.getText().toString())){		
				frame.setVisible(false);
				new mainLabel();
			}
			else
				{
				
					JFrame frameInfo = new JFrame("Errore");
					JOptionPane.showMessageDialog(frameInfo, "Username e/o password errati ");
				}
			
		}	
			
		
	}

}
