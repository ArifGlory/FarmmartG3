package com.example.fujimiya.farmartrevisi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderKomoditi extends RecyclerView.ViewHolder {

    public TextView txtNamaKomo,txtHargaKomo;
    public ImageView gmbrList;


    public RecycleViewHolderKomoditi(View itemView) {
        super(itemView);

        txtNamaKomo = (TextView) itemView.findViewById(R.id.txtNamaKom);
        txtHargaKomo = (TextView) itemView.findViewById(R.id.txtHarga);
        gmbrList = (ImageView) itemView.findViewById(R.id.imgKomo);


    }
}
