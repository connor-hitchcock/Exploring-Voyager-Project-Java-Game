package exceptions;


public class GameOverException extends IllegalArgumentException{
	
	public GameOverException(String message) {
		super(message);
	}
}
