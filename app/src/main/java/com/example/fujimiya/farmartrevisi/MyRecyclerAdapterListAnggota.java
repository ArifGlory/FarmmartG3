package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fujimiya on 12/25/16.
 */

public class MyRecyclerAdapterListAnggota extends RecyclerView.Adapter<ItemControllerListAnggota> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> FStatus = new ArrayList<String>();
    public static List<String> Fkunci = new ArrayList<String>();
    public static List<String> FAlamat = new ArrayList<String>();
    public static List<String> FEmail = new ArrayList<String>();
    Firebase Fref;

    //Set List Items
    public void setCardList(final Context context) {

        notifyDataSetChanged();
        Firebase.setAndroidContext(this.context);

        Fref = new Firebase("https://farmartcorp.firebaseio.com/anggota");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FNama.clear();
                FStatus.clear();
                FEmail.clear();
                FAlamat.clear();
                Fkunci.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String status = (String) child.child("status").getValue();
                    if (status.equals("Admin")) {

                    }
                    else {
                        String nama = (String) child.child("username").getValue();
                        FNama.add(nama);
                        String statusu = (String) child.child("status").getValue();
                        FStatus.add(statusu);
                        String email = (String) child.child("email").getValue();
                        FEmail.add(email);
                        String alamat = (String) child.child("alamat").getValue();
                        FAlamat.add(alamat);
                        String key = (String) child.getKey();
                        Fkunci.add(key);
                    }

                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public MyRecyclerAdapterListAnggota(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapterListAnggota(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemControllerListAnggota onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_list_anggota, viewGroup, false);
        ItemControllerListAnggota viewHolder = new ItemControllerListAnggota(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemControllerListAnggota versionViewHolder, int i) {

        versionViewHolder.title.setText(FNama.get(i) + " ("+FStatus.get(i)+")");
        versionViewHolder.subTitle.setText(FEmail.get(i));
        versionViewHolder.alamat.setText(FAlamat.get(i));
    }

    @Override
    public int getItemCount() {

        return FNama == null ? 0 : FNama.size();
    }
}