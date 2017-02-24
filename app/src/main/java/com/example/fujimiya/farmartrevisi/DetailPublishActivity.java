package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class DetailPublishActivity extends AppCompatActivity {

    public String Sgambar, Sharga, Sspesifikasi, Snama, Skomoditi;
    ImageView imageView;
    TextView Fnama, Fharga, Fspesifikasi;
    String kunci, room;

    Firebase FUrefregistrasi, FUrefanggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publish);

        Fnama = (TextView) findViewById(R.id.nama);
        Fharga = (TextView) findViewById(R.id.harga);
        Fspesifikasi = (TextView) findViewById(R.id.spesifikasi);
        imageView = (ImageView) findViewById(R.id.gambar);
        Intent i = getIntent();

        try {
            Sgambar = i.getStringExtra("gambar");
            Sharga = i.getStringExtra("harga");
            Sspesifikasi = i.getStringExtra("spesifikasi");
            Snama = i.getStringExtra("nama");
            kunci = i.getStringExtra("kunci");
            room = i.getStringExtra("room");
            Skomoditi = i.getStringExtra("komoditi");

            showbyte(Sgambar);
            Fnama.setText(Snama);
            Fharga.setText(Sharga);
            Fspesifikasi.setText(Sspesifikasi);


            Firebase.setAndroidContext(this);
            FUrefanggota = new Firebase("https://farmartcorp.firebaseio.com/anggota");
            FUrefregistrasi = new Firebase("https://farmartcorp.firebaseio.com/registrasi");




        } catch (Exception e) {
            Toast.makeText(DetailPublishActivity.this, "Data Telah Rusak", Toast.LENGTH_LONG).show();
        }


    }

    private void showbyte(String nama){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://farmartcorp.appspot.com/file/");
        StorageReference islandRef = storageRef.child(nama);
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            }
        });

    }

    private void delete(String nama){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://farmartcorp.appspot.com/file/");
        StorageReference islandRef = storageRef.child(nama);

        islandRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getBaseContext(),"Data terhapus",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(),"Data tidak terhapus",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void Terima(View view) {

        IsiDataPublishTerima isiDataPublishTerima = new IsiDataPublishTerima(Sgambar, Sharga, Sspesifikasi,Skomoditi);
        FUrefanggota.child(room).child("zlist").push().setValue(isiDataPublishTerima);
        FUrefregistrasi.child(kunci).setValue(null);
        finish();

    }

    public void Abaikan(View view) {
        try {
            delete(Sgambar);
            FUrefregistrasi.child(kunci).setValue(null);
            finish();
            //System.exit(0);
        }catch (Exception e){
            Toast.makeText(this,"Ada masalah",Toast.LENGTH_LONG).show();
        }
    }
}
