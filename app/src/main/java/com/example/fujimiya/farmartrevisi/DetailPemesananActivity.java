package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DetailPemesananActivity extends AppCompatActivity {

    TextView txtCustomer,txtMode,txtJumlah,txtTotal,txtTanggal,txtKomoditas,txtHarga;
    Firebase Gref,GrefTerima;
    private String key,keyCus,keyjual;
    Intent i;
    private String nm,jmkh,komo,md,tg,ttl,hrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);
        Firebase.setAndroidContext(this);
        i = getIntent();
        keyCus = i.getStringExtra("kunciCus");
        nm = i.getStringExtra("namaCus");
        jmkh = i.getStringExtra("jumlahCus");
        hrg = i.getStringExtra("hargaCus");
        komo = i.getStringExtra("komoCus");
        md = i.getStringExtra("modeCus");
        tg = i.getStringExtra("timeCus");
        ttl = i.getStringExtra("totalCus");
        key = UserActivity.kunci;
        Gref = new Firebase("https://farmartcorp.firebaseio.com/penjualan/");
        GrefTerima = new Firebase("https://farmartcorp.firebaseio.com/pesananterima/");

        txtCustomer = (TextView) findViewById(R.id.nama);
        txtJumlah = (TextView) findViewById(R.id.txtjumlah);
        txtTotal = (TextView) findViewById(R.id.txttotal);
        txtTanggal = (TextView) findViewById(R.id.txttanggal);
        txtKomoditas = (TextView) findViewById(R.id.txtKomoditas);
        txtMode = (TextView) findViewById(R.id.txtmode);
        String url = Gref.toString();

        txtCustomer.setText(nm);
        txtJumlah.setText(jmkh);
        txtKomoditas.setText(komo);
        txtMode.setText(md);
        txtTanggal.setText("on " + tg);
        txtTotal.setText(ttl);
        //Toast.makeText(DetailPemesananActivity.this, ""+url, Toast.LENGTH_LONG).show();


        /*try {
            Gref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if (child.getKey().equals(keyCus)) {
                            String anam = child.child("costomer").getValue().toString();
                            String gmbr = child.child("gambar").getValue().toString();
                            String harga = child.child("harga").getValue().toString();
                            String jumlah = child.child("jumlah").getValue().toString();
                            String komoditi = child.child("komoditi").getValue().toString();
                            String mode = child.child("modebayar").getValue().toString();
                            String tgl = child.child("timestamp").getValue().toString();
                            String total = child.child("total").getValue().toString();

                            nm = anam;
                            jmkh = jumlah;
                            komo = komoditi;
                            md = mode;
                            tg = tgl;
                            ttl = total;
                            hrg = harga;

                            txtCustomer.setText(anam);
                            txtJumlah.setText(jumlah);
                            txtKomoditas.setText(komoditi);
                            txtMode.setText(mode);
                            txtTanggal.setText("on " + tgl);
                            txtTotal.setText(total);
                       }
                    }

                    Toast.makeText(DetailPemesananActivity.this, "Data berhasil diambil "+keyjual, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch (Exception e){
            Log.e("Erornya ",""+e);
        }*/

    }

    public void Terima(View view) {

       // try {
           //disini bakal masukin ke child pesananterima di firebase
            PesananTerimaModel pesanTerima = new PesananTerimaModel(nm.toString(),jmkh.toString(),
                                            komo,hrg,md,
                                            tg,ttl,keyCus);
            GrefTerima.push().setValue(pesanTerima);
        Gref.child(key).child(keyCus).setValue(null);
            Toast.makeText(DetailPemesananActivity.this, "Pesanan diterima", Toast.LENGTH_SHORT).show();
            i = new Intent(this,UserActivity.class);
            startActivity(i);
      /*  }catch (Exception e){
            Log.e("Erornya ",""+e);
        }*/
    }

    public void Abaikan(View view) {

        Gref.child(key).child(keyCus).setValue(null);
        i = new Intent(this,UserActivity.class);
        startActivity(i);
        Toast.makeText(DetailPemesananActivity.this, "Pesanan dihapus", Toast.LENGTH_SHORT).show();
    }
}
