package santander.tecnologia.challenge.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class MeetUp {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meetUp")
	@JsonIgnore
	private Set<MeetUpUsers> meetUpUser;
	
	private LocalDate meetUpDate;
	
	private String direction;

	
	public LocalDate getMeetUpDate() {
		return meetUpDate;
	}

	public void setMeetUpDate(LocalDate meetUpDate) {
		this.meetUpDate = meetUpDate;
	}

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	

}
