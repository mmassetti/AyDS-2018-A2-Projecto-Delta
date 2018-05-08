package ayds.dictionary.delta.fulllogic.model.exceptions;

public class BadFormatException extends CustomizedException {
    private static final String message = "Bad word's format. Please try again";

    public BadFormatException(){
        super(message);
    }
}
