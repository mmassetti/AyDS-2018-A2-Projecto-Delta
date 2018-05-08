package ayds.dictionary.delta.fulllogic.model.exceptions;

import ayds.dictionary.delta.fulllogic.model.listeners.ErrorListener;

public interface ExceptionHandler {
    void handleException(Exception e);
    void setListener (ErrorListener e);
}
