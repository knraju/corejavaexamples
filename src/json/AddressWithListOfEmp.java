package json;

import java.util.List;

public class AddressWithListOfEmp {
	
	String street;
	String state;
	List<EmpJson> empJsonList;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<EmpJson> getEmpJsonList() {
		return empJsonList;
	}
	public void setEmpJsonList(List<EmpJson> empJsonList) {
		this.empJsonList = empJsonList;
	}
	
	

}
