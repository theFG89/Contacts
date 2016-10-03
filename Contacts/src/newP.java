import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class newP extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel panelNewInfo = new JPanel();
	
	JFrame mainFrame = new JFrame("Nuovo Contatto");
	
	JButton save = new JButton("Salva");
	JButton cancel = new JButton("Annulla");
	File myfile = new File("informazioni.txt");
	JTextField newName;
	JTextField newSurname;
	JTextField newAddress;
	JTextField newTel;
	JTextField newAge;
	Vector<person> VP; 
	private saving		savecontact;
	private cancel		back;

	
	public newP(Vector<person> VV){

		VP=VV; 
		
		mainFrame.setSize(400,400);
		mainFrame.setLocation(600, 300);		
		panelNewInfo.setLayout(new GridLayout(12,1));
		panelNewInfo.add(new JLabel("Nome"));
		newName = new JTextField("");
		panelNewInfo.add(newName);
		panelNewInfo.add(new JLabel("Cognome"));
		newSurname = new JTextField("");
		panelNewInfo.add(newSurname);
		panelNewInfo.add(new JLabel("Indirizzo"));
		newAddress = new JTextField("");
		panelNewInfo.add(newAddress);
		panelNewInfo.add(new JLabel("Telefono"));
		newTel = new JTextField("");
		panelNewInfo.add(newTel);
		panelNewInfo.add(new JLabel("Età"));
		newAge = new JTextField("");
		panelNewInfo.add(newAge);
		panelNewInfo.add(save, BorderLayout.CENTER);
		panelNewInfo.add(cancel, BorderLayout.CENTER);
		mainFrame.getContentPane().add(panelNewInfo);

		
		
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		savecontact = new saving();
		save.addActionListener(savecontact);
		back = new cancel();
		cancel.addActionListener(back);
	}
	
	private class saving implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( newName.getText().length()==0 || newSurname.getText().length()==0 || newAddress.getText().length()==0 || newTel.getText().length()==0 || newAge.getText().length()==0 || checkAge(newAge.getText().toString())==false)
			{
				JFrame frameInfo = new JFrame("Errore");
				JOptionPane.showMessageDialog(frameInfo, "Ricontrollare i dati inseriti");
			}
			else if( checkValueInsert()==false){
				JFrame frameInfo = new JFrame("Errore");
				JOptionPane.showMessageDialog(frameInfo, "Contatto già esistente");
			}
			else{
				PrintStream ps;
		    	try {
		    		 
					 ps = new PrintStream(new FileOutputStream("informazioni.txt", true));
					 if (!myfile.exists())
						 ps.println();
     			     ps.print(newName.getText().toString()+";"+newSurname.getText().toString()+";"+
				       		    newAddress.getText().toString()+";"+newTel.getText().toString()+";"+newAge.getText().toString());
     			     ps.close();
     				WindowEvent close = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
     				mainFrame.dispatchEvent(close);
     				 new mainLabel();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
				
			
		}
		
	}
	
	private class cancel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			WindowEvent close = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
			mainFrame.dispatchEvent(close);
			 new mainLabel();
			
		}
		
	}
	
	private boolean checkAge(String s){
		
		for(int i=0;i<s.length();i++){
			if( (int)s.charAt(i) <48 || (int)s.charAt(i) >57)
				return false;
		}
		return true;
	}
	
	private boolean checkValueInsert(){

		
		for (int k=0;k<=VP.size()-1;k++){
			if (VP.elementAt(k).getName().toString().equals(newName.getText().toString())  && 
					VP.elementAt(k).getSurname().toString().equals(newSurname.getText().toString())  && 
					VP.elementAt(k).getTel().toString().equals(newTel.getText().toString()) &&
					VP.elementAt(k).getAddress().toString().equals(newAddress.getText().toString())  &&
					String.valueOf(VP.elementAt(k).getAge()).equals(newAge.getText().toString()))
			  return false;	
		}
		
		return true;
	}


}
