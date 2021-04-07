package exceptions;

public class TooManyCharactersException extends IllegalArgumentException{
	
	public TooManyCharactersException(String message) {
		super(message);
	}
}
