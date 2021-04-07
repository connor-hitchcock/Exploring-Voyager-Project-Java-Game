package exceptions;

public class InsufficientFuelException extends IllegalArgumentException{
	
	public InsufficientFuelException(String message) {
		super(message);
	}
}
