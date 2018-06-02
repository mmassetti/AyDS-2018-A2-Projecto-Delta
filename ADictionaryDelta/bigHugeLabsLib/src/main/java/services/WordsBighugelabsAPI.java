package services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordsBighugelabsAPI {

    @GET("206df5b830b0688a637f1bda5b49f4ac/{word}/xml")
    Call<String> getTerm(@Path("word") String word);

}
