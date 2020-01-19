package api.retrofit;

import api.request.DelayRequest;
import api.response.DelayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DelayRetrofit {

    @POST("delay/{delay}")
    Call<DelayResponse> delay(@Path("delay") int delay);
}
