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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private EditText et_firstName, et_lastName, et_emailAddress, et_githubLink;
    private String firstName, lastName, emailAddress, githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
//        setSupportActionBar(toolbar);
//        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);


        et_firstName = findViewById(R.id.first_name);
        et_lastName = findViewById(R.id.last_name);
        et_emailAddress = findViewById(R.id.email_address);
        et_githubLink = findViewById(R.id.github_link);
        Button main_submit_button = findViewById(R.id.main_submit_button);
        main_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extractDataFromForm();
                showSubmissionDialog();
            }
        });
    }

    private void showSubmissionDialog() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this,
                R.style.RoundShapeTheme);
        View customVeiw = View.inflate(this, R.layout.alert_dialog, null);
        ImageButton cancelButton = customVeiw.findViewById(R.id.dismiss_button);
        materialAlertDialogBuilder
                .setView(customVeiw)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        postForm();
                    }
                })
                .create()
                .show();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void postForm() {
        new PostFormService().submitApp(firstName, lastName, emailAddress, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SubmitActivity.this,
                            R.style.RoundShapeTheme);
                    materialAlertDialogBuilder
                            .setView(R.layout.sub_successful)
                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                   finish();
                                }
                            })
                            .create()
                            .show();
                }else {
                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SubmitActivity.this,
                            R.style.RoundShapeTheme);
                    materialAlertDialogBuilder
                            .setView(R.layout.sub_unsuccessful)
                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
                                    recreate();
                                }
                            })
                            .create()
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Submission", "Failed Submission", t);
            }
        });
    }

    private void extractDataFromForm() {
        firstName = et_firstName.getText().toString();
        lastName = et_lastName.getText().toString();
        emailAddress = et_emailAddress.getText().toString();
        githubLink = et_githubLink.getText().toString();
    }
}