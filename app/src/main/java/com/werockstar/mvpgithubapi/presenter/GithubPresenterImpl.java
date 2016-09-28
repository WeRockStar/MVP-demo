package com.werockstar.mvpgithubapi.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.werockstar.mvpgithubapi.model.GithubItem;
import com.werockstar.mvpgithubapi.service.GithubService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubPresenterImpl implements GithubPresenter {

    private GithubPresenter.View githubView;
    private Context context;
    private Call<GithubItem> call;

    private final String BASE_URL = "https://api.github.com/";

    public GithubPresenterImpl(GithubPresenter.View githubView, Context contexts) {
        this.githubView = githubView;
        this.context = contexts;
    }

    @Override
    public void onLoadData(String username) {
        githubView.showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        GithubService service = retrofit.create(GithubService.class);
        call = service.getData(username);
        call.enqueue(new CallbackGithub());
    }

    @Override
    public void onStop() {
        call.cancel();
    }


    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLogging())
                .build();
    }

    private HttpLoggingInterceptor getLogging() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }


    private class CallbackGithub implements Callback<GithubItem> {

        @Override
        public void onResponse(Call<GithubItem> call, Response<GithubItem> response) {
            if (response.isSuccessful())
                githubView.showGithubProfile(response.body());
            githubView.dismissLoading();
        }

        @Override
        public void onFailure(Call<GithubItem> call, Throwable t) {
            if (t != null) {
                Log.d("Debug", t.getMessage());
                Toast.makeText(context, "Can't connected network", Toast.LENGTH_SHORT)
                        .show();
            }
            githubView.dismissLoading();
        }
    }
}
