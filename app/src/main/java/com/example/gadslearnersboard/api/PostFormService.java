package com.example.gadslearnersboard.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostFormService {
    private final static String BASE_URL = " https://docs.google.com/forms/d/e/";
    private final LeaderBoardApi mLeaderBoardApi;

    public PostFormService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        mLeaderBoardApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LeaderBoardApi.class);
    }

    public Call<Void> submitApp(String name, String lastName, String email, String link) {
        return mLeaderBoardApi.submitApp(name, lastName, email, link);
    }
}
