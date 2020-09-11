package com.example.gadslearnersboard.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.gadslearnersboard.api.LeaderBoardService;
import com.example.gadslearnersboard.models.SkillLeader;
import com.example.gadslearnersboard.models.TopLearner;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LeaderBoardRepository {
    private static final String TAG = "LeaderBoardRepository";

    public MutableLiveData<List<SkillLeader>> getSkillLeader() {
        final MutableLiveData<List<SkillLeader>> skillLeader = new MutableLiveData<>();
        new LeaderBoardService().getSkillLeader().enqueue(new Callback<List<SkillLeader>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                if (response.isSuccessful()) {
                    skillLeader.postValue(response.body());
                }else {
                    //response failed for some reason
                    Log.e(TAG, "onResponse: Request Failed " + response.errorBody());
                    skillLeader.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<SkillLeader>> call, Throwable t) {
                skillLeader.setValue(null);
            }
        });
        return skillLeader;
    }

    public MutableLiveData<List<TopLearner>> getTopLearner() {
        final MutableLiveData<List<TopLearner>> topLearner = new MutableLiveData<>();
        new LeaderBoardService().getTopLearner().enqueue(new Callback<List<TopLearner>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                if (response.isSuccessful()) {
                    topLearner.setValue(response.body());
                }else {
                    //response failed for some reason
                    Log.e(TAG, "onResponse: Request Failed " + response.errorBody());
                    topLearner.postValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<TopLearner>> call, @NotNull Throwable t) {
                topLearner.setValue(null);
            }
        });
        return topLearner;
    }
}
