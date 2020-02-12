package santander.tecnologia.challenge.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private Set<MeetUpUsers> meetUpUser;
	
	private String firstName;
	
	
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User() {
		super();
	}

	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<MeetUpUsers> getMeetUpUser() {
		return meetUpUser;
	}

	public void setMeetUpUser(Set<MeetUpUsers> meetUpUser) {
		this.meetUpUser = meetUpUser;
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
	
	

}
