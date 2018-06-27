package ayds.dictionary.delta.model.exceptions;

import java.util.Map;

import ayds.dictionary.delta.model.Source;
import ayds.dictionary.delta.model.listeners.ErrorListener;

public interface ExceptionHandler {
    void setListener (ErrorListener e);
    boolean thereIsConnection(Map<Source,Exception> sourceExceptionMap);
    void appNotConnected();
    String getExceptionMessage(Exception e);
}
