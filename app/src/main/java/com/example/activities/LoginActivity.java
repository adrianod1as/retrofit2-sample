package com.example.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.R;
import com.example.api.RestClient;
import com.example.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    RadioButton rbSecretary, rbTeacher, rbFather;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //WIDGETS
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        rbSecretary = (RadioButton) findViewById(R.id.rbSecretary);
        rbTeacher = (RadioButton) findViewById(R.id.rbTeacher);
        rbFather = (RadioButton) findViewById(R.id.rbFather);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        //ON CLICK
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                try {
                    if (rbSecretary.isChecked()) {
                        String username = edtUsername.getText().toString();
                        String password = edtPassword.getText().toString();

                        if (edtUsername.getText().toString().length() == 0 && edtPassword.getText().toString().length() == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Type the username and password", Toast.LENGTH_SHORT).show();
                        } else if (edtUsername.getText().toString().length() == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Type the username", Toast.LENGTH_SHORT).show();
                        } else if (edtPassword.getText().toString().length() == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Type the password", Toast.LENGTH_SHORT).show();
                        } else {
                            RestClient.TAGApiInterface tagApiInterface = RestClient.getClient();
                            Call<ArrayList<User>> userCall = tagApiInterface.getCredentials(username, password);
                            userCall.enqueue(new Callback<ArrayList<User>>() {
                                @Override
                                public void onResponse(Call<ArrayList<User>> call, final Response<ArrayList<User>> response) {
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    progressDialog.dismiss();
                                                    //Log.i("TAG", "Status code = " + response.code());
                                                    ArrayList<User> user = response.body();
                                                    if (response.isSuccessful() && response.body().size() != 0) {
                                                        startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                        //Log.i("TAG", "response = " + new Gson().toJson(user));
                                                    } else {
                                                        Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }, 3000);
                                }

                                @Override
                                public void onFailure(Call<ArrayList<User>> call, final Throwable t) {
                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    progressDialog.dismiss();
                                                    t.printStackTrace();
                                                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                                                }
                                            }, 3000);
                                }
                            });
                        }
                    } else if (rbTeacher.isChecked()) {
                        progressDialog.dismiss();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        Toast.makeText(LoginActivity.this, "Don't work... Yet!", Toast.LENGTH_SHORT).show();
                    } else if (rbFather.isChecked()) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Don't work... Yet!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception err) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                                }
                            }, 3000);
                    err.printStackTrace();
                }
                break;
        }
    }
}
