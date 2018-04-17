package ayds.dictionary.delta.fulllogic.model.services;

import ayds.dictionary.delta.fulllogic.model.ConversorHelper;
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
            meaning = callResponse.body();
            if (meaning == null) {
                meaning = "No Results";
            } else {
                meaning = convertFinalString(meaning);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return meaning;
    }

    private String convertFinalString(String meaning){
        return conversorHelper.convertString(meaning);
    }
}
