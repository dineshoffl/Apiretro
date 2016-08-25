package tracker.com.apiretro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface GithubUserAPI {


    @GET("getCategory")
    Call<Example> getUser();
}
