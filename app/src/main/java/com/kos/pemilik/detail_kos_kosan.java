package com.kos.pemilik;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class detail_kos_kosan extends AppCompatActivity {
    RequestQueue getBeritaDetail;
    Item item;
    Toolbar mToolbar;
    CollapsingToolbarLayout collapsingToolbar;
    private static final String TAG = detail_kos_kosan.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos_kosan);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("detail kos");
        ImageView dtl_gambar = (ImageView) findViewById(R.id.detail_image_view2);
        TextView dtl_id = (TextView) findViewById(R.id.detail_id_kos);
        TextView dtl_almt = (TextView) findViewById(R.id.detail_alamat);
        TextView dtl_hrg = (TextView) findViewById(R.id.detail_harga);
        getBeritaDetail = Volley.newRequestQueue(getApplicationContext());

        Intent in = getIntent();
        String id_kos = in.getStringExtra(config.TAG_id);
        String almt_kos = in.getStringExtra(config.KEY_alamat);
        String gambr_kos = in.getStringExtra(config.KEY_GAMBAR);
        String hrg_kos = in.getStringExtra(config.KEY_harga);

        dtl_id.setText(id_kos);
        dtl_almt.setText("alamat " + almt_kos);
        dtl_hrg.setText("harga " + hrg_kos);
        Picasso.with(getApplicationContext()).load(gambr_kos).into(dtl_gambar);
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

