package ayds.dictionary.delta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.exceptions.BadFormatException;
import ayds.dictionary.delta.model.exceptions.EmptyResultException;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.model.services.ServicesManager;


class RepositoryImp implements Repository {
    private final String prefixExistsInDB = "[*]";
    private ServicesManager servicesManager;
    private DataBaseHelper dataBaseHelper;
    private ExceptionHandler handler;
    private FormatChecker formatChecker;

    RepositoryImp(ServicesManager servicesManager, DataBaseHelper dataBaseHelper, ExceptionHandler handler, FormatChecker formatChecker) {
        this.servicesManager = servicesManager;
        this.dataBaseHelper = dataBaseHelper;
        this.handler = handler;
        this.formatChecker = formatChecker;
    }

    public List<Concept> searchTerm(String term) {
        List<Concept> meaningsList = new ArrayList<>();
        String meaning;
        for (Source source : allServices()) {
            try {
                Concept myConcept = createConcept(term);
                myConcept.setSource(source);
                meaning = getMeaningFromDB(myConcept);
                if (existsInDB(meaning)) {
                    meaning = prefixExistsInDB + meaning;
                    myConcept.setMeaning(meaning);
                } else {
                    meaning = getMeaningFromService(myConcept);
                    checkForBadMeaning(meaning);
                    myConcept.setMeaning(meaning);
                    saveConceptOnDB(myConcept);
                }
                meaningsList.add(myConcept);
            } catch (Exception e) {
                handler.handleException(e);
            }
        }
        return meaningsList;
    }


    private Concept createConcept(String term) {
        Concept concept = new Concept();
        concept.setTerm(term);
        return concept;
    }

    private boolean existsInDB(String meaning) {
        return meaning != null;
    }

    private Set<Source> allServices() {
        return servicesManager.getAllServices();
    }

    private void checkForBadMeaning(String meaning) throws EmptyResultException {
        formatChecker.checkBadResult(meaning);
    }

    private String getMeaningFromDB(Concept concept) {
        return dataBaseHelper.getConceptMeaning(concept);
    }

    private String getMeaningFromService(Concept concept) throws Exception {
        return servicesManager.getMeaning(concept.getTerm(), concept.getSource());
    }

    private void saveConceptOnDB(Concept concept) {
        dataBaseHelper.saveConcept(concept);
    }
}