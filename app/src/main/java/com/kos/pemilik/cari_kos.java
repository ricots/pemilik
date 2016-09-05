package com.kos.pemilik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class cari_kos extends Fragment {
Spinner kategori,spiner_fasilitas;
    SharedPreferences sp,spp;
    SharedPreferences.Editor spe, spee;
    Button cari;
    EditText harga;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_cari_kos,container,false);
        harga = (EditText) v.findViewById(R.id.harga);
        String list[]={"pilih kategori kos","laki-laki","perempuan"};
        String list_fasilitas[]={"pilih fasilitas kos","kasur+almari","kasur+almari+tv","kasur+almari+tv+kamar mandi dalam"};

        kategori = (Spinner) v.findViewById(R.id.kategori);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,list);
        kategori.setAdapter(AdapterList);

        spiner_fasilitas = (Spinner) v.findViewById(R.id.spiner_fasilitas);
        ArrayAdapter<String> AdapterList_fas = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,list_fasilitas);
        spiner_fasilitas.setAdapter(AdapterList_fas);

        cari = (Button) v.findViewById(R.id.cari);

        sp = getActivity().getSharedPreferences("parameter", 0);
        spe = sp.edit();
        spp = getActivity().getSharedPreferences("paramete", 1);

        spee = spp.edit();
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spe.putString("paramet", harga.getText().toString());
                spe.commit();
                Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                startActivity(menu);
                if (kategori.getSelectedItem().equals("laki-laki")) {
                   /* Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                    startActivity(menu);*/
                    spe.putString("parameter", "laki-laki");
                    spe.commit();
                }  if (kategori.getSelectedItem().equals("perempuan")){
                    /*Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                    startActivity(menu);*/
                    spe.putString("parameter", "perempuan");
                    spe.commit();
                }  if (spiner_fasilitas.getSelectedItem().equals("kasur+almari")){
                    /*Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                    startActivity(menu);*/
                    spe.putString("paramete", "");
                    spe.commit();
                }  if (spiner_fasilitas.getSelectedItem().equals("kasur+almari+tv")){
                    /*Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                    startActivity(menu);*/
                    spe.putString("paramete", "kasur_almari_tv");
                    spe.commit();
                }  if (spiner_fasilitas.getSelectedItem().equals("kasur+almari+tv+kamar mandi dalam")){
                    /*Intent menu = new Intent(cari_kos.this.getActivity(), hasil_cari.class);
                    startActivity(menu);*/
                    spe.putString("paramete", "kasur_almari_tv_kamar_mandi_dalam");
                    spe.commit();
                }
            }
        });

        return v;
    }
}
