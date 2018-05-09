package ayds.dictionary.delta.model.exceptions;

public class ConnectionErrorException extends CustomizedException {
    private static final String message = "There was an error with the connection";

    public ConnectionErrorException(){
        super(message);
    }
}
