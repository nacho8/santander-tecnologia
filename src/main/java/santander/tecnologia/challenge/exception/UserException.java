package santander.tecnologia.challenge.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = -3608047774876421293L;

	public UserException(String msg, Exception e) {
		super(msg, e);
	}

	public UserException(String msg) {
		super(msg);
	}
}
