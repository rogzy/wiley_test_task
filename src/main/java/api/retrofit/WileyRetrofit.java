package api.retrofit;

import api.aut.wiley.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WileyRetrofit {

    @GET("/en-us/search/autocomplete/comp_00001H9J")
    Call<SearchResponse> search(@Query("term") String term);
}
