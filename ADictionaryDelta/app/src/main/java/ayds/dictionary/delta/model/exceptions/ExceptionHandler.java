package ayds.dictionary.delta.model.exceptions;

import ayds.dictionary.delta.model.listeners.ErrorListener;

public interface ExceptionHandler {
    void handleException(Exception e);
    void setListener (ErrorListener e);
}
