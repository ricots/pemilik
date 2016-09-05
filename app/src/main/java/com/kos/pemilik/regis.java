package com.kos.pemilik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class regis extends AppCompatActivity {
    EditText input_pemlik, e_alamat,e_pas,e_id,e_hp;
    Button btnregis;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registarsi");
        e_id = (EditText) findViewById(R.id.id_pemilik);
        input_pemlik = (EditText) findViewById(R.id.input_pemilik);
        e_alamat = (EditText) findViewById(R.id.input_alamat);
        e_pas = (EditText) findViewById(R.id.input_password);
        e_hp = (EditText) findViewById(R.id.input_hp);
        btnregis = (Button) findViewById(R.id.regis);

        PD = new ProgressDialog(this);
        PD.setMessage("silahkan tunggu.....");
        PD.setCancelable(false);
    }

    public void rgs(View v) {
        PD.show();
        final String e_id_pemilik = e_id.getText().toString();
        final String e_pemilik = input_pemlik.getText().toString();
        final String almt = e_alamat.getText().toString();
        final String pass = e_pas.getText().toString();
        final String hp = e_hp.getText().toString();


        StringRequest postRequest = new StringRequest(Request.Method.POST, config.REGIS_KOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "berhasil registrasi",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(regis.this, login.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
                Toast.makeText(getApplicationContext(),
                        "gagal registrasi", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(config.TAG_id,e_id_pemilik);
                params.put(config.KEY_nama_pemilik,e_pemilik);
                params.put(config.KEY_alamat,almt);
                params.put(config.KEY_PASS,pass);
                params.put(config.KEY_NO_HP,hp);
                return params;
            }
        };

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
        //MyApplication.getInstance().addToReqQueue(postRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // moveTaskToBack(true);;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
