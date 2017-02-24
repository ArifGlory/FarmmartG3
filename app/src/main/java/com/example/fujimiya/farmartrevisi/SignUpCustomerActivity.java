package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SignUpCustomerActivity extends AppCompatActivity implements android.location.LocationListener{

    String Falamat;
    String Fstatus = "Customer";
    Firebase Fref;
    EditText email,username,password;
    IsiDataUser isiDataUser;
    private LocationManager locationManager;
    private String provider;
    Location lokasiterahir;
    Double Flat,Flon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        Location location = locationManager.getLastKnownLocation(provider);

        email = (EditText) findViewById(R.id.etEmail);
        username = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPass);


        Firebase.setAndroidContext(this);
        Fref = new Firebase("https://farmartcorp.firebaseio.com/pendaftaran");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(SignUpCustomerActivity.this,"Anda Terhubung ke Server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void signin(View view) {
        finish();
        Intent i = new Intent(SignUpCustomerActivity.this,SignInActivity.class);
        startActivity(i);
        System.exit(0);
    }


    public void create(View view) {
        if (lokasiterahir != null) {
            if(Falamat != null) {
                formcek();
                isiDataUser = new IsiDataUser(email.getText().toString(), username.getText().toString(), password.getText().toString(), Fstatus, Falamat, Flat, Flon);
                Fref.push().setValue(isiDataUser);
                clear();

                finish();
                Intent i = new Intent(SignUpCustomerActivity.this, SignInActivity.class);
                startActivity(i);
                System.exit(0);
            }
            else {
                Toast.makeText(getApplicationContext(),"Sedang mengambil lokasi", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Sedang mengambil lokasi", Toast.LENGTH_LONG).show();
        }
    }

    private void clear()
    {
        email.setText(null);
        username.setText(null);
        password.setText(null);
    }
    private boolean validateName() {
        if (username.getText().toString().trim().isEmpty()) {
            username.setError("Tidak boleh kosong!");
            username.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean validatepassword() {
        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Tidak boleh kosong!");
            password.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean validateemail() {
        if (email.getText().toString().trim().isEmpty()) {
            email.setError("Tidak boleh kosong!");
            email.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private void formcek() {

        if (!validateName()) {
            return;
        }
        if (!validatepassword()) {
            return;
        }

        if (!validateemail()) {
            return;
        }



    }

    @Override
    public void onLocationChanged(Location location) {

        lokasiterahir = location;
        double lat = (double) (location.getLatitude());
        double lon = (double) (location.getLongitude());
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {

            addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                Falamat = address+" "+city+" "+state+" "+country;
                Toast.makeText(getApplicationContext(),"Lokasi berhasil diambil", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),address+" "+city+" "+state+" "+country, Toast.LENGTH_LONG).show();

            }

        } catch (IOException e){
            e.printStackTrace();
        }

        Flat = lat;
        Flon = lon;


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
