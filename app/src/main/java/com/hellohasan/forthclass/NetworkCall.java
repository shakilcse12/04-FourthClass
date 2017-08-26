package com.hellohasan.forthclass;

import android.support.annotation.NonNull;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    private CallBackInterface callBackInterface;

    public NetworkCall(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void sendData(DataModel dataModel){

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> call = apiInterface.sendUserData(dataModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                Logger.d("Response: " + response);
                ResponseModel responseModel = response.body();
                Logger.d("Response: " + responseModel.getMessage());
                callBackInterface.uiUpdate(responseModel);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Logger.d("Failure Response: " + t.toString());
                callBackInterface.uiUpdate(new ResponseModel(false, t.getMessage()));
            }
        });
    }
}
