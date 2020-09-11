package com.example.gadslearnersboard.api;

import com.example.gadslearnersboard.models.SkillLeader;
import com.example.gadslearnersboard.models.TopLearner;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderBoardService {
    private final static String BASE_URL = "https://gadsapi.herokuapp.com";
    private final LeaderBoardApi mLeaderBoardApi;

    public LeaderBoardService() {

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


    public Call<List<SkillLeader>> getSkillLeader() {
        return mLeaderBoardApi.getSkillLeader();
    }

    public Call<List<TopLearner>> getTopLearner() {
        return mLeaderBoardApi.getTopLearner();
    }
}
