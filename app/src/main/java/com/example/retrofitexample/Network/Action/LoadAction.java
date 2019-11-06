package com.example.retrofitexample.Network.Action;

import android.content.Context;

import com.example.retrofitexample.Model.SOAnswersResponse;
import com.example.retrofitexample.Network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadAction {
    public interface Listener {
        void LoadActionSuccess(SOAnswersResponse response);

        void onFailure(Call call, Throwable t);
    }

    public static void perform(final Listener listener) {
        ApiUtils.getSOService().getAnswers()
                .enqueue(new Callback<SOAnswersResponse>() {
                    @Override
                    public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                        if (listener != null) {
                            listener.LoadActionSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                        if (listener != null) {
                            listener.onFailure(call, t);
                        }
                    }
                });
    }
}
