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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.List;

public class LearnerFragment extends Fragment {


    private LearnerViewModel mLearnerViewModel;
    private RecyclerView mRecyclerView;
    private LearnerAdapter mLearnerAdapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learner, container, false);
        mRecyclerView = root.findViewById(R.id.rv_gads);
        mLearnerViewModel.getTopLearner().observe(getViewLifecycleOwner(), new Observer<List<TopLearner>>() {
            @Override
            public void onChanged(List<TopLearner> topLearners) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mLearnerAdapter = new LearnerAdapter(requireContext(), topLearners);
                mRecyclerView.setAdapter(mLearnerAdapter);
            }
        });
        return root;
    }

    /*private void showNetworkDialog(Boolean isConnected) {
        final MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext(),
                R.style.RoundShapeTheme);
        View customTitleVeiw = View.inflate(requireContext(), R.layout.network_alert, null);
        materialAlertDialogBuilder
                .setCustomTitle(customTitleVeiw)
                .setMessage("No internet connection found!" + "\n" +
                        "Please, turn on your Mobile data")
                .setPositiveButton("OK", (dialogInterface, i) -> {

                })
                .setCancelable(false)
                .show();
    }*/
}