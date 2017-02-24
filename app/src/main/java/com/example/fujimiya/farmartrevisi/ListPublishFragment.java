package com.example.fujimiya.farmartrevisi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListPublishFragment extends Fragment {

    RecyclerView rv;
    MyRecyclerAdapterPublish adapter;
    CardView cv;

    public ListPublishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_publish, container, false);

        cv = (CardView) view.findViewById(R.id.cardlist_item);
        rv = (RecyclerView) view.findViewById(R.id.home_recyclerview);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //Adapter
        if (adapter == null) {
            adapter = new MyRecyclerAdapterPublish(this.getActivity());
            rv.setAdapter(adapter);
        }

        return view;
    }

}
