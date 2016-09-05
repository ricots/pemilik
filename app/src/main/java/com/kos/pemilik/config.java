package com.kos.pemilik;

/**
 * Created by ACER on 2016-02-27.
 */
public class config {
    //String URL_CARI = config.PAKET + detail_temp.getText().toString() +"&harga="+harga.getText.toString();
    //http://kosserver.16mb.com/cari_kos.php?harga=400000&kategori=perempuan&fasilitas=kasur+almari+tv
    public static final String LIST_KOST = "http://kosserver.16mb.com/list_kos.php";
    public static final String CARI_KOST = "http://kosserver.16mb.com/cari_kos.php?harga=";
    public static final String LOGIN_KOST = "http://kosserver.16mb.com/login.php";
    public static final String REGIS_KOST = "http://kosserver.16mb.com/regis.php";
    public static final String INPUT_KOST = "http://kosserver.16mb.com/input_pemilik.php";
    public static final String TAG_id = "id_pemilik";
    public static final String KEY_nama_pemilik = "nama_pemilik";
    public static final String KEY_alamat = "alamat";
    public static final String KEY_harga = "harga";
    public static final String KEY_fasilitas = "fasilitas";
    public static final String KEY_kategori = "kategori";
    public static final String KEY_GAMBAR = "foto";
    public static final String KEY_PASS = "password";
    public static final String KEY_NO_HP = "no_hp";
    public static final String KEY_jml_kamar = "jumlah_kamar";
    public static final String KEY_TGL = "tanggal";
    public static final String KEY_MULAI = "jam_mulai";
    public static final String KEY_SELESAI = "jam_berahir";
    public static final String KEY_ID = "id_kegiatan";
    public static final String KEY_DESKIRIPSI = "deskripsi";

    public static final String KEY_ID_RUANG = "id_ruang";
    public static final String KEY_KODE_JADWAL = "kode_jadwal";
    public static final String KEY_NAMA_RUANG = "nama_ruang";
    public static final String KEY_HARI = "hari";
    public static final String KEY_JAM = "jam";
    public static final String KEY_JAM_SELESAI = "selesai";
    public static final String KEY_KEGIATAN_LAB = "kegiatan";
    public static final String KEY_PRODI_LAB = "prodi";
    public static final String KEY_MHS = "jumlah_mahasiswa";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "npm";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}

