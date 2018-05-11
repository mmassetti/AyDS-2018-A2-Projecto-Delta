package ayds.dictionary.delta.model;

import java.io.IOException;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.model.exceptions.ModuleExceptions;
import services.Service;

class RepositoryImp implements Repository {
    private Service service;
    private DataBaseHelper dataBaseHelper;
    private ExceptionHandler handler;
    private ConversorHelper conversorHelper;

    RepositoryImp(Service service, DataBaseHelper dataBaseHelper, ConversorHelper conversorHelper) {
        this.service = service;
        this.dataBaseHelper = dataBaseHelper;
        this.handler = ModuleExceptions.getInstance().getHandler();
        this.conversorHelper = conversorHelper;
    }

    public Concept searchTerm(String term) {
        String meaning;
        Concept myConcept = createConcept(term);
        try {
            checkFormat(term);
            meaning = dataBaseHelper.getConceptMeaning(myConcept);
            final String prefixExistsInDb = "[*]";
            if (meaning != null) { // exists in db
                meaning = prefixExistsInDb + meaning;
                myConcept.setMeaning(meaning);
            } else {
                meaning = searchTermOnService(term);
                if (!isBadResult(meaning)) {
                    meaning = convertFinalString(meaning);
                    myConcept.setMeaning(meaning);
                    dataBaseHelper.saveConcept(myConcept);
                }
            }
        } catch (Exception e) {
            handler.handleException(e);
        }
        return myConcept;
    }


    private Concept createConcept(String term) {
        Concept concept = new Concept();
        concept.setTerm(term);
        concept.setSource(Source.WIKIPEDIA);
        return concept;
    }

    private String searchTermOnService(String term) throws Exception {
        try {
            return service.getMeaning(term);
        } catch (IOException e) {
            throw new ConnectionErrorException();
        }
    }

    private String convertFinalString(String meaning) {
        return conversorHelper.convertString(meaning);
    }

    private void checkFormat(String term) throws BadFormatException {
        if (!isWellFormedTermFormat(term) || !isValidTerm(term))
            throw new BadFormatException();
    }

    private boolean isWellFormedTermFormat(String term) {
        char termLetter;
        boolean wellFormedTerm = true;
        for (int i = 0; i < term.length() && wellFormedTerm; i++) {
            termLetter = term.charAt(i);
            if (!Character.isLetter(termLetter)) {
                wellFormedTerm = false;
            }
        }
        return wellFormedTerm;
    }

    private boolean isValidTerm(String term) {
        boolean validTerm = true;
        final String emptyString = "";
        boolean nullTerm = term == null;
        boolean emptyTerm = term.equals(emptyString);
        if (nullTerm || emptyTerm)
            validTerm = false;
        return validTerm;
    }

    private boolean isBadResult(String meaning) throws EmptyResultException {
        boolean badResult = false;
        final String emptyString = "";
        boolean nullMeaning = meaning == null;
        boolean emptyMeaning = meaning.equals(emptyString);
        if (nullMeaning || emptyMeaning) {
            throw new EmptyResultException();
        }
        return badResult;
    }
}
