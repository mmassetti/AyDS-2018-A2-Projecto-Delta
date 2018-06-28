package ayds.dictionary.delta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ayds.dictionary.delta.model.database.DataBaseHelper;
import ayds.dictionary.delta.model.exceptions.ExceptionHandler;
import ayds.dictionary.delta.model.services.ServicesManager;


class RepositoryImp implements Repository {
    private final String prefixExistsInDB = "[*]";
    private ServicesManager servicesManager;
    private DataBaseHelper dataBaseHelper;
    private ExceptionHandler handler;
    private ResultsManager resultsManager;

    RepositoryImp(ServicesManager servicesManager, DataBaseHelper dataBaseHelper, ExceptionHandler handler, ResultsManager resultsManager) {
        this.servicesManager = servicesManager;
        this.dataBaseHelper = dataBaseHelper;
        this.handler = handler;
        this.resultsManager = resultsManager;
    }

    public List<FinalConceptResult> searchTerm(String term) {
        List<FinalConceptResult> finalList = new ArrayList<>();
        List<Concept> meaningsList = new ArrayList<>();
        Map<Source, Exception> sourceExceptionMap = new TreeMap<>();
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
                    myConcept.setMeaning(meaning);
                    saveConceptOnDB(myConcept);
                }
                meaningsList.add(myConcept);
            } catch (Exception e) {
                sourceExceptionMap.put(source, e);
            }
        }
        if (thereIsConnection(sourceExceptionMap))
            finalList = buildFinalList(term, meaningsList, sourceExceptionMap);
        return finalList;
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

    private String getMeaningFromDB(Concept concept) {
        return dataBaseHelper.getConceptMeaning(concept);
    }

    private String getMeaningFromService(Concept concept) throws Exception {
        return servicesManager.getMeaning(concept.getTerm(), concept.getSource());
    }

    private void saveConceptOnDB(Concept concept) {
        dataBaseHelper.saveConcept(concept);
    }

    private boolean thereIsConnection(Map<Source, Exception> sourceExceptionMap) {
        boolean appConnected = true;
        if (!(sourceExceptionMap.isEmpty())) {
            appConnected = handler.thereIsConnection(sourceExceptionMap);
            if (!appConnected)
                handler.appNotConnected();
        }
        return appConnected;
    }

    private List<FinalConceptResult> buildFinalList(String term, List<Concept> meaningsList, Map<Source, Exception> sourceExceptionMap) {
        return resultsManager.buildList(term, meaningsList, sourceExceptionMap);
    }
}