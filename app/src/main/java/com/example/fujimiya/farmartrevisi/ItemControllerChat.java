package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 12/25/16.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;


public class ItemControllerChat extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Variable
    CardView cardItemLayout;
    ImageView icon; // Picture
    TextView pesan;
    TextView nama;
    TextView times;
    RelativeLayout messageContainer;
    FrameLayout left_arrow;
    FrameLayout right_arrow;
    DialogInterface.OnClickListener listener;
    Firebase FUref;
    Intent i;

    public ItemControllerChat(View itemView) {
        super(itemView);

        left_arrow = (FrameLayout) itemView.findViewById(R.id.left_arrow);
        right_arrow = (FrameLayout) itemView.findViewById(R.id.right_arrow);
        messageContainer = (RelativeLayout) itemView.findViewById(R.id.message_container);
        pesan = (TextView) itemView.findViewById(R.id.message_text);
        nama = (TextView) itemView.findViewById(R.id.name_text);
        times = (TextView) itemView.findViewById(R.id.time_text);

        //onClick
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    }

}