package com.example.fujimiya.farmartrevisi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderBuktiBayar extends RecyclerView.ViewHolder {

    public TextView txtNamaCusBB,txtNamaKomoditiBB;
    public ImageView img_buktibayar;


    public RecycleViewHolderBuktiBayar(View itemView) {
        super(itemView);

        txtNamaCusBB = (TextView) itemView.findViewById(R.id.listitem_namaBukti);
        txtNamaKomoditiBB = (TextView) itemView.findViewById(R.id.listitem_komoditiBukti);
        img_buktibayar = (ImageView) itemView.findViewById(R.id.img_buktibayar);


    }
}
