package com.kos.pemilik;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private AsyncTask<String, String, String> asyncTask;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    //Defining views
    private EditText editTextnpm;
    private EditText editTextPass;
    private Button buttonLogin;
    ProgressDialog PD;
    TextView akun;
    private boolean loggedIn = false;
    /*private String ktp;
    private String password;*/
    private TextView mTvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextnpm = (EditText) findViewById(R.id.npm);
        editTextPass = (EditText) findViewById(R.id.pass);
        buttonLogin = (Button) findViewById(R.id.login);
        mTvResponse = (TextView)findViewById(R.id.tv_response);
        /*buttonLogin.setOnClickListener(this);
        PD = new ProgressDialog(this);
        PD.setMessage("silahkan tunggu.....");
        PD.setCancelable(false);
*/      sp = this.getSharedPreferences("isi data", 0);
        spe = sp.edit();
        PD = new ProgressDialog(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        akun = (TextView) findViewById(R.id.akun);
        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(login.this,regis.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        //Getting values from edit texts
        final String email = editTextnpm.getText().toString().trim();
        final String password = editTextPass.getText().toString().trim();
        PD.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_KOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(config.LOGIN_SUCCESS)) {
                            hideDialog();
                            sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            spe = sp.edit();

                            //Adding values to editor
                            spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                            spe.putString(config.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            spe.commit();
                            gotoCourseActivity();

                        } else {
                            hideDialog();
                            //Displaying an error message on toast
                            Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(login.this, "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(config.TAG_id, email);
                params.put(config.KEY_PASS, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void gotoCourseActivity() {
        Intent intent = new Intent(login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!PD.isShowing())
            PD.show();
    }

    private void hideDialog() {
        if (PD.isShowing())
            PD.dismiss();
    }

  /*  @Override
    public void onClick(View v) {
        login();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        PD.show();
        ktp = editTextnpm.getText().toString();
        password = editTextPass.getText().toString();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        //If we are getting success from server
                         if(response.equalsIgnoreCase(config.LOGIN_SUCCESS)){
                        //Creating a shared preference
                       *//* sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        spe = sp.edit();

                        //Adding values to editor
                        spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                        spe.putString(config.EMAIL_SHARED_PREF, ktp);

                        //Saving values to editor
                        spe.commit();

                        //Starting profile activity
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),
                                "berhasil login",
                                Toast.LENGTH_SHORT).show();*//*
                             openProfile();
                          }else{
                        //If the server response is not success
                        //Displaying an error message on toast
                           Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        PD.dismiss();
                        Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(config.KEY_NO_KTP,ktp);
                params.put(config.KEY_PASSWORD,password);
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(config.KEY_NO_KTP,ktp);
        startActivity(intent);
    }*/
}
