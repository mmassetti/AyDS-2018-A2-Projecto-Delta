package ayds.dictionary.delta.fulllogic.modelo;

import ayds.dictionary.delta.fulllogic.modelo.bdd.BaseDatos;
import ayds.dictionary.delta.fulllogic.modelo.servicios.Servicio;

public class RepositorioImp implements Repositorio {
    private Servicio servicio;
    private BaseDatos baseDatos;

    public RepositorioImp(Servicio servicio, BaseDatos baseDatos) {
        this.servicio = servicio;
        this.baseDatos = baseDatos;
    }

    public String buscarTermino(String term) {
        String meaning = baseDatos.getMeaning(term);

        if (meaning != null) { // exists in db
            meaning = "[*]" + meaning;
        } else {
            meaning = servicio.getMeaning(term);
            baseDatos.saveTerm(meaning, term);
        }
        return meaning;
    }
}
