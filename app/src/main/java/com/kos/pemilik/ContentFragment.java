package com.kos.pemilik;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    private ProgressDialog dialog;
    //private List<Item> array = new ArrayList<Item>();
    List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private Adapter adapter;
    CircleImageView gambar_berita;
    Context context;
    private static final String TAG = ContentFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        listView = (ListView) v.findViewById(R.id.list);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("loading...");
        dialog.show();

        adapter = new Adapter(ContentFragment.this.getActivity(), array);
        listView.setAdapter(adapter);
        list_kos();
        return v;
    }

    private void list_kos(){

        // Creating volley request obj
        JsonArrayRequest arrReq = new JsonArrayRequest(config.LIST_KOST,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hideDialog();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Item item = new Item();
                                item.setKategori(obj.getString(config.KEY_kategori));
                                item.setAlamat(obj.getString(config.KEY_alamat));
                                item.setHarga(obj.getString(config.KEY_harga));
                                item.setFasilitas(obj.getString(config.KEY_fasilitas));
                                if (obj.getString(config.KEY_GAMBAR) != "") {
                                    item.setFoto(obj.getString(config.KEY_GAMBAR));
                                }

                                array.add(item);



                            } catch (JSONException e) {
                                Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                            }

                            // notifying list adapterInfo about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequesQueue(arrReq);
    }

    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }
    }
}
