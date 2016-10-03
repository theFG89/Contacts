import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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


public class editP {
	
	JPanel panelNewInfo = new JPanel();
	
	JFrame mainFrame = new JFrame("Nuovo Contatto");
	
	JButton save = new JButton("Salva");
	JButton cancel = new JButton("Annulla");
	
	JTextField newName;
	JTextField newSurname;
	JTextField newAddress;
	JTextField newTel;
	JTextField newAge;
	private saving		savecontact;
	private cancel		back;
	Vector<person> VP;
	int positionToModify=-1;

	public editP(String n, String c,String t,Vector<person> VV){

		mainFrame.setSize(400,400);

		mainFrame.setLocation(600, 300);
		
		panelNewInfo.setLayout(new GridLayout(12,1));

		VP=VV;
		int k=VV.size()-1;

		while (k!=-1){

			if(VV.elementAt(k).getName().toString()== n && VV.elementAt(k).getSurname().toString()==c && VV.elementAt(k).getTel().toString()==t){

				panelNewInfo.setLayout(new GridLayout(12,1));
				
				panelNewInfo.add(new JLabel("Nome"));
				newName = new JTextField(n);
				panelNewInfo.add(newName);
				
				panelNewInfo.add(new JLabel("Cognome"));
				newSurname = new JTextField(c);
				panelNewInfo.add(newSurname);
				
				panelNewInfo.add(new JLabel("Indirizzo"));
				newAddress = new JTextField(VP.elementAt(k).getAddress().toString());
				panelNewInfo.add(newAddress);
				
				panelNewInfo.add(new JLabel("Telefono"));
				newTel = new JTextField(VP.elementAt(k).getTel().toString());
				panelNewInfo.add(newTel);
				
				panelNewInfo.add(new JLabel("Età"));
				newAge = new JTextField(String.valueOf(VV.elementAt(k).getAge()));
				panelNewInfo.add(newAge);
				positionToModify=k;
			}	;
		k--;
		}
				
		
		
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
		public void actionPerformed(ActionEvent arg0) {
			if(newName.getText().length()==0 || newSurname.getText().length()==0 || newAddress.getText().length()==0 || newTel.getText().length()==0 || newAge.getText().length()==0 || checkAge(newAge.getText().toString())==false)
			{
				JFrame frameInfo = new JFrame("Errore");
				JOptionPane.showMessageDialog(frameInfo, "Ricontrollare i dati inseriti");
			}
			else{
			VP.elementAt(positionToModify).setName(newName.getText().toString());
			VP.elementAt(positionToModify).setSurname(newSurname.getText().toString());
			VP.elementAt(positionToModify).setAddress(newAddress.getText().toString());
			VP.elementAt(positionToModify).setTel(newTel.getText().toString());
			VP.elementAt(positionToModify).setAge(Integer.parseInt(newAge.getText().toString()));
			
			

			PrintStream ps;
	    	try {
				 ps = new PrintStream(new FileOutputStream("informazioni.txt"));

					for(int k=0;k<=VP.size()-1;k++){
						ps.println(VP.elementAt(k).getName().toString()+";"+VP.elementAt(k).getSurname().toString()+";"+VP.elementAt(k).getAddress().toString()+";"+
								VP.elementAt(k).getTel().toString()+";"+String.valueOf(VP.elementAt(k).getAge()));
					   
					}
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
	
	private boolean checkAge(String s){
		
		for(int i=0;i<s.length();i++){
			if( (int)s.charAt(i) <48 || (int)s.charAt(i) >57)
				return false;
		}
		return true;
	}
	
	private class cancel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			WindowEvent close = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
			mainFrame.dispatchEvent(close);
			new mainLabel();
			
		}
		
	}
	
}
