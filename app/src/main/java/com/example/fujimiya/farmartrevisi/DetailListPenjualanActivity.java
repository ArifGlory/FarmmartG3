package com.example.fujimiya.farmartrevisi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

public class DetailListPenjualanActivity extends AppCompatActivity {

    TextView komoditi,harga,spesifikasi;
    ImageView gambar;
    String Skomoditi,Sharga,Sspesifikasi,Sgambar,Skunci;
    Bitmap bitmap;
    DialogInterface.OnClickListener listener;
    Firebase FUrefanggota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_penjualan);

        komoditi = (TextView) findViewById(R.id.title_komoditi);
        gambar = (ImageView) findViewById(R.id.gambar);
        harga = (TextView) findViewById(R.id.harga);
        spesifikasi = (TextView) findViewById(R.id.spesifikasi);

        Firebase.setAndroidContext(this);
        FUrefanggota = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        FUrefanggota.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(DetailListPenjualanActivity.this,"Anggota berhasil diproses", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        try{
            Intent i = getIntent();
            Skunci = i.getStringExtra("kunci");
            Skomoditi = i.getStringExtra("komoditi");
            Sharga = i.getStringExtra("harga");
            Sspesifikasi = i.getStringExtra("spesifikasi");
            Sgambar = i.getStringExtra("gambar");
            //bitmap = StringToBitMap(i.getStringExtra("gambar"));

            komoditi.setText(Skomoditi);
            //gambar.setImageBitmap(bitmap);
            showbyte(Sgambar);
            harga.setText(Sharga);
            spesifikasi.setText(Sspesifikasi);



        }catch (Exception e){

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
                gambar.setImageBitmap(bitmap);
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

    public void Hapus(View view) {
        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakan anda ingin menghapus data?");
            builder.setCancelable(false);

            listener = new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        delete(Sgambar);
                        FUrefanggota.child(ListPenjualanFragment.kunci).child("zlist").child(Skunci).setValue(null);
                        finish();
                        //System.exit(0);
                    }

                    if(which == DialogInterface.BUTTON_NEGATIVE){
                        dialog.cancel();
                    }
                }
            };
            builder.setPositiveButton("Ya",listener);
            builder.setNegativeButton("Tidak", listener);
            builder.show();



        }catch (Exception e)
        {
            Toast.makeText(view.getContext(), ""+e, Toast.LENGTH_LONG).show();
        }
    }
}
