package services;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceImp implements Service {
    WordsBighugelabsAPI wikiAPI;

    public ServiceImp() {
        connect();
    }

    public void connect() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }

    public String getMeaning(String term) throws Exception {
        String meaning = null;
        Response<String> callResponse;
        callResponse = wikiAPI.getTerm(term).execute();
        meaning = callResponse.body();
        return meaning;
    }

}
