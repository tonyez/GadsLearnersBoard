package com.example.gadslearnersboard.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gadslearnersboard.models.SkillLeader;
import com.example.gadslearnersboard.repository.LeaderBoardRepository;

import java.util.List;

public class SkillViewModel extends ViewModel {
    private LeaderBoardRepository mLeaderBoardRepository;
    private LiveData<List<SkillLeader>> mSkillLeaders;

    public SkillViewModel() {
        mLeaderBoardRepository = new LeaderBoardRepository();
    }

    public void init() {
        if (this.mSkillLeaders != null) {
            return;
        }
        mSkillLeaders = mLeaderBoardRepository.getSkillLeader();
    }

    public LiveData<List<SkillLeader>> getSkillLeaders() {
        return mSkillLeaders;
    }
}
