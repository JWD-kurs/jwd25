package jwd.wafepa.web.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegistrationDTO {
	private Long id;
	@NotEmpty
	@Email(message="E-mail must be valid")
	private String email;
	@NotEmpty
	@Size(min=3,max=30)
	private String firstname;
	@NotEmpty
	@Size(min=3,max=30)
	private String lastname;
	@NotEmpty
	@Size(min=8,max=30)	
	private String password;
	@NotEmpty
	@Size(min=8,max=30)
	private String passwordConfirm;
	

	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
