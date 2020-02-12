package santander.tecnologia.challenge.exception;

public class MeetUpException extends Exception {

	private static final long serialVersionUID = -3608047774876421293L;

	public MeetUpException(String msg, Exception e) {
		super(msg, e);
	}

	public MeetUpException(String msg) {
		super(msg);
	}
}
