package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DetailPendaftarActivity extends AppCompatActivity {

    String key;
    String Snama, Sstatus,Semail,Salamat,Spassword;
    Double lat,lon;
    Firebase FUrefdaftar,FUrefanggota;
    TextView Fnama,Fstatus,Femail,Falamat;
    IsiDataUser isiDataUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pendaftar);
        Fnama = (TextView) findViewById(R.id.nama);
        Fstatus = (TextView) findViewById(R.id.status);
        Femail = (TextView) findViewById(R.id.email);
        Falamat = (TextView) findViewById(R.id.alamat);

        Intent i = getIntent();
        key = i.getStringExtra("key");
        Snama = i.getStringExtra("nama");
        Sstatus = i.getStringExtra("status");
        Semail = i.getStringExtra("email");
        Salamat = i.getStringExtra("alamat");
        Spassword = i.getStringExtra("password");

        lat = i.getDoubleExtra("lat",0.000000);
        lon = i.getDoubleExtra("lon",0.000000);

        Fnama.setText(Snama);
        Fstatus.setText("("+Sstatus+")");
        Femail.setText(Semail);
        Falamat.setText(Salamat);

        //Toast.makeText(this,""+lat, Toast.LENGTH_LONG).show();

        //Toast.makeText(this,key, Toast.LENGTH_LONG).show();

        Firebase.setAndroidContext(this);
        FUrefanggota = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        FUrefdaftar = new Firebase("https://farmartcorp.firebaseio.com/pendaftaran");

        FUrefanggota.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(DetailPendaftarActivity.this,"Anggota berhasil diproses", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void Terima(View view) {
        isiDataUser = new IsiDataUser(Semail,Snama,Spassword,Sstatus,Salamat,lat,lon);
        FUrefanggota.push().setValue(isiDataUser);
        FUrefdaftar.child(key).setValue(null);
        finish();
    }

    public void Abaikan(View view) {
        FUrefdaftar.child(key).setValue(null);
        finish();
        System.exit(0);
    }
}
