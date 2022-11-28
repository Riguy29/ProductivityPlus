package modules.contacts;

public class Contact {
	private String pathToPicture;
	private String name;
	private String phoneNumber;
	private String email;
	private String group;
	private String DOB;
	
	


	public Contact(String pathToPicture, String name, String phoneNumber, String email, String group, String dOB) {
		super();
		this.pathToPicture = pathToPicture;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.group = group;
		DOB = dOB;
	}
	
	
	public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		DOB = dOB;
	}


	public String getPathToPicture() {
		return pathToPicture;
	}
	public void setPathToPicture(String pathToPicture) {
		this.pathToPicture = pathToPicture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
}
