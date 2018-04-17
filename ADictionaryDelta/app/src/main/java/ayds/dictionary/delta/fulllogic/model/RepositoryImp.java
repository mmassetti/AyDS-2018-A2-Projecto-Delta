package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.model.services.Service;

public class RepositoryImp implements Repository {
    private Service service;
    private DataBaseHelper dataBaseHelper;

    public RepositoryImp(Service service, DataBaseHelper dataBaseHelper) {
        this.service = service;
        this.dataBaseHelper = dataBaseHelper;
    }

    public String searchTerm(String term) {
        String meaning = dataBaseHelper.getMeaning(term);

        if (meaning != null) { // exists in db
            meaning = "[*]" + meaning;
        } else {
            meaning = service.getMeaning(term);
            dataBaseHelper.saveTerm(meaning, term);
        }
        return meaning;
    }
}
