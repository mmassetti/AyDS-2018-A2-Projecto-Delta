package ayds.dictionary.delta.model.exceptions;

public class EmptyResultException extends CustomizedException {
    private static final String message = "There is no result for the search";

    public EmptyResultException(){
        super(message);
    }
}
