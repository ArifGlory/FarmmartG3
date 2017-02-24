package com.example.fujimiya.farmartrevisi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListKonfirmasiPesanan extends Fragment {


    public ListKonfirmasiPesanan() {
        // Required empty public constructor
    }


    RecyclerView recycler_konfirPesanan;
    RecycleAdapterKonfirPesanan adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_konfirmasi_pesanan, container, false);
        recycler_konfirPesanan = (RecyclerView) view.findViewById(R.id.recycleKonfirPesanan);

        adapter = new RecycleAdapterKonfirPesanan(view.getContext());
        recycler_konfirPesanan.setAdapter(adapter);
        recycler_konfirPesanan.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
