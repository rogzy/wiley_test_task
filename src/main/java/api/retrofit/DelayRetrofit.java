package api.retrofit;

import api.aut.delay.DelayResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DelayRetrofit {

    @POST("delay/{delay}")
    Call<DelayResponse> delay(@Path("delay") int delay);
}
