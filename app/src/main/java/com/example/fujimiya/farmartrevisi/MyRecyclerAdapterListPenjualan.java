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

public class MyRecyclerAdapterListPenjualan extends RecyclerView.Adapter<ItemControllerListPenjualan> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> FHarga = new ArrayList<String>();
    public static List<String> Fkunci = new ArrayList<String>();


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
                FHarga.clear();

                for (DataSnapshot child : dataSnapshot.child(ListPenjualanFragment.kunci).child("zlist").getChildren()) {
                    String nama = (String) child.child("komoditi").getValue();
                    FNama.add(nama);
                    String harga = (String) child.child("harga").getValue();
                    FHarga.add(harga);
                    String key = (String) child.getKey();
                    Fkunci.add(key);


                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), ""+dataSnapshot.child("-KaDLRKumCb6giy4ThYu").child("zlist").child("-KaH0wIQ9WQsTPhir6UA").child("komoditi").getValue(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public MyRecyclerAdapterListPenjualan(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapterListPenjualan(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemControllerListPenjualan onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle, viewGroup, false);
        ItemControllerListPenjualan viewHolder = new ItemControllerListPenjualan(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemControllerListPenjualan versionViewHolder, int i) {

        versionViewHolder.title.setText(FNama.get(i));
        versionViewHolder.subTitle.setText(FHarga.get(i));
    }

    @Override
    public int getItemCount() {

        return FNama == null ? 0 : FNama.size();
    }
}