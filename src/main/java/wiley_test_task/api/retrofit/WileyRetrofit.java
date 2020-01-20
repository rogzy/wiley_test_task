package wiley_test_task.api.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wiley_test_task.api.aut.wiley.SearchResponse;

public interface WileyRetrofit {

    @GET("/en-us/search/autocomplete/comp_00001H9J")
    Call<SearchResponse> search(@Query("term") String term);
}
