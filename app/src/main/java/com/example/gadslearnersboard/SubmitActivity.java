package com.example.gadslearnersboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gadslearnersboard.api.PostFormService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private EditText et_firstName, et_lastName, et_emailAddress, et_githubLink;
    private String firstName, lastName, emailAddress, githubLink;
    private Button mMain_submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_main);

        findViews();
        mMain_submit_button = findViewById(R.id.main_submit_button);
        mMain_submit_button.setOnClickListener(view -> {
            extractDataFromForm();
            makeViewsInvisible();
            showSubmissionDialog();
        });
    }

    private void findViews() {
        et_firstName = findViewById(R.id.first_name);
        et_lastName = findViewById(R.id.last_name);
        et_emailAddress = findViewById(R.id.email_address);
        et_githubLink = findViewById(R.id.github_link);
    }

    private void extractDataFromForm() {
        firstName = et_firstName.getText().toString();
        lastName = et_lastName.getText().toString();
        emailAddress = et_emailAddress.getText().toString();
        githubLink = et_githubLink.getText().toString();
    }

    private void makeViewsInvisible() {
        et_firstName.setVisibility(View.INVISIBLE);
        et_lastName.setVisibility(View.INVISIBLE);
        et_emailAddress.setVisibility(View.INVISIBLE);
        et_githubLink.setVisibility(View.INVISIBLE);
        mMain_submit_button.setVisibility(View.INVISIBLE);
    }

    private void showSubmissionDialog() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this,
                R.style.RoundShapeTheme);
        View customVeiw = View.inflate(this, R.layout.alert_dialog, null);
        ImageButton cancelButton = customVeiw.findViewById(R.id.dismiss_button);
        materialAlertDialogBuilder
                .setView(customVeiw)
                .setPositiveButton("YES", (dialogInterface, i) -> postForm())
                .create()
                .show();

        cancelButton.setOnClickListener(view -> recreate());

    }

    private void postForm() {
        new PostFormService().submitApp(firstName, lastName, emailAddress, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                int statusCode = response.code();
                showStatusDialog(statusCode);
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                Log.e("Submission", "Failed Submission", t);

            }
        });
    }

    private void showStatusDialog(int responseCode) {
        if (responseCode == 200) {
            MaterialAlertDialogBuilder materialAlertDialogBuilder =
                    new MaterialAlertDialogBuilder(SubmitActivity.this, R.style.RoundShapeTheme);
            materialAlertDialogBuilder
                    .setView(R.layout.sub_successful)
                    .setOnCancelListener(dialogInterface -> finish())
                    .create()
                    .show();
        }else {
            MaterialAlertDialogBuilder materialAlertDialogBuilder =
                    new MaterialAlertDialogBuilder(SubmitActivity.this,
                    R.style.RoundShapeTheme);
            materialAlertDialogBuilder
                    .setView(R.layout.sub_unsuccessful)
                    .setOnCancelListener(dialogInterface -> recreate())
                    .create()
                    .show();
        }
    }

}