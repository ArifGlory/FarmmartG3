package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 12/25/16.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class ItemControllerPublish extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView title;
    TextView subTitle;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemControllerPublish(View itemView) {
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

    public String Sgambar,Sharga,Sspesifikasi,Snama,Skunci,Skomoditi;

    @Override
    public void onClick(View v) {
        view = v;
        //tampilkan toas ketika click
        urut = Integer.parseInt(String.format("%d", getAdapterPosition()));
        i = new Intent(v.getContext(),DetailPublishActivity.class);

        Firebase.setAndroidContext(v.getContext());

        FUref = new Firebase("https://farmartcorp.firebaseio.com/registrasi");
        FUref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                kunci = "";

                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    if (child.child("kunci").getValue().toString().equals(MyRecyclerAdapterPublish.FKunci.get(urut))) {
                        String key = (String) child.getKey();
                        Sgambar = (String) child.child("gambar").getValue().toString();
                        Sharga = child.child("harga").getValue().toString();
                        Sspesifikasi = child.child("spesifikasi").getValue().toString();
                        Snama = child.child("nama").getValue().toString();
                        Skunci = child.child("kunci").getValue().toString();
                        Skomoditi = child.child("komoditi").getValue().toString();
                        kunci = key;
                        Toast.makeText(view.getContext(), FUref.child(key).getKey().toString(), Toast.LENGTH_LONG).show();
                        urut = 0;
                        i.putExtra("gambar",Sgambar);
                        i.putExtra("harga",Sharga);
                        i.putExtra("spesifikasi",Sspesifikasi);
                        i.putExtra("nama",Snama);
                        i.putExtra("kunci",kunci);
                        i.putExtra("room",Skunci);
                        i.putExtra("komoditi",Skomoditi);

                        view.getContext().startActivity(i);


                    }

                }
                } catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Data Telah Rusak", Toast.LENGTH_LONG).show();
                    //FUref.child(kunci).setValue(null);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

}