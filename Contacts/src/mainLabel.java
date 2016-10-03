import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class mainLabel extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JButton newPeople = new JButton("Nuovo");
	JButton edit = new JButton("Modifica");
	JButton delete = new JButton("Elimina");
	File myfile = new File("informazioni.txt");
	BufferedWriter writer = null;
	PrintStream printer;
	Scanner reader;
	person P;
	JFrame f;
	JTable tableC;
	Vector<person> VP ;

	public mainLabel(){
		
		f=  new JFrame("Rubrica");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600,600);
		f.setLocation(600,300);
		JPanel panelPanelButton = new JPanel(new BorderLayout());
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		VP = new Vector<person>();
		//Creating jTable and insert value inside
		
				if(myfile.exists()){
		try {
			reader = new Scanner(myfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}


		while (reader.hasNextLine()) {

			
			String line2[] = reader.nextLine().split(";");
			
			if(line2.length>2){
			P = new person(line2[0].toString(),line2[1].toString(),line2[2].toString(),line2[3].toString(),
							Integer.valueOf(line2[4].toString()));

			
			VP.addElement(P);

			line2=null;
				}
			}
		reader.close();  	
			}
	
		DefaultTableModel tableContact = new DefaultTableModel(new Object[]{"Nome","Cognome","Telefono"}, 0){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			  };
			  
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		tableC = new JTable(tableContact);
		tableC.setModel(tableContact);
		
		int k=0;
		if(VP.isEmpty()){
			 
			 tableContact.insertRow(tableC.getRowCount(),new Object[]{"","","","",""});
		}
		else{
			while (k!=VP.size()){
				Object nameinRow[]={VP.elementAt(k).getName(),VP.elementAt(k).getSurname(),VP.elementAt(k).getTel()};
				tableContact.addRow(nameinRow);
				k++;
			}
		}

		JScrollPane tableContainer = new JScrollPane(tableC);
		panelTable.add(tableContainer, BorderLayout.CENTER);
		
		panelButton.add(newPeople);
        panelButton.add(edit);
        panelButton.add(delete);
        panelPanelButton.add(panelButton, BorderLayout.CENTER);
        f.add(panelPanelButton, BorderLayout.SOUTH);
		f.getContentPane().add(panelTable);
		f.pack();
		f.setVisible(true);
		
		openNewPeople();
		deletePeople();
		editPeople();


		f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                savingInformationToFile();
                e.getWindow().dispose();
            }
        });
		
		}

	
	
	
	public  void openNewPeople(){
		newPeople.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				new newP(VP);
				
			}
		});
		
	}
	
	public void deletePeople(){
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableC.getSelectedRow()==-1){
					JFrame frameInfo = new JFrame("Errore");
					JOptionPane.showMessageDialog(frameInfo, "Selezionare prima il contatto da eliminare");
				}
				else{
					//ottenere riga selezionata
					   int i = tableC.getSelectedRow();
					   DefaultTableModel model = (DefaultTableModel)tableC.getModel();
					   String valueCol1 = (String) model.getValueAt(i, 0);
					   String valueCol2 = (String) model.getValueAt(i, 1);
					   String valueCol3= (String)model.getValueAt(i, 2);
					int dialogResult = JOptionPane.showConfirmDialog (null, "Eliminare "+valueCol1+" "+ valueCol2+"?","Eliminazione",JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
					   if(i>=0){
						   deleteObject(valueCol1, valueCol2,valueCol3);
						   model.removeRow(i);
						   savingInformationToFile();
					   }

					   
					}
					if (dialogResult == JOptionPane.NO_OPTION){
						close();
					}
				}
				
			}
		});
	}
	
	
	public void deleteObject(String n,String c,String tel){
		
		int k=0;
		int elementToDelete=-1;
		while (k!=VP.size()){
			if(VP.elementAt(k).getName().toString()== n && VP.elementAt(k).getSurname().toString()==c && VP.elementAt(k).getTel().toString()==tel){
				elementToDelete=k;	
			}	
			k++;
			
		}
		if (elementToDelete!=-1)
			VP.removeElementAt(elementToDelete);
		
	}
	
    public void editPeople(){
    	edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableC.getSelectedRow()==-1){
					JFrame frameInfo = new JFrame("Errore");
					JOptionPane.showMessageDialog(frameInfo, "Selezionare prima il contatto da modificare");
				}
				else{
					int i = tableC.getSelectedRow();
					   DefaultTableModel model = (DefaultTableModel)tableC.getModel();
					   String nEdit = (String) model.getValueAt(i, 0);
					   String cEdit = (String) model.getValueAt(i, 1);
					   String tEdit = (String) model.getValueAt(i, 2);
					f.setVisible(false);
					 new editP(nEdit,cEdit,tEdit,VP);
				}
				
			}
		});
    }
    
    public void savingInformationToFile(){
    	FileOutputStream out;
    	PrintStream ps;
    	try{
    		out=new FileOutputStream("informazioni.txt");
    		ps = new PrintStream(out);
    		int k=0;
    		while (k!=VP.size()){

			ps.println(VP.elementAt(k).getName().toString()+";"+VP.elementAt(k).getSurname().toString()+";"+VP.elementAt(k).getAddress().toString()+";"+
			VP.elementAt(k).getTel().toString()+";"+String.valueOf(VP.elementAt(k).getAge()));
			k++;
			}
    		ps.close();
    	}catch (Exception e){
    		System.err.println("Errore scrittura su file  informazioni.txt");
    	}
    }
	
	private void close(){
		this.setVisible(false);
	}

	
}
