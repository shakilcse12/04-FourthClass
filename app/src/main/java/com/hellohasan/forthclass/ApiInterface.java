package com.hellohasan.forthclass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("rest.php")
    Call<ResponseModel> sendUserData(@Body DataModel dataModel);


}
