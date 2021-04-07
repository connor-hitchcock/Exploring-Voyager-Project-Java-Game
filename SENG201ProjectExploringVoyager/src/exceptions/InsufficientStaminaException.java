package exceptions;

public class InsufficientStaminaException extends IllegalArgumentException{
	
	public InsufficientStaminaException(String message) {
		super(message);
	}
}
