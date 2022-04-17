package fr.mns.tprabbit.rabbitmq_consumer.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data shared when a user is created or updated
 * @author Alex
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEventData extends EventData{
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "UserEventData [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}
	
	

}
