package ayds.dictionary.delta.fulllogic.modelo;

import ayds.dictionary.delta.fulllogic.modelo.bdd.room.DataBase;
import ayds.dictionary.delta.fulllogic.modelo.servicios.ServicioApi;

public class RepositorioImp implements Repositorio {
    private ServicioApi servicioApi;

    public RepositorioImp(ServicioApi servicioApi){
        this.servicioApi=servicioApi;
    }

    public String buscarTermino(String term){
        String meaning = DataBase.getMeaning(term);

        if (meaning != null) { // exists in db
            meaning = "[*]" + meaning;
        } else {
            meaning = servicioApi.getMeaning(term);
            //Como no estaba en la bdd, deberia guardar lo que obtuvo
        }


        return meaning;
    }
}
