package santander.tecnologia.challenge.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MeetUpUsers {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_up_id")
    private MeetUp meetUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private Date userMeetUpDate;
    
    private boolean userAttented;

    
    
	public boolean isUserAttented() {
		return userAttented;
	}

	public void setUserAttented(boolean userAttented) {
		this.userAttented = userAttented;
	}

	public Date getUserMeetUpDate() {
		return userMeetUpDate;
	}

	public void setUserMeetUpDate(Date userMeetUpDate) {
		this.userMeetUpDate = userMeetUpDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetUp getMeetUp() {
		return meetUp;
	}

	public void setMeetUp(MeetUp meetUp) {
		this.meetUp = meetUp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    

}
