package wiley_test_task.api.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import wiley_test_task.api.aut.delay.DelayResponse;

public interface DelayRetrofit {

    @POST("delay/{delay}")
    Call<DelayResponse> delay(@Path("delay") int delay);
}
