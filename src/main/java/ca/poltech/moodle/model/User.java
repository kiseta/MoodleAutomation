package ca.poltech.moodle.model;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String country;
	private String details;
	private boolean mustCreatePassword;
	private String password;
	
	public User() {
		super();
	}
	
	public User(String id, String firstName, String lastName, String email, String city, String country, String details,
			boolean mustCreatePassword,String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.country = country;
		this.details = details;
		this.mustCreatePassword = mustCreatePassword;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public boolean isMustCreatePassword() {
		return mustCreatePassword;
	}
	public void setMustCreatePassword(boolean mustCreatePassword) {
		this.mustCreatePassword = mustCreatePassword;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", city="
				+ city + ", country=" + country + ", details=" + details + ", mustCreatePassword=" + mustCreatePassword
				+ ", password=" + password + "]";
	}
	
}
