package com.example.fujimiya.farmartrevisi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderPesananTerima extends RecyclerView.ViewHolder {

    public TextView txtNamaCustomerTerima,txtTanggalTerima;

    public RecycleViewHolderPesananTerima(View itemView) {
        super(itemView);

        txtNamaCustomerTerima = (TextView) itemView.findViewById(R.id.listitem_namaCustomerTerima);
        txtTanggalTerima = (TextView) itemView.findViewById(R.id.listitem_tanggalTerima);

    }
}
