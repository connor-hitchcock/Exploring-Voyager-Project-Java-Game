package exceptions;

public class IncorrectCrewNumberException extends IllegalArgumentException {
	
	public IncorrectCrewNumberException(String message) {
		super(message);
	}
}