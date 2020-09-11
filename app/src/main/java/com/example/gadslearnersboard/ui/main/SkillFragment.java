package com.example.gadslearnersboard.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadslearnersboard.R;
import com.example.gadslearnersboard.models.SkillLeader;

import java.util.List;


public class SkillFragment extends Fragment {
    private SkillViewModel mSkillViewModel;


    /*public SkillFragment() {
        // Required empty public constructor
    }*/

    public static SkillFragment newInstance() {
        return new SkillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mSkillViewModel = new ViewModelProvider(this).get(SkillViewModel.class);
       mSkillViewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_skill, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.rv_gads_skill);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mSkillViewModel.getSkillLeaders().observe(getViewLifecycleOwner(), new Observer<List<SkillLeader>>() {
            @Override
            public void onChanged(List<SkillLeader> skillLeaders) {
                SkillAdapter skillAdapter = new SkillAdapter(requireContext(), skillLeaders);
                recyclerView.setAdapter(skillAdapter);
            }
        });
        return root;
    }
}