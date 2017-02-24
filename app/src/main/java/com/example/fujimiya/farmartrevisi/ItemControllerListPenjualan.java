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


public class ItemControllerListPenjualan extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView title;
    TextView subTitle;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemControllerListPenjualan(View itemView) {
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

    public String Snama, Sstatus, Semail, Salamat, Spassword;
    Double lat, lon;

    @Override
    public void onClick(View v) {
        view = v;
        //tampilkan toas ketika click
        urut = Integer.parseInt(String.format("%d", getAdapterPosition()));
        i = new Intent(v.getContext(), DetailListPenjualanActivity.class);
        Firebase.setAndroidContext(v.getContext());

        FUref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        FUref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    kunci = "";
                    //Toast.makeText(view.getContext(), MyRecyclerAdapterListPenjualan.FNama.get(urut), Toast.LENGTH_LONG).show();
                    for (DataSnapshot child : dataSnapshot.child(ListPenjualanFragment.kunci).child("zlist").getChildren()) {


                        if (child.getKey().equals(MyRecyclerAdapterListPenjualan.Fkunci.get(urut))) {

                        String key = (String) child.getKey();
                        String komo = (String) child.child("komoditi").getValue();
                        String harga = (String) child.child("harga").getValue();
                        String gambar = (String) child.child("gambar").getValue();
                        String spek = (String) child.child("spesifikasi").getValue();
                        kunci = key;
                        //Toast.makeText(view.getContext(), FUref.child(key).getKey().toString(), Toast.LENGTH_LONG).show();
                        urut = 0;

                        i.putExtra("kunci",kunci);
                        i.putExtra("komoditi",komo);
                        i.putExtra("harga",harga);
                        i.putExtra("gambar",gambar);
                        i.putExtra("spesifikasi",spek);
                        view.getContext().startActivity(i);


                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Data Telah Rusak", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

}