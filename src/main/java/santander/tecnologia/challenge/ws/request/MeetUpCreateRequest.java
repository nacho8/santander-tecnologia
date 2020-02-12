package santander.tecnologia.challenge.ws.request;

import java.time.LocalDate;

public class MeetUpCreateRequest {
	
	private String direction;
	private Long userId;
	private LocalDate dateMeetUp;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDateMeetUp() {
		return dateMeetUp;
	}

	public void setDateMeetUp(LocalDate dateMeetUp) {
		this.dateMeetUp = dateMeetUp;
	}
	
	

}
