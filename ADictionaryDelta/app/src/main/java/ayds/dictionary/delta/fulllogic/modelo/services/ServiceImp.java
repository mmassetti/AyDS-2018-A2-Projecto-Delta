package ayds.dictionary.delta.fulllogic.modelo.services;

import ayds.dictionary.delta.fulllogic.modelo.ConversorHelper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.io.IOException;

public class ServiceImp implements Service {
    WordsBighugelabsAPI wikiAPI;
    ConversorHelper conversorHelper;

    public ServiceImp(ConversorHelper conversorHelper){
        this.conversorHelper = conversorHelper;
        connect();
    }

    public void connect() {
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
                meaning = conversorHelper.convertString(meaning);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return meaning;
    }
}
