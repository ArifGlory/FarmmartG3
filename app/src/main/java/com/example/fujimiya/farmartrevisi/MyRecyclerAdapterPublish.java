package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by fujimiya on 12/25/16.
 */

public class MyRecyclerAdapterPublish extends RecyclerView.Adapter<ItemControllerPublish> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> FHarga = new ArrayList<String>();
    public static List<String> FKunci = new ArrayList<String>();

    Firebase Frefreg,Frefanggota;

    //Set List Items
    public void setCardList(final Context context) {

        notifyDataSetChanged();
        Firebase.setAndroidContext(this.context);

        Frefreg = new Firebase("https://farmartcorp.firebaseio.com/registrasi");
        Frefreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

                FHarga.clear();
                FNama.clear();
                FKunci.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String harga = (String) child.child("harga").getValue();
                    FHarga.add(harga);
                    String nama = (String) child.child("nama").getValue();
                    FNama.add(nama);
                    String kunci = (String) child.child("kunci").getValue();
                    FKunci.add(kunci);
                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public MyRecyclerAdapterPublish(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapterPublish(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemControllerPublish onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_publish, viewGroup, false);
        ItemControllerPublish viewHolder = new ItemControllerPublish(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemControllerPublish versionViewHolder, int i) {

        versionViewHolder.title.setText(FNama.get(i));
        versionViewHolder.subTitle.setText(FHarga.get(i));
    }

    @Override
    public int getItemCount() {

        return FHarga == null ? 0 : FHarga.size();
    }
}