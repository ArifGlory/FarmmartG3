package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 12/25/16.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class ItemController extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView title;
    TextView subTitle;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemController(View itemView) {
        super(itemView);

        //Set id
        cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);

        //Tambahan untuk id Picture

        kunci = "";
        //id Text
        title = (TextView) itemView.findViewById(R.id.listitem_name);
        subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);

        //onClick
        itemView.setOnClickListener(this);

    }


    public String kunci;
    public View view;
    public AlertDialog alg;
    public int urut;

    public String Snama, Sstatus,Semail,Salamat,Spassword;
    Double lat,lon;

    @Override
    public void onClick(View v) {
        view = v;
        //tampilkan toas ketika click
        urut = Integer.parseInt(String.format("%d", getAdapterPosition()));
        i = new Intent(v.getContext(),DetailPendaftarActivity.class);
        Firebase.setAndroidContext(v.getContext());

        FUref = new Firebase("https://farmartcorp.firebaseio.com/pendaftaran");
        FUref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {


                kunci = "";

                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    if (child.getKey().toString().equals(MyRecyclerAdapter.Fkunci.get(urut))) {
                        String key = (String) child.getKey();
                        Snama = child.child("username").getValue().toString();
                        Sstatus = child.child("status").getValue().toString();
                        Semail = child.child("email").getValue().toString();
                        Salamat = child.child("alamat").getValue().toString();
                        Spassword = child.child("password").getValue().toString();
                        lat = (Double) child.child("lat").getValue();
                        lon = (Double) child.child("lon").getValue();
                        kunci = key;
                        //Toast.makeText(view.getContext(), FUref.child(key).getKey().toString(), Toast.LENGTH_LONG).show();
                        urut = 0;
                        i.putExtra("key",kunci);
                        i.putExtra("nama",Snama);
                        i.putExtra("email",Semail);
                        i.putExtra("status",Sstatus);
                        i.putExtra("alamat",Salamat);
                        i.putExtra("password",Spassword);
                        i.putExtra("lat",lat);
                        i.putExtra("lon",lon);

                        view.getContext().startActivity(i);


                    }

                }
                } catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Data Telah Rusak", Toast.LENGTH_LONG).show();
                    FUref.child(kunci).setValue(null);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

}