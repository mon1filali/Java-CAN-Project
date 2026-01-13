package Logic.Exceptions;

public class ExceededNumberOfPlayersException extends Exception {
    public ExceededNumberOfPlayersException(String error) {
        super(error);
    }
}
