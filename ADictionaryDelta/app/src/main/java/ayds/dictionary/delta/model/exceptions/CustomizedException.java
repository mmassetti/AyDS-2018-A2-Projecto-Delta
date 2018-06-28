package ayds.dictionary.delta.model.exceptions;

abstract class CustomizedException extends Exception {

    CustomizedException(String message) {
        super(message);
    }
}
