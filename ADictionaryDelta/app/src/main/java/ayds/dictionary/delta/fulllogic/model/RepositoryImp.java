package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.model.exceptions.BadFormatException;
import ayds.dictionary.delta.fulllogic.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.fulllogic.model.exceptions.ModuleExceptions;
import ayds.dictionary.delta.fulllogic.model.services.Service;

class RepositoryImp implements Repository {
    private Service service;
    private DataBaseHelper dataBaseHelper;
    private ExceptionHandler handler;

    RepositoryImp(Service service, DataBaseHelper dataBaseHelper) {
        this.service = service;
        this.dataBaseHelper = dataBaseHelper;
        this.handler = ModuleExceptions.getInstance().getHandler();
    }

    public String searchTerm(String term) {
        String meaning = null;
        try {
            checkFormat(term);
            meaning = dataBaseHelper.getMeaning(term);
            final String prefixExistsInDb = "[*]";
            if (meaning != null) { // exists in db
                meaning = prefixExistsInDb + meaning;
            } else {
                meaning = service.getMeaning(term);
                dataBaseHelper.saveTerm(meaning, term);
            }
        } catch (Exception e) {
            handler.handleException(e);
        }
        return meaning;
    }

    private void checkFormat(String term) throws BadFormatException{
        if(!isWellFormedTermFormat(term) || !isValidTerm(term))
            throw new BadFormatException();
    }

    private boolean isWellFormedTermFormat(String term){
        char termLetter=' ';
        boolean wellFormedTerm = true;
        for(int i=0; i< term.length() && wellFormedTerm;i++){
            termLetter = term.charAt(i);
            if(!Character.isLetter(termLetter)){
                wellFormedTerm=false;
            }
        }
        return wellFormedTerm;
    }

    private boolean isValidTerm(String term){
        boolean validTerm = true;
        final String emptyString = "";
        boolean nullTerm = term == null;
        boolean emptyTerm = term.equals(emptyString);
        if (nullTerm || emptyTerm)
            validTerm = false;
        return validTerm;
    }
}
