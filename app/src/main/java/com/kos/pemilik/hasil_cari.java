package com.kos.pemilik;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class hasil_cari extends AppCompatActivity {
    SharedPreferences sp,spp;
    SharedPreferences.Editor spe,spee;
    TextView tamp_harga,tamp_kategori,tamp_fasilitas;
    ProgressDialog dialog;
    ListView tamp_kos;
    private List<Item> array = new ArrayList<Item>();
    private Adapter_hasil adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_cari);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("hasil cari kos");

        tamp_harga = (TextView) findViewById(R.id.tamp_harga);
        tamp_kategori = (TextView) findViewById(R.id.tamp_kategori);
        tamp_fasilitas = (TextView) findViewById(R.id.tamp_fasilitas);
        sp = this.getSharedPreferences("parameter", 0);
        spe = sp.edit();
        String tp_harga = sp.getString("paramet","");
        String tp_kategori = sp.getString("parameter","");
        String tp_fasilitas = sp.getString("paramete","");
        tamp_kategori.setText(tp_kategori);
        tamp_fasilitas.setText(tp_fasilitas);
        tamp_harga.setText(tp_harga);
        System.out.println("hasilnya "+tp_fasilitas);

        tamp_kos = (ListView) findViewById(R.id.tamp_kos);

        tamp_kos = (ListView) findViewById(R.id.tamp_kos);
        adp=new Adapter_hasil(this,array);
        tamp_kos.setAdapter(adp);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        String URL_CARI = config.CARI_KOST + tamp_harga.getText().toString() +"&kategori=" +
                tamp_kategori.getText().toString() +"&fasilitas=" + tamp_fasilitas.getText().toString();

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL_CARI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();

                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject obj=response.getJSONObject(i);
                        Item item=new Item();
                        item.setHarga(obj.getString(config.KEY_harga));
                        item.setFasilitas(obj.getString(config.KEY_fasilitas));
                        item.setKategori(obj.getString(config.KEY_kategori));
                        if (obj.getString(config.KEY_GAMBAR) != "") {
                            item.setFoto(obj.getString(config.KEY_GAMBAR));
                        }

                        array.add(item);
                    }catch(JSONException ex){
                        ex.printStackTrace();
                    }
                }
                adp.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"silahkan cek koneksi data anda",Toast.LENGTH_LONG).show();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10800, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        hideDialog();
        AppController.getInstance().addToRequesQueue(jsonArrayRequest);
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
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
