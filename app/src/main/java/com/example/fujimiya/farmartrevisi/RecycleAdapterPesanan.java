package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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
public class RecycleAdapterPesanan extends RecyclerView.Adapter<RecycleViewHolderPesanan> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> Glist_dari_namaCus = new ArrayList();
    public static List<String> Glist_dari_tanggalCus = new ArrayList();
    public static List<String> Glist_dari_keyCus = new ArrayList();
    public static List<String> Glist_dari_hargaCus = new ArrayList();
    public static List<String> Glist_dari_jumlahCus = new ArrayList();
    public static List<String> Glist_dari_keynyaCus = new ArrayList();
    public static List<String> Glist_dari_komoditiCus = new ArrayList();
    public static List<String> Glist_dari_modeCus = new ArrayList();
    public static List<String> Glist_dari_totalCus = new ArrayList();
    public static String namaCustomer;
    public static String tglCustomer;
    String key = "";
    Firebase Gref,refLagi;
    Bitmap bitmap;

    String[] nama ={"Berita 1 ","Berita 2","Berita 3"};

    public RecycleAdapterPesanan(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);
        key = UserActivity.kunci;
        Gref = new Firebase("https://farmartcorp.firebaseio.com/penjualan/").child(key);
        try {

            Gref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Glist_dari_namaCus.clear();
                    Glist_dari_tanggalCus.clear();
                    Glist_dari_keyCus.clear();
                    Glist_dari_hargaCus.clear();
                    Glist_dari_jumlahCus.clear();
                    Glist_dari_komoditiCus.clear();
                    Glist_dari_keynyaCus.clear();
                    Glist_dari_modeCus.clear();
                    Glist_dari_totalCus.clear();
                    for (DataSnapshot child : dataSnapshot.getChildren()){
                            String anam = child.child("costomer").getValue().toString();
                            Glist_dari_namaCus.add(anam);
                            String hrg = child.child("harga").getValue().toString();
                            Glist_dari_hargaCus.add(hrg);
                        String jml = child.child("jumlah").getValue().toString();
                        Glist_dari_jumlahCus.add(jml);
                        String keyyy = child.child("key").getValue().toString();
                        Glist_dari_keynyaCus.add(keyyy);
                        String komo = child.child("komoditi").getValue().toString();
                        Glist_dari_komoditiCus.add(komo);
                        String mod = child.child("modebayar").getValue().toString();
                        Glist_dari_modeCus.add(mod);
                            String lgt = child.child("timestamp").getValue().toString();
                            Glist_dari_tanggalCus.add(lgt);
                        String total = child.child("total").getValue().toString();
                        Glist_dari_totalCus.add(total);
                        String kunciCus = child.getKey();
                        Glist_dari_keyCus.add(kunciCus);
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
    public RecycleViewHolderPesanan onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_list_pesanan, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderPesanan viewHolderPesanan = new RecycleViewHolderPesanan(view);
        return viewHolderPesanan;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderPesanan holder, int position) {


        holder.txtNamaCustomer.setText(Glist_dari_namaCus.get(position).toString());
        holder.txtTanggal.setText(Glist_dari_tanggalCus.get(position).toString());

        holder.txtNamaCustomer.setOnClickListener(clicklistener);
        holder.txtTanggal.setOnClickListener(clicklistener);

        holder.txtNamaCustomer.setTag(holder);
        holder.txtTanggal.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderPesanan vHolder = (RecycleViewHolderPesanan) v.getTag();
            int position = vHolder.getPosition();
            //Toast.makeText(context.getApplicationContext(), "Kunci Cusnya : "+Glist_dari_keyCus.get(position).toString(), Toast.LENGTH_SHORT).show();
            i = new Intent(v.getContext(),DetailPemesananActivity.class);
            i.putExtra("kunciCus",Glist_dari_keynyaCus.get(position).toString());
            i.putExtra("namaCus",Glist_dari_namaCus.get(position).toString());
            i.putExtra("hargaCus",Glist_dari_hargaCus.get(position).toString());
            i.putExtra("jumlahCus",Glist_dari_jumlahCus.get(position).toString());
            i.putExtra("komoCus",Glist_dari_komoditiCus.get(position).toString());
            i.putExtra("modeCus",Glist_dari_modeCus.get(position).toString());
            i.putExtra("timeCus",Glist_dari_tanggalCus.get(position).toString());
            i.putExtra("totalCus",Glist_dari_totalCus.get(position).toString());
            context.startActivity(i);



        }
    };


    public int getItemCount() {

        return Glist_dari_namaCus == null ? 0 : Glist_dari_namaCus.size();

    }


}
