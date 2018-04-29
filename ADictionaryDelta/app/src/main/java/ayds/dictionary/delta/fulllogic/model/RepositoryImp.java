package ayds.dictionary.delta.fulllogic.model;

import ayds.dictionary.delta.fulllogic.model.database.DataBaseHelper;
import ayds.dictionary.delta.fulllogic.model.services.Service;

class RepositoryImp implements Repository {
    private Service service;
    private DataBaseHelper dataBaseHelper;

    RepositoryImp(Service service, DataBaseHelper dataBaseHelper) {
        this.service = service;
        this.dataBaseHelper = dataBaseHelper;
    }

    public String searchTerm(String term) {
        // TODO: 4/25/2018  chequear si es vacio o nulo
        //Aca se deberia manejar con el handler


        String meaning = dataBaseHelper.getMeaning(term);
        final String prefixExistsInDb = "[*]";

        if (meaning != null) { // exists in db
            meaning = prefixExistsInDb + meaning;
        } else {

            meaning = service.getMeaning(term);
            // TODO: 4/25/2018  chequear si es vacio o nulo
                dataBaseHelper.saveTerm(meaning, term);
        }
        return meaning;
    }
}
