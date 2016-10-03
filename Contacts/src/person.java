
public class person {

	String name;
	String surname;
	String address;
	String tel;
	int age;

public person (String n, String s, String add, String t, int a){
	this.name=n;
	this.surname=s;
	this.address=add;
	this.tel=t;
	this.age=a;
}
public person (String n, String s, String t){
	this.name=n;
	this.surname=n;
	this.tel=t;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public person addPerson(person p){
	return p;
}


}


