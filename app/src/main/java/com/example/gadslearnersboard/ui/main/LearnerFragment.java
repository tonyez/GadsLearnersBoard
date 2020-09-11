package com.example.gadslearnersboard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadslearnersboard.R;
import com.example.gadslearnersboard.models.TopLearner;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class LearnerFragment extends Fragment {


    private LearnerViewModel mLearnerViewModel;

    public static LearnerFragment newInstance() {
        return new LearnerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLearnerViewModel = new ViewModelProvider(this).get(LearnerViewModel.class);
        mLearnerViewModel.init();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learner, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.rv_gads);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mLearnerViewModel.getTopLearner().observe(getViewLifecycleOwner(), new Observer<List<TopLearner>>() {
            @Override
            public void onChanged(List<TopLearner> topLearners) {
                LearnerAdapter learnerAdapter = new LearnerAdapter(requireContext(),topLearners);
                recyclerView.setAdapter(learnerAdapter);
            }
        });
        return root;
    }
}