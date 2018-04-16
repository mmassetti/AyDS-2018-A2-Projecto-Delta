package ayds.dictionary.delta.fulllogic.modelo.servicios;

import android.util.Log;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.io.IOException;

public class ServicioApiImp implements ServicioApi {
    WordsBighugelabsAPI wikiAPI;

    public ServicioApiImp(){
        conectar();
    }

    public void conectar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
       wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }


    /* Revisar método, deberia buscar el termino en la api y despues devolver algo que no se si es un String*/
    public String getMeaning(String term) { /*Devuelve un String?*/
        String meaning;
        Response<String> callResponse;
        try {
            callResponse = wikiAPI.getTerm(term).execute();
            Log.e("**", "XML " + callResponse.body());
            if (callResponse.body() == null) {
                meaning = "No Results";
            } else {
                meaning = callResponse.body();
            }
            return meaning;

        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
