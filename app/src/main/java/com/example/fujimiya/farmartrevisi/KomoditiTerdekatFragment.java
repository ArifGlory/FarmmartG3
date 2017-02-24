package com.example.fujimiya.farmartrevisi;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class KomoditiTerdekatFragment extends Fragment implements OnMapReadyCallback {


    public KomoditiTerdekatFragment() {
        // Required empty public constructor
    }


    Firebase ref;
    private GoogleMap mMap;
    public Marker marker_ghost;
    public LatLng me;
    Intent i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_komoditi_terdekat, container, false);
        Firebase.setAndroidContext(this.getActivity());
        ref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        final FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();

        fragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        if (me == null) {
            ambil_lokasiku();
        }

        mMap.clear();
        LatLng lampung = new LatLng(-5.382351, 105.257791);
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CircleOptions mOptions = new CircleOptions()
                .center(lampung).radius(100)
                .strokeColor(0x110000FF).strokeWidth(8).fillColor(0x110000FF);
        mMap.addCircle(mOptions);

        mMap.addMarker(new MarkerOptions().position(lampung).title("lokasi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lampung, 17));

        ambil();
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {

                marker_ghost = marker;
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.dialog_customer, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(v);
                TextView textView = (TextView) v.findViewById(R.id.txttitle);
                Button btnKeProfil = (Button) v.findViewById(R.id.btnkeProfil);

                textView.setText(marker.getTitle());
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();


                btnKeProfil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i = new Intent(getActivity(),ProfilUserActivity.class);
                        i.putExtra("userKirim",marker.getTitle());
                        startActivity(i);
                    }
                });


            }
        });



    }

    public void ambil_lokasiku() {
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                me = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("It's Me!"));

            }
        });

    }

    public void ambil() {

        try {

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (mMap != null) {
                        mMap.clear();
                    }
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String status = (String) child.child("status").getValue();
                        if (status.equals("User")) {

                            String username = (String) child.child("username").getValue();

                            String key = (String) child.getKey();

                            Double latt = (Double) child.child("lat").getValue();
                            Double lonn = (Double) child.child("lon").getValue();
                            LatLng FlKomoditi = new LatLng(latt, lonn);
                            //Toast.makeText(getActivity().getApplication(),""+FlKomoditi ,Toast.LENGTH_LONG).show();

                            mMap.addMarker(new MarkerOptions().position(FlKomoditi).title(username));


                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FlKomoditi, 17));
                        }

                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        }catch (Exception e ){
            Log.e("Eror Maps Ambildata","Erornya : "+e);
        }


    }
}
