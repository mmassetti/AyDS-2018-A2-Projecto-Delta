package ayds.dictionary.delta.model;

import java.io.IOException;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import services.Service;


class RepositoryImp implements Repository {
    private Service service;
    private DataBaseHelper dataBaseHelper;
    private ExceptionHandler handler;
    private ConversorHelper conversorHelper;
    private FormatChecker formatChecker = new FormatCheckerImp();

    RepositoryImp(Service service, DataBaseHelper dataBaseHelper, ConversorHelper conversorHelper, ExceptionHandler handler) {
        this.service = service;
        this.dataBaseHelper = dataBaseHelper;
        this.handler = handler;
        this.conversorHelper = conversorHelper;
    }

    public Concept searchTerm(String term) {
        String meaning;
        Concept myConcept = createConcept(term);
        try {
            formatChecker.checkFormat(term);
            meaning = dataBaseHelper.getConceptMeaning(myConcept);
            final String prefixExistsInDb = "[*]";
            if (meaning != null) { // exists in db
                meaning = prefixExistsInDb + meaning;
                myConcept.setMeaning(meaning);
            } else {
                meaning = searchTermOnService(term);
                formatChecker.checkBadResult(meaning);
                meaning = convertFinalString(meaning);
                myConcept.setMeaning(meaning);
                dataBaseHelper.saveConcept(myConcept);
            }
        } catch (Exception e) {
            handler.handleException(e);
        }
        return myConcept;
    }

    private Concept createConcept(String term) {
        Concept concept = new Concept();
        concept.setTerm(term);
        concept.setSource(Source.BIGHUGELABS);
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
}