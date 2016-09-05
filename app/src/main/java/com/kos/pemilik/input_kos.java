package com.kos.pemilik;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class input_kos extends Fragment {
    Spinner kategori, spiner_fasilitas;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    EditText idnya,input_harga_kos,jml;
    Button add,broswe;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView img;
    Bitmap bitmap;
    File resultingFile;
    static final int REQUEST_TAKE_PHOTO = 11111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_input_kos,container,false);
        idnya = (EditText) v.findViewById(R.id.idnya);
        img =(ImageView) v.findViewById(R.id.imgsrc);
        input_harga_kos = (EditText) v.findViewById(R.id.input_harga_kos);
        jml = (EditText) v.findViewById(R.id.input_jml);
        sp = getActivity().getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user = sp.getString(config.EMAIL_SHARED_PREF, "Not Available");
        idnya.setText(user);

        String list[]={"pilih kategori kos","laki-laki","perempuan"};
        String list_fasilitas[]={"pilih fasilitas kos","kasur+almari","kasur+almari+tv","kasur+almari+tv+kamar mandi dalam"};

        kategori = (Spinner) v.findViewById(R.id.katg_kos);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,list);
        kategori.setAdapter(AdapterList);

        spiner_fasilitas = (Spinner) v.findViewById(R.id.input_fasilitas_kos);
        ArrayAdapter<String> AdapterList_fas = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,list_fasilitas);
        spiner_fasilitas.setAdapter(AdapterList_fas);

        add = (Button) v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final String id = idnya.getText().toString();
                final String hrgnya = input_harga_kos.getText().toString();
                final String kos = jml.getText().toString();
                final String inp_ktg = kategori.getSelectedItem().toString();
                final String inp_fast = spiner_fasilitas.getSelectedItem().toString();
                final String image_kos = getStringImage(bitmap);
                RequestHandler rh = new RequestHandler() {
                    @Override
                    public boolean canHandleRequest(com.squareup.picasso.Request data) {
                        return false;
                    }

                    @Override
                    public Result load(com.squareup.picasso.Request request, int networkPolicy) throws IOException {
                        return null;
                    }
                };


                StringRequest postRequest = new StringRequest(Request.Method.POST, config.INPUT_KOST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(),
                                        "Data Inserted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                input_harga_kos.setText(null);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Map<String, String> params = new HashMap<String, String>();
                        Map<String,String> params = new Hashtable<String, String>();
                        params.put(config.TAG_id,id);
                        params.put(config.KEY_harga,hrgnya);
                        params.put(config.KEY_kategori,inp_ktg);
                        params.put(config.KEY_fasilitas,inp_fast);
                        params.put(config.KEY_GAMBAR,image_kos);
                        params.put(config.KEY_jml_kamar,kos);
                        return params;
                    }
                };

                // Adding request to request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(postRequest);
                //MyApplication.getInstance().addToReqQueue(postRequest);
*/
                uploadImage();
            }
        });


        broswe = (Button) v.findViewById(R.id.broswe);
        broswe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == broswe){
                    showFileChooser();
                }
            }
        });
        return v;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //img.setImageBitmap(imageBitmap);
            bitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(bitmap);
        }
        else if (data != null) {
            Uri picturePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), picturePath);
            } catch (IOException e){}
            finally {
                img.setImageBitmap(bitmap);
            }

        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void showFileChooser() {
        final CharSequence[] items = {"Ambil Foto", "Pilih dari Galeri",
                "Batal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(input_kos.this.getActivity());
        builder.setTitle("Tambah Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil Foto")) {
                    Intent cameraIntent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(
                            cameraIntent,
                            REQUEST_TAKE_PHOTO);

                } else if (items[item].equals("Pilih dari Galeri")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void setFullImageFromFilePath(String imagePath, ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    protected void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        CameraActivity activity = (CameraActivity)getActivity();
        File f = new File(activity.getCurrentPhotoPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.getActivity().sendBroadcast(mediaScanIntent);
    }

    private void uploadImage(){
        final String id = idnya.getText().toString();
        final String hrgnya = input_harga_kos.getText().toString();
        final String kos = jml.getText().toString();
        final String inp_ktg = kategori.getSelectedItem().toString();
        final String inp_fast = spiner_fasilitas.getSelectedItem().toString();
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            Request_foto rh = new Request_foto();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getActivity(),
                        "Data Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put(config.KEY_GAMBAR, uploadImage);
                data.put(config.TAG_id,id);
                data.put(config.KEY_harga,hrgnya);
                data.put(config.KEY_kategori,inp_ktg);
                data.put(config.KEY_fasilitas,inp_fast);
                data.put(config.KEY_jml_kamar,kos);
                String result = rh.sendPostRequest(config.INPUT_KOST,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
}
