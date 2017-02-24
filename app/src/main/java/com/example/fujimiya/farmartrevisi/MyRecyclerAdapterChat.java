package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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

public class MyRecyclerAdapterChat extends RecyclerView.Adapter<ItemControllerChat> {

    Context context;
    List<String> versionModels;

    //Text
    public static List<String> FFrom = new ArrayList<String>();
    public static List<String> FNama = new ArrayList<String>();
    public static List<String> FMessage = new ArrayList<String>();
    public static List<String> FTimestamp = new ArrayList<String>();
    public static List<String> FTo = new ArrayList<String>();
    public static List<String> FUserid = new ArrayList<String>();

    Firebase Fref;

    //Set List Items
    public void setCardList(final Context context) {

        notifyDataSetChanged();
        Firebase.setAndroidContext(this.context);

        Fref = new Firebase("https://farmartcorp.firebaseio.com/chat");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FMessage.clear();
                FTimestamp.clear();
                FNama.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String from = (String) child.child("from").getValue();
                    String to = (String) child.child("to").getValue();

                    if (from.equals(ChatActivity.name) && to.equals(ChatActivity.Sfrom) || from.equals(ChatActivity.Sfrom) && to.equals(ChatActivity.name) ) {

                        String pesan = (String) child.child("mesage").getValue();
                        FMessage.add(pesan);
                        String timestamp = (String) child.child("timestamp").getValue();
                        FTimestamp.add(timestamp);
                        String nama = (String) child.child("name").getValue();
                        FNama.add(nama);
                    }
                    //FFrom.add(from);

                }
                notifyDataSetChanged();
                //Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public MyRecyclerAdapterChat(Context context) {
        this.context = context;
        setCardList(context);
    }

    public MyRecyclerAdapterChat(List<String> versionModels) {
        this.versionModels = versionModels;

    }

    @Override
    public ItemControllerChat onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_message, viewGroup, false);
        ItemControllerChat viewHolder = new ItemControllerChat(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemControllerChat versionViewHolder, int i) {
        versionViewHolder.pesan.setText(FMessage.get(i));
        versionViewHolder.times.setText(FTimestamp.get(i));
        versionViewHolder.nama.setText(FNama.get(i));
        //ini buat kanan kiri
        if (FNama.get(i).equals(ChatActivity.name)) {
            versionViewHolder.messageContainer.setGravity(Gravity.RIGHT);
            versionViewHolder.left_arrow.setVisibility(View.GONE);
            versionViewHolder.right_arrow.setVisibility(View.VISIBLE);
        }
        else {
            versionViewHolder.messageContainer.setGravity(Gravity.LEFT);
            versionViewHolder.left_arrow.setVisibility(View.VISIBLE);
            versionViewHolder.right_arrow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        return FNama == null ? 0 : FNama.size();
    }


}