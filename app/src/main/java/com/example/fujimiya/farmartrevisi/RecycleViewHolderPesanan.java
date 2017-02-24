package com.example.fujimiya.farmartrevisi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderPesanan extends RecyclerView.ViewHolder {

    public TextView txtNamaCustomer,txtTanggal;

    public RecycleViewHolderPesanan(View itemView) {
        super(itemView);

        txtNamaCustomer = (TextView) itemView.findViewById(R.id.listitem_namaCustomer);
        txtTanggal = (TextView) itemView.findViewById(R.id.listitem_tanggal);

    }
}
