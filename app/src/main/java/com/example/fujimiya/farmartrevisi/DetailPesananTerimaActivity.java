package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class DetailPesananTerimaActivity extends AppCompatActivity {

    TextView txtCustomer,txtMode,txtJumlah,txtTotal,txtTanggal,txtKomoditas,txtHarga;
    Firebase Gref,GrefTerima;
    private String key,keyCus,keyjual;
    Intent i;
    private String nm,jmkh,komo,md,tg,ttl,hrg,keycusnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_terima);
        Firebase.setAndroidContext(this);
        i = getIntent();

      //  keyCus = i.getStringExtra("kunciCus");
        nm = i.getStringExtra("namaCus");
        jmkh = i.getStringExtra("jumlahCus");
        hrg = i.getStringExtra("hargaCus");
        komo = i.getStringExtra("komoCus");
        md = i.getStringExtra("modeCus");
        tg = i.getStringExtra("timeCus");
        ttl = i.getStringExtra("totalCus");
        keycusnya = i.getStringExtra("kunciCus");
        Gref = new Firebase("https://farmartcorp.firebaseio.com/konfirmasi-pesanan/").child(keycusnya);
        GrefTerima = new Firebase("https://farmartcorp.firebaseio.com/pesananterima/");

        txtCustomer = (TextView) findViewById(R.id.nama);
        txtMode = (TextView) findViewById(R.id.txtmode);
        txtJumlah = (TextView) findViewById(R.id.txtjumlah);
        txtTotal = (TextView) findViewById(R.id.txttotal);
        txtTanggal = (TextView) findViewById(R.id.txttanggal);
        txtKomoditas = (TextView) findViewById(R.id.txtKomoditas);

        txtCustomer.setText(nm);
        txtJumlah.setText(jmkh);
        txtKomoditas.setText(komo);
        txtMode.setText(md);
        txtTanggal.setText("on " + tg);
        txtTotal.setText(ttl);

    }

    public void Proses(View view) {

        PesananTerimaModel pesanTerima = new PesananTerimaModel(nm.toString(),jmkh.toString(),
                komo,hrg,md,
                tg,ttl,keycusnya);
        Gref.push().setValue(pesanTerima);
       // GrefTerima.child(key).child(keyCus).setValue(null);
        Toast.makeText(this, "Pesanan diterima", Toast.LENGTH_SHORT).show();
        i = new Intent(this,AdminActivity.class);
        startActivity(i);


    }

    public void Abaikan(View view) {

    }
}
