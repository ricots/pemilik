package com.kos.pemilik;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ACER on 2016-01-31.
 */
public class Adapter_hasil extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader=AppController.getInstance().getmImageLoader();
    public Adapter_hasil(Activity activity, List<Item> items){
        this.activity=activity;
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.list_hasil,null);
            //convertView=inflater.inflate(R.layout.list_jadwal,null);
       }
         if(imageLoader==null);
        imageLoader=AppController.getInstance().getmImageLoader();
        CircleImageView imageView = (CircleImageView) convertView.findViewById(R.id.image_view1);
        TextView hs_kat = (TextView) convertView.findViewById(R.id.hs_kategori);
        TextView hs_hrg = (TextView) convertView.findViewById(R.id.hs_harga);
        TextView hs_fasl = (TextView) convertView.findViewById(R.id.hs_fasilitas);

        Item item=items.get(position);
        Picasso.with(activity).load(item.getFoto()).resize(120, 60).into(imageView);
        hs_kat.setText("kategori : " +item.getKategori());
        hs_hrg.setText("harga : " +item.getHarga());
        hs_fasl.setText("fasilitas : " + item.getFasilitas());


        Item it=items.get(position);

        return convertView;
    }
}
