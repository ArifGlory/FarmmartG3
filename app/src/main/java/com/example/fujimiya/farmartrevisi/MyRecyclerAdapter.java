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

public class MyRecyclerAdapter extends RecyclerView.Adapter<ItemController> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> FStatus = new ArrayList<String>();
    public static List<String> Fkunci = new ArrayList<String>();

    Firebase Fref;

    //Set List Items
    public void setCardList(final Context context) {

        notifyDataSetChanged();
        Firebase.setAndroidContext(this.context);

        Fref = new Firebase("https://farmartcorp.firebaseio.com/pendaftaran");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FNama.clear();
                FStatus.clear();
                Fkunci.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String nama = (String) child.child("username").getValue();
                    FNama.add(nama);
                    String status = (String) child.child("status").getValue();
                    FStatus.add(status);
                    String key = (String) child.getKey();
                    Fkunci.add(key);

                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public MyRecyclerAdapter(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapter(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemController onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle, viewGroup, false);
        ItemController viewHolder = new ItemController(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemController versionViewHolder, int i) {

        versionViewHolder.title.setText(FNama.get(i));
        versionViewHolder.subTitle.setText(FStatus.get(i));
    }

    @Override
    public int getItemCount() {

        return FNama == null ? 0 : FNama.size();
    }
}