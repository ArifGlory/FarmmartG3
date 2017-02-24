package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 12/25/16.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class ItemControllerListAnggota extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView title;
    TextView subTitle;
    TextView alamat;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemControllerListAnggota(View itemView) {
        super(itemView);

        //Set id
        cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);

        //Tambahan untuk id Picture

        kunci = "";
        //id Text
        title = (TextView) itemView.findViewById(R.id.listitem_name);
        subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);
        alamat = (TextView) itemView.findViewById(R.id.listitem_alamat);

        //onClick
        itemView.setOnClickListener(this);

    }


    public String kunci;
    public View view;
    public AlertDialog alg;
    public int urut;
    TextView Fnama,Fstatus,Femail,Falamat;
    public String Snama, Sstatus,Semail,Salamat,Spassword;
    Double lat,lon;
    AlertDialog alert;
    @Override
    public void onClick(View v) {
        view = v;
        //tampilkan toas ketika click
        urut = Integer.parseInt(String.format("%d", getAdapterPosition()));
        //i = new Intent(v.getContext(),DetailPendaftarActivity.class);
        Firebase.setAndroidContext(v.getContext());

        FUref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        FUref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                kunci = "";

                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    if (child.getKey().toString().equals(MyRecyclerAdapterListAnggota.Fkunci.get(urut))) {
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
                        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.dialog, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                        alertDialogBuilder.setView(v);

                        Fnama = (TextView) v.findViewById(R.id.nama);
                        Fstatus = (TextView) v.findViewById(R.id.status);
                        Femail = (TextView) v.findViewById(R.id.email);
                        Falamat = (TextView) v.findViewById(R.id.alamat);

                        Fnama.setText(Snama);
                        Fstatus.setText(" ("+Sstatus+")");
                        Femail.setText(Semail);
                        Falamat.setText(Salamat);

                        Button hapus = (Button) v.findViewById(R.id.btnHapus);
                        hapus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(view.getContext(), FUref.child(kunci).getKey().toString(), Toast.LENGTH_LONG).show();

                                alert.cancel();
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setMessage("Apakan anda ingin menghapus data?");
                                builder.setCancelable(false);

                                listener = new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(which == DialogInterface.BUTTON_POSITIVE){
                                            FUref.child(kunci).setValue(null);

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
                        });

                        alert = alertDialogBuilder.create();
                        alert.show();
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