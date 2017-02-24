package com.example.fujimiya.farmartrevisi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SettingAkunActivity extends AppCompatActivity {

    EditText Fnama,Fpass;
    Firebase Fref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_akun);
        Firebase.setAndroidContext(this);

        Fnama = (EditText) findViewById(R.id.etUserName);
        Fpass = (EditText) findViewById(R.id.etPass);

        try {
            Fref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
            Fref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Fnama.setText(dataSnapshot.child("Admin").child("username").getValue().toString());
                    Fpass.setText(dataSnapshot.child("Admin").child("password").getValue().toString());
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
            Fref.child("Admin").child("username").setValue(Fnama.getText().toString());
            Fref.child("Admin").child("password").setValue(Fpass.getText().toString());
            finish();
            //System.exit(0);
        } catch (Exception e){

        }

    }
}
