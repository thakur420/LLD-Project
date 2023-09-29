package Exception;

public class InvalidNumberOfPlayerException extends Exception {
    public InvalidNumberOfPlayerException() {
    }
    public InvalidNumberOfPlayerException(String message) {
        super(message);
    }
}
