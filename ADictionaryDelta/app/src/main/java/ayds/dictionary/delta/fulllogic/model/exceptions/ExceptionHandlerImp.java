package ayds.dictionary.delta.fulllogic.model.exceptions;

import ayds.dictionary.delta.fulllogic.model.listeners.ErrorListener;

class ExceptionHandlerImp implements ExceptionHandler {
    private ErrorListener errorListener;

    public void handleException(Exception e){
        String exceptionMessage;

        if(e instanceof CustomizedException)
            exceptionMessage = e.getMessage();

        else
            exceptionMessage = "An unexpected error occurred. Please try your request again later";

        notifyListener(exceptionMessage);
    }

    private void notifyListener(String exceptionMessage){
        if(errorListener!=null)
            errorListener.didErrorOcurr(exceptionMessage);
    }

    public void setListener(ErrorListener errorListener){
        this.errorListener=errorListener;
    }
}
