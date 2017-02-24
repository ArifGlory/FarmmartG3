package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterKomoditi extends RecyclerView.Adapter<RecycleViewHolderKomoditi> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> Glist_dari_judul = new ArrayList();
    public static List<String> Glist_dari_isi = new ArrayList();
    public static List<String> Glist_dari_gambar = new ArrayList();
    public static String GpubjudulEdit;
    public static String GpubisiEdit;
    Firebase Gref;
    Bitmap bitmap;


    //dekalrasi buat List nya
    String[] nama ={"Berita 1 ","Berita 2","Berita 3"};



    //int ic_aja = R.drawable.greencircle;

    public RecycleAdapterKomoditi(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);

        try {


            Gref = new Firebase("https://farmartcorp.firebaseio.com/anggota").child(ProfilUserActivity.keyF).child("zlist");
            Gref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    notifyDataSetChanged();
                    //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        catch (Exception e){

            Log.e("Erornya","ErorAdapter"+e);
        }

        //Glist_dari_berita.add("Berita di arraylist1");

    }


    @Override
    public RecycleViewHolderKomoditi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderKomoditi viewHolderKomoditi = new RecycleViewHolderKomoditi(view);
        return viewHolderKomoditi;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderKomoditi holder, int position) {



        //holder.checkBoxJudul.setText(nama[position]);
        //holder.txtNamaOutlet.setText(nama[position]);
        //holder.txtNamaKomo.setText(nama[position]);
       // holder.txtHargaKomo.setText("Harga : " + Glist_dari_isi.get(position).toString());
        //holder.txtNamaKomo.setText("Komoditi : "+ Glist_dari_judul.get(position).toString());
        //holder.gmbrList.setImageBitmap(StringToBitMap(Glist_dari_gambar.get(position).toString()));
        //holder.txtHargaKomo.setText(Glist_dari_isi.get(position).toString());


        holder.txtNamaKomo.setOnClickListener(clicklistener);


        holder.txtNamaKomo.setTag(holder);
        holder.txtHargaKomo.setTag(holder);
        holder.gmbrList.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Toast.makeText(context.getApplicationContext(), "Key nya : "+ ProfilUserActivity.keyF, Toast.LENGTH_SHORT).show();
        }
    };


    public int getItemCount() {
        //return nama.length;

        return Glist_dari_judul == null ? 0 : Glist_dari_judul.size();
       // return nama.length;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
