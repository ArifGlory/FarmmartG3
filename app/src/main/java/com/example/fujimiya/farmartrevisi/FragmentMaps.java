package com.example.fujimiya.farmartrevisi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
public class FragmentMaps extends Fragment implements OnMapReadyCallback {


    public FragmentMaps() {
        // Required empty public constructor
    }



    Firebase ref;
    private GoogleMap mMap;
    public Marker marker_ghost;
    Intent i;
    private String key,alamat,email;
    AutoCompleteTextView Gcari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_maps, container, false);
        Firebase.setAndroidContext(this.getActivity());
        ref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        //Gcari = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextCari);
        final FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();

        fragment.getMapAsync(this);

        return  view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //mMap.setMyLocationEnabled(true);

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
                          //i.putExtra("key",marker.getSnippet());
                            startActivity(i);
                   }
               });


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

    public void cari_komoditi() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mMap != null) {
                    mMap.clear();
                }
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String Fjenis = (String) child.child("komoditi").getValue();
                    if (child.child("komoditi").getValue().toString().equals(Gcari.getText().toString())) {
                        String nama = (String) child.child("nama").getValue();
                        String mail = (String) child.child("email").getValue();
                        String komoditi = (String) child.child("komoditi").getValue();
                        String key = (String) child.getKey();

                        Double latt = (Double) child.child("lat").getValue();
                        Double lonn = (Double) child.child("lon").getValue();
                        LatLng FlKomoditi = new LatLng(latt, lonn);

                        mMap.addMarker(new MarkerOptions().position(FlKomoditi).title("\rPetani      : " + nama + "\n\r" + "Komoditi : " + komoditi + "\n\r" +
                                "Email    : " + mail));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FlKomoditi, 17));

                        //Toast.makeText(getActivity().getApplication(), nama, Toast.LENGTH_LONG).show();
                    }


                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
