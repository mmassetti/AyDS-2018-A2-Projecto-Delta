package ayds.dictionary.delta.fulllogic.modelo.servicios;

public interface Servicio {

    void conectar();
    String getMeaning(String term);
}
