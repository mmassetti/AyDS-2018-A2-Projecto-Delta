package ayds.dictionary.delta.model.exceptions;

import java.util.Map;

import ayds.dictionary.delta.model.Source;
import ayds.dictionary.delta.model.listeners.ErrorListener;

class ExceptionHandlerImp implements ExceptionHandler {
    private ErrorListener errorListener;


    public boolean thereIsConnection(Map<Source, Exception> sourceExceptionMap) {
        boolean appConnected = false;
        for (Exception e : sourceExceptionMap.values()) {
            if (!(e instanceof ConnectionErrorException)) {
                appConnected = true;
            }
        }
        return appConnected;
    }

    public void appNotConnected() {
        CustomizedException connectionException = new ConnectionErrorException();
        notifyListener(connectionException.getMessage());
    }

    public String getExceptionMessage(Exception e) {
        String exceptionMessage;
        if (e instanceof CustomizedException) {
            exceptionMessage = e.getMessage();
        } else {
            exceptionMessage = "An unexpected error occurred. Please try your request again later";
        }
        return exceptionMessage;
    }

    private void notifyListener(String exceptionMessage) {
        if (errorListener != null) {
            errorListener.didErrorOccur(exceptionMessage);
        }
    }

    public void setListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }
}
