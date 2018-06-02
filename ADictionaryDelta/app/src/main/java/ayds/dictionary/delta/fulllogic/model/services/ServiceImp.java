package ayds.dictionary.delta.fulllogic.model.services;

import ayds.dictionary.delta.fulllogic.model.ConversorHelper;
import ayds.dictionary.delta.fulllogic.model.exceptions.ConnectionErrorException;
import ayds.dictionary.delta.fulllogic.model.exceptions.EmptyResultException;
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

    public String getMeaning(String term) throws Exception {
        String meaning;
        Response<String> callResponse;
        try {
            callResponse = wikiAPI.getTerm(term).execute();
            meaning = callResponse.body();
            if(isBadResult(meaning))
                throw new EmptyResultException();
            else
                meaning = convertFinalString(meaning);

        } catch (IOException e1) {
            throw new ConnectionErrorException();
        } catch (Exception e1){
            throw e1;
        }
        return meaning;
    }

    private String convertFinalString(String meaning){
        return conversorHelper.convertString(meaning);
    }

    private boolean isBadResult(String meaning){
        boolean badResult = false;
        final String emptyString = "";
        boolean nullMeaning = meaning==null;
        boolean emptyMeaning = meaning.equals(emptyString);
        if(nullMeaning || emptyMeaning)
            badResult = true;
        return badResult;
    }
}
