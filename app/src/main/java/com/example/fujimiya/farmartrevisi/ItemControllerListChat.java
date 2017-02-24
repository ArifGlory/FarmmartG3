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


public class ItemControllerListChat extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView title;
    TextView subTitle;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemControllerListChat(View itemView) {
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

    public String Sfrom,Sto;

    @Override
    public void onClick(View v) {
        view = v;
        //tampilkan toas ketika click
        urut = Integer.parseInt(String.format("%d", getAdapterPosition()));
        i = new Intent(v.getContext(),ChatActivity.class);
        i.putExtra("to",MyRecyclerAdapterListChat.Fto.get(urut));
        i.putExtra("from",MyRecyclerAdapterListChat.Ffrom.get(urut));
        i.putExtra("nama",ChatListFragment.nama);
        Toast.makeText(v.getContext(),"To : "+MyRecyclerAdapterListChat.Fto.get(urut)+" From : "+MyRecyclerAdapterListChat.Ffrom.get(urut),Toast.LENGTH_LONG).show();
        v.getContext().startActivity(i);
    }

}