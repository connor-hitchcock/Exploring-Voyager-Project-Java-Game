package exceptions;

public class TakenDamageException extends IllegalArgumentException{
	
	public TakenDamageException(String message) {
		super(message);
	}
}
