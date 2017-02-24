package com.example.fujimiya.farmartrevisi;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListPesananFragment extends Fragment {


    public ListPesananFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerPesanan;
    RecycleAdapterPesanan adapterPesanan;
    Intent i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pesanan, container, false);
        recyclerPesanan = (RecyclerView) view.findViewById(R.id.recyclePesanan);
        Toast.makeText(view.getContext()," "+UserActivity.kunci,Toast.LENGTH_SHORT).show();
        //recyclerPesanan.setHasFixedSize(true);
        //recyclerPesanan.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerPesanan.setItemAnimator(new DefaultItemAnimator());

        adapterPesanan = new RecycleAdapterPesanan(view.getContext());
        recyclerPesanan.setAdapter(adapterPesanan);
        recyclerPesanan.setLayoutManager(new LinearLayoutManager(view.getContext()));



        return view;
    }

}
