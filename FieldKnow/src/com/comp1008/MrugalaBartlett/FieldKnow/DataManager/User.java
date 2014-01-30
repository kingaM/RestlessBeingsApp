/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

public class User {

	private long id;
	private String user, password, firstName, lastName, email;

	public User(Long id, String firstName, String lastName, String email,
			String user, String password) {
		this.id = id;
		this.user = user;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	private boolean checkEmail(String email) {
		return !(email.contains("@") || email.contains("."));
	}

	public String checkInput(String pass2) {
		if (password.equals("") | pass2.equals("") | firstName.equals("")
				| lastName.equals("") | email.equals("") | user.equals("")) {
			return "Please complete all fields!";
		}
		if (!password.equals(pass2)) {
			return "Passwords do not match!";
		}
		if ((password.length() < 6) | (password.length() > 12)) {
			return "Passwords must be 6-12 characters!";
		}
		if ((user.length() < 6) | (user.length() > 12)) {
			return "Username must be 6-12 characters!";
		}
		if (checkEmail(email)) {
			return "Email is incorrect";
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
