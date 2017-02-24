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

public class MyRecyclerAdapterListChat extends RecyclerView.Adapter<ItemControllerListChat> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> Fto = new ArrayList<String>();
    public static List<String> Ffrom = new ArrayList<String>();

    Firebase Fref;

    //Set List Items
    public void setCardList(final Context context) {

        notifyDataSetChanged();
        Firebase.setAndroidContext(this.context);

        Fref = new Firebase("https://farmartcorp.firebaseio.com/chat");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FNama.clear();
                Fto.clear();
                Ffrom.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String nama = (String) child.child("name").getValue();
                    String to = (String) child.child("to").getValue();
                    String from = (String) child.child("from").getValue();


                    if (to.equals(ChatListFragment.nama)) {

                        if (FNama.contains(nama)) {

                        } else {
                            FNama.add(nama);
                            Fto.add(to);
                            Ffrom.add(from);
                        }
                    }
                   //String key = (String) child.getKey();
                   // Fkunci.add(key);

                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public MyRecyclerAdapterListChat(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapterListChat(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemControllerListChat onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_list_chat, viewGroup, false);
        ItemControllerListChat viewHolder = new ItemControllerListChat(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemControllerListChat versionViewHolder, int i) {

        versionViewHolder.title.setText(FNama.get(i));
        //versionViewHolder.subTitle.setText(FStatus.get(i));
    }

    @Override
    public int getItemCount() {

        return FNama == null ? 0 : FNama.size();
    }
}