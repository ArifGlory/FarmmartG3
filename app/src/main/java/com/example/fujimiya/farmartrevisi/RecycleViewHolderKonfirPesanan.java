package com.example.fujimiya.farmartrevisi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderKonfirPesanan extends RecyclerView.ViewHolder {

    public TextView txtNamaKomoditi,txtTanggal;

    public RecycleViewHolderKonfirPesanan(View itemView) {
        super(itemView);

        txtNamaKomoditi = (TextView) itemView.findViewById(R.id.listitem_namaKomoditi);
        txtTanggal = (TextView) itemView.findViewById(R.id.listitem_tanggal);

    }
}
