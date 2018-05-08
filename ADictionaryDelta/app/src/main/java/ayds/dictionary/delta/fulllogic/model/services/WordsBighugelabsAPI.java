package ayds.dictionary.delta.fulllogic.model.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordsBighugelabsAPI {

  @GET("ba10a6bc725bde1d3b3dfc78f9b92ab1/{word}/xml")
  Call<String> getTerm(@Path("word") String word);

}
