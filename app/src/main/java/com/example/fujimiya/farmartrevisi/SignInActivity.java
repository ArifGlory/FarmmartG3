package com.example.fujimiya.farmartrevisi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    Firebase Fref;
    EditText Fusername,Fpassword;
    DialogInterface.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Fusername = (EditText) findViewById(R.id.etUserName);
        Fpassword = (EditText) findViewById(R.id.etPass);
        Firebase.setAndroidContext(this);
        Fref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(SignInActivity.this,"Data Berhasil Diambil", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void signup(View view) {
        finish();
        Intent i = new Intent(SignInActivity.this,PilihActivity.class);
        startActivity(i);
    }

    public void signin(View view) {

        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){

                    String nama = (String) child.child("username").getValue();
                    String password = (String) child.child("password").getValue();
                    Double lat = (Double) child.child("lat").getValue();
                    Double lon = (Double) child.child("lon").getValue();

                    try {

                        if (child.child("username").getValue().toString().equals(Fusername.getText().toString()) && child.child("password").getValue().toString().equals(Fpassword.getText().toString()) && child.child("status").getValue().toString().equals("Admin")) {
                            //Toast.makeText(getApplicationContext(), "Anda berhasil login " + nama, Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(SignInActivity.this, AdminActivity.class);
                            startActivity(i);
                            System.exit(0);
                        }
                        if (child.child("username").getValue().toString().equals(Fusername.getText().toString()) && child.child("password").getValue().toString().equals(Fpassword.getText().toString()) && child.child("status").getValue().toString().equals("User")) {
                            //Toast.makeText(getApplicationContext(), "Anda berhasil login " + nama, Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(SignInActivity.this, UserActivity.class);
                            i.putExtra("key",child.getKey().toString());
                            i.putExtra("nama",nama);
                            i.putExtra("password",password);
                            i.putExtra("lat",lat);
                            i.putExtra("lon",lon);
                            startActivity(i);
                            System.exit(0);
                        }
                        if (child.child("username").getValue().toString().equals(Fusername.getText().toString()) && child.child("password").getValue().toString().equals(Fpassword.getText().toString()) && child.child("status").getValue().toString().equals("Customer")) {
                            //Toast.makeText(getApplicationContext(), "Anda berhasil login " + nama, Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(SignInActivity.this, CustomerDrawer.class);
                            i.putExtra("key",child.getKey().toString());
                            i.putExtra("nama",nama);
                            //i.putExtra("password",password);
                            //i.putExtra("lat",lat);
                            //i.putExtra("lon",lon);
                            startActivity(i);
                            System.exit(0);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Username dan password salah",Toast.LENGTH_LONG).show();
                    }
                    //Toast.makeText(getApplicationContext(),"Anda berhasil login "+password,Toast.LENGTH_LONG).show();

                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Apakan anda tetap ingin menutup aplikasi?");
            builder.setCancelable(false);

            listener = new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        finishAffinity();
                    }

                    if(which == DialogInterface.BUTTON_NEGATIVE){
                        dialog.cancel();
                    }
                }
            };
            builder.setPositiveButton("Ya",listener);
            builder.setNegativeButton("Tidak", listener);
            builder.show();

        }
        return super.onKeyDown(keyCode, event);
    }
}
