package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterPesananTerima extends RecyclerView.Adapter<RecycleViewHolderPesananTerima> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> Glist_dari_namaPT = new ArrayList();
    public static List<String> Glist_dari_tanggalPT= new ArrayList();
    public static List<String> Glist_dari_keyCusPT = new ArrayList();
    public static List<String> Glist_dari_hargaPT= new ArrayList();
    public static List<String> Glist_dari_jumlahPT= new ArrayList();
    public static List<String> Glist_dari_keynyaCusPT = new ArrayList();
    public static List<String> Glist_dari_komoditiPT= new ArrayList();
    public static List<String> Glist_dari_modePT= new ArrayList();
    public static List<String> Glist_dari_totalPT= new ArrayList();
    public static String namaCustomer;
    public static String tglCustomer;
    String key = "";
    Firebase Gref,refLagi;
    Bitmap bitmap;

    String[] nama ={"Berita 1 ","Berita 2","Berita 3"};

    public RecycleAdapterPesananTerima(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);

        Gref = new Firebase("https://farmartcorp.firebaseio.com/pesananterima/");
        try {

            Gref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Glist_dari_namaPT.clear();
                    Glist_dari_tanggalPT.clear();
                    Glist_dari_keyCusPT.clear();
                    Glist_dari_hargaPT.clear();
                    Glist_dari_tanggalPT.clear();
                    Glist_dari_jumlahPT.clear();
                    Glist_dari_komoditiPT.clear();
                    Glist_dari_keynyaCusPT.clear();
                    Glist_dari_totalPT.clear();
                    Glist_dari_modePT.clear();
                    for (DataSnapshot child : dataSnapshot.getChildren()){
                            String anam = child.child("customer").getValue().toString();
                            Glist_dari_namaPT.add(anam);
                            String lgt = child.child("tanggal").getValue().toString();
                            Glist_dari_tanggalPT.add(lgt);
                            String hrg = child.child("hargakomoditi").getValue().toString();
                            Glist_dari_hargaPT.add(hrg);
                        String keyyy = child.child("keycustomer").getValue().toString();
                        Glist_dari_keynyaCusPT.add(keyyy);
                        String jml = child.child("jumlah").getValue().toString();
                        Glist_dari_jumlahPT.add(jml);
                        String komo = child.child("komoditas").getValue().toString();
                        Glist_dari_komoditiPT.add(komo);
                        String md = child.child("mode").getValue().toString();
                        Glist_dari_modePT.add(md);
                        String ttl = child.child("total").getValue().toString();
                        Glist_dari_totalPT.add(ttl);

                    }
                    notifyDataSetChanged();
                    Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            //Toast.makeText(context.getApplicationContext(), "Data ke 0 = "+Glist_dari_namaCus.get(0).toString(), Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){

            Log.e("Erornya","ErorAdapter"+e);
        }

    }


    @Override
    public RecycleViewHolderPesananTerima onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_list_pesananterima, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderPesananTerima viewHolderPesananTerima = new RecycleViewHolderPesananTerima(view);
        return viewHolderPesananTerima;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderPesananTerima holder, int position) {


        holder.txtNamaCustomerTerima.setText(Glist_dari_namaPT.get(position).toString());
        holder.txtTanggalTerima.setText(Glist_dari_tanggalPT.get(position).toString());

        holder.txtNamaCustomerTerima.setOnClickListener(clicklistener);
        holder.txtTanggalTerima.setOnClickListener(clicklistener);

        holder.txtNamaCustomerTerima.setTag(holder);
        holder.txtTanggalTerima.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderPesananTerima vHolder = (RecycleViewHolderPesananTerima) v.getTag();
            int position = vHolder.getPosition();
            //Toast.makeText(context.getApplicationContext(), "Kunci Cusnya : "+Glist_dari_keyCus.get(position).toString(), Toast.LENGTH_SHORT).show();
            i = new Intent(v.getContext(),DetailPesananTerimaActivity.class);
            i.putExtra("kunciCus",Glist_dari_keynyaCusPT.get(position).toString());
            i.putExtra("namaCus",Glist_dari_namaPT.get(position).toString());
            i.putExtra("hargaCus",Glist_dari_hargaPT.get(position).toString());
            i.putExtra("jumlahCus",Glist_dari_jumlahPT.get(position).toString());
            i.putExtra("komoCus",Glist_dari_komoditiPT.get(position).toString());
            i.putExtra("modeCus",Glist_dari_modePT.get(position).toString());
            i.putExtra("timeCus",Glist_dari_tanggalPT.get(position).toString());
            i.putExtra("totalCus",Glist_dari_totalPT.get(position).toString());
            context.startActivity(i);

        }
    };


    public int getItemCount() {

        //return nama.length;
        return Glist_dari_namaPT == null ? 0 : Glist_dari_namaPT.size();

    }


}
