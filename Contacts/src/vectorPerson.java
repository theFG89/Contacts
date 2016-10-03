import java.util.Vector;

public class vectorPerson {

	private Vector<person> p;
	
	public vectorPerson(){
		p = new Vector<person>();
	}
	
	public void insertP (person a){
		if(!count(a))
			p.add(a);
	}
	public boolean count(person a){
		return p.contains(a);
	}
	
	
	public void deletePerson (person a){
		p.removeElement(a);
	}
	
	public void printVector(){
		System.out.println(p);
	}
}
