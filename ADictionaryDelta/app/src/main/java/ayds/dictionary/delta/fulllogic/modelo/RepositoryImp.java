package ayds.dictionary.delta.fulllogic.modelo;

import ayds.dictionary.delta.fulllogic.modelo.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.modelo.services.Service;

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
