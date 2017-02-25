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
public class ListBuktiBayarFragment extends Fragment {


    public ListBuktiBayarFragment() {
        // Required empty public constructor
    }


    RecyclerView recycler_buktibayar;
    RecycleAdapterBuktiBayar adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_bukti_bayar, container, false);
        recycler_buktibayar = (RecyclerView) view.findViewById(R.id.recycleBuktibayar);
        adapter = new RecycleAdapterBuktiBayar(view.getContext());
        recycler_buktibayar.setAdapter(adapter);
        recycler_buktibayar.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
