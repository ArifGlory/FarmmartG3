package com.example.fujimiya.farmartrevisi;


import android.content.Intent;
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
public class ListPesananTerimaFragment extends Fragment {


    public ListPesananTerimaFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerPesananterima;
    RecycleAdapterPesananTerima recycleAdapterPesananTerima;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pesanan_terima, container, false);
        recyclerPesananterima = (RecyclerView) view.findViewById(R.id.recyclePesananTerima);

        recycleAdapterPesananTerima = new RecycleAdapterPesananTerima(view.getContext());
        recyclerPesananterima.setAdapter(recycleAdapterPesananTerima);
        recyclerPesananterima.setLayoutManager(new LinearLayoutManager(view.getContext()));




        return view;
    }

}
