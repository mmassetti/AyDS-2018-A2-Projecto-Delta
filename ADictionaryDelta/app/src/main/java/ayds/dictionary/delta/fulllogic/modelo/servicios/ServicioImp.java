package ayds.dictionary.delta.fulllogic.modelo.servicios;

import ayds.dictionary.delta.fulllogic.modelo.HelperConversor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.io.IOException;

public class ServicioImp implements Servicio {
    WordsBighugelabsAPI wikiAPI;
    HelperConversor helperConversor;

    public ServicioImp(HelperConversor helperConversor){
        this.helperConversor = helperConversor;
        conectar();
    }

    public void conectar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
       wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }

    public String getMeaning(String term) {
        String meaning=null;
        Response<String> callResponse;
        try {
            callResponse = wikiAPI.getTerm(term).execute();
            if (callResponse.body() == null) {
                meaning = "No Results";
            } else {
                meaning = callResponse.body();
                meaning = helperConversor.transformarString(meaning);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return meaning;
    }
}
