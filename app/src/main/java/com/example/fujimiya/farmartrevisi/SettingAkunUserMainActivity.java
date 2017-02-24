package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SettingAkunUserMainActivity extends AppCompatActivity {

    EditText Fnama,Fpass;
    Firebase Fref;
    String key,nama,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_akun_user_main);
        Firebase.setAndroidContext(this);
        Intent i = getIntent();
        key = i.getStringExtra("kunci");
        try {
        Fnama = (EditText) findViewById(R.id.etUserName);
        Fpass = (EditText) findViewById(R.id.etPass);
        Fref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Fnama.setText(dataSnapshot.child(key).child("username").getValue().toString());
                Fpass.setText(dataSnapshot.child(key).child("password").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        } catch (Exception e){

        }
    }

    public void update(View view) {
        try{
            //Toast.makeText(SettingAkunActivity.this,""+Fref.child("Admin").child("username").getKey(), Toast.LENGTH_LONG).show();
            Fref.child(key).child("username").setValue(Fnama.getText().toString());
            Fref.child(key).child("password").setValue(Fpass.getText().toString());
            finish();
            //System.exit(0);
        } catch (Exception e){

        }
    }

}
