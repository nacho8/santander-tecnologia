package santander.tecnologia.challenge.ws.response;

public class MeetUpObtainAmountBeerResponse {
	
	private String status;
	private int amountBeer;
	
	public MeetUpObtainAmountBeerResponse() {
		super();
	}
	
	public MeetUpObtainAmountBeerResponse(String status, int amountBeer) {
		super();
		this.status = status;
		this.amountBeer = amountBeer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAmountBeer() {
		return amountBeer;
	}
	public void setAmountBeer(int amountBeer) {
		this.amountBeer = amountBeer;
	}

	

}
