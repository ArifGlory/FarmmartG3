package com.example.fujimiya.farmartrevisi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterBuktiBayar extends RecyclerView.Adapter<RecycleViewHolderBuktiBayar> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> Glist_dari_namaBB = new ArrayList();
    public static List<String> Glist_dari_keyBB = new ArrayList();
    public static List<String> Glist_dari_hargaBB = new ArrayList();
    public static List<String> Glist_dari_komoditiBB = new ArrayList();
    public static List<String> Glist_dari_spekBB = new ArrayList();
    public static List<String> Glist_dari_gambarBB = new ArrayList();
    public static String namaCustomer;
    public static String tglCustomer;
    String key = "";
    Firebase Gref,refLagi;
    Bitmap bitmap;

    public RecycleViewHolderBuktiBayar viewHolderBuktiBayar;

    String[] nama ={"Berita 1 ","Berita 2","Berita 3"};

    public RecycleAdapterBuktiBayar(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);
        key = UserActivity.kunci;
        Gref = new Firebase("https://farmartcorp.firebaseio.com/buktipembayaran/");
        try {

            Gref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Glist_dari_namaBB.clear();
                    Glist_dari_keyBB.clear();
                    Glist_dari_hargaBB.clear();
                    Glist_dari_gambarBB.clear();
                    Glist_dari_komoditiBB.clear();
                    Glist_dari_spekBB.clear();

                    for (DataSnapshot child : dataSnapshot.getChildren()){
                            String anam = child.child("nama").getValue().toString();
                            Glist_dari_namaBB.add(anam);
                            String hrg = child.child("harga").getValue().toString();
                            Glist_dari_hargaBB.add(hrg);
                        String keyyy = child.child("kunci").getValue().toString();
                        Glist_dari_keyBB.add(keyyy);
                        String komo = child.child("komoditi").getValue().toString();
                        Glist_dari_komoditiBB.add(komo);
                        String gambar = child.child("gambar").getValue().toString();
                        Glist_dari_gambarBB.add(gambar);
                            String spek = child.child("spesifikasi").getValue().toString();
                            Glist_dari_gambarBB.add(spek);

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
    public RecycleViewHolderBuktiBayar onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_listbuktibayar, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        /*RecycleViewHolderBuktiBayar*/ viewHolderBuktiBayar = new RecycleViewHolderBuktiBayar(view);
        return viewHolderBuktiBayar;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderBuktiBayar holder, int position) {


        holder.txtNamaCusBB.setText(Glist_dari_namaBB.get(position).toString());
        holder.txtNamaKomoditiBB.setText(Glist_dari_komoditiBB.get(position).toString());
        //holder.img_buktibayar.setImageResource();
        showbyte(Glist_dari_gambarBB.get(position).toString());

        holder.txtNamaCusBB.setOnClickListener(clicklistener);
        holder.txtNamaKomoditiBB.setOnClickListener(clicklistener);
        holder.img_buktibayar.setOnClickListener(clicklistener);

        holder.txtNamaCusBB.setTag(holder);
        holder.txtNamaKomoditiBB.setTag(holder);
        holder.img_buktibayar.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderBuktiBayar vHolder = (RecycleViewHolderBuktiBayar) v.getTag();
            int position = vHolder.getPosition();
            //Toast.makeText(context.getApplicationContext(), "Kunci Cusnya : "+Glist_dari_keyCus.get(position).toString(), Toast.LENGTH_SHORT).show();
           /* i = new Intent(v.getContext(),DetailPemesananActivity.class);
            i.putExtra("kunciCus",Glist_dari_keynyaCus.get(position).toString());
            i.putExtra("namaCus",Glist_dari_namaCus.get(position).toString());
            i.putExtra("hargaCus",Glist_dari_hargaCus.get(position).toString());
            i.putExtra("jumlahCus",Glist_dari_jumlahCus.get(position).toString());
            i.putExtra("komoCus",Glist_dari_komoditiCus.get(position).toString());
            i.putExtra("modeCus",Glist_dari_modeCus.get(position).toString());
            i.putExtra("timeCus",Glist_dari_tanggalCus.get(position).toString());
            i.putExtra("totalCus",Glist_dari_totalCus.get(position).toString());
            context.startActivity(i);*/

        }
    };


    public int getItemCount() {

        return Glist_dari_namaBB == null ? 0 : Glist_dari_namaBB.size();
       // return 2;

    }

    private void showbyte(String nama){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://farmartcorp.appspot.com/file/");
        StorageReference islandRef = storageRef.child(nama);
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
               viewHolderBuktiBayar.img_buktibayar.setImageBitmap(bitmap);
            }
        });
    }

}
