package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

public class BeliActivity extends AppCompatActivity {

    String Skomoditi,Sharga,Sgambar,Skunci,Smodebayar;
    TextView komoditi,harga,jumlah,total;
    ImageView gambar;
    int Ijumlah = 0;
    int Itotal = 0;

    Firebase Fref;
    String timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli);

        Firebase.setAndroidContext(this);
        Fref = new Firebase("https://farmartcorp.firebaseio.com/penjualan");

        try {
            komoditi = (TextView) findViewById(R.id.nama);
            gambar = (ImageView) findViewById(R.id.gambar);
            harga = (TextView) findViewById(R.id.harga);
            jumlah = (TextView) findViewById(R.id.jumlah);
            total = (TextView) findViewById(R.id.total);

            Intent i = getIntent();
            Skomoditi = i.getStringExtra("komoditi");
            Sharga = i.getStringExtra("harga");
            Sgambar = i.getStringExtra("gambar");


            //Toast.makeText(this,""+Itotal+Ijumlah,Toast.LENGTH_LONG).show();
            komoditi.setText(Skomoditi);
            showbyte(Sgambar);
            harga.setText(Sharga);
            jumlah.setText(""+Ijumlah);
            total.setText(""+Itotal);
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
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
                gambar.setImageBitmap(bitmap);
            }
        });

    }

    public void plus(View view) {
        Ijumlah++;
        Itotal = Ijumlah * Integer.parseInt(Sharga);
        jumlah.setText(""+Ijumlah);
        total.setText(""+Itotal);
    }

    public void min(View view) {
        if (Ijumlah > 0)
        {
            Ijumlah--;
            Itotal = Ijumlah * Integer.parseInt(Sharga);
            jumlah.setText(""+Ijumlah);
            total.setText(""+Itotal);
        }
    }

    public void carabayar(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.transfer:
                if (checked)
                    Smodebayar = "Transfer";
                   Toast.makeText(this,"Transfer",Toast.LENGTH_LONG).show();
                    break;
            case R.id.cod:
                if (checked)
                    Smodebayar = "COD";
                    Toast.makeText(this,"COD",Toast.LENGTH_LONG).show();
                    break;
        }
    }

    public void Beli(View view) {
        try
        {

            if (Smodebayar.equals("COD")){
                Intent a = new Intent(this,ChatActivity.class);
                a.putExtra("to",ProfilUserActivity.userTerima);
                a.putExtra("from",ProfilUserActivity.userTerima);
                a.putExtra("nama",CustomerDrawer.name);
                startActivity(a);
            }else {

                Calendar calendar = Calendar.getInstance();
                int bulan = calendar.get(Calendar.MONTH) + 1;
                //timestamp = ""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR);

                long oneDayInMillis = 24 * 60 * 60 * 1000;
                long timeDifference = System.currentTimeMillis() - System.currentTimeMillis();

                if (timeDifference < oneDayInMillis) {
                    //formattedTime = DateFormat.format("hh:mm a", System.currentTimeMillis()).toString();
                    //Toast.makeText(this,""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR)+" "+DateFormat.format("hh:mm a", System.currentTimeMillis()).toString(),Toast.LENGTH_LONG).show();
                    timestamp = "" + calendar.get(Calendar.DATE) + "-" + bulan + "-" + calendar.get(Calendar.YEAR) + " " + DateFormat.format("hh:mm a", System.currentTimeMillis()).toString();

                } else {
                    //formattedTime = DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString();
                    //Toast.makeText(this,""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR)+" "+DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString(),Toast.LENGTH_LONG).show();
                    timestamp = "" + calendar.get(Calendar.DATE) + " - " + bulan + " - " + calendar.get(Calendar.YEAR) + " " + DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString();

                }

                BeliModel beliModel = new BeliModel(timestamp, Skomoditi, Sharga, "" + Ijumlah, "" + Itotal, Smodebayar, Sgambar, CustomerDrawer.name, CustomerDrawer.konci);
                Fref.child(ProfilUserActivity.keyF).push().setValue(beliModel);
                Toast.makeText(this, "Transaksi Berhasil, tunggu sampai pesanan anda diproses", Toast.LENGTH_LONG).show();
                finish();
            }

        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }
}
