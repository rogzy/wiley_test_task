package api.retrofit;

import api.response.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.io.File;

public interface WileyRetrofit {

    @GET("/en-us/search/autocomplete/comp_00001H9J")
    Call<SearchResponse> search(@Query("term") String term);

    @GET
    Call<File> download(@Url String url);
}
