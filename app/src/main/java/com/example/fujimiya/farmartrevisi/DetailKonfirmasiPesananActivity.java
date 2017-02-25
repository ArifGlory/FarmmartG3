package com.example.fujimiya.farmartrevisi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class DetailKonfirmasiPesananActivity extends AppCompatActivity {

    ImageView img_upload;
    TextView txtCustomer,txtMode,txtJumlah,txtTotal,txtTanggal,txtKomoditas,txtHarga;
    private String key,keyCus,keyjual;
    Intent i;
    private String nm,jmkh,komo,md,tg,ttl,hrg;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    IsiDataPublish isiDataPublish;
    Button btnPublish;
    Firebase Gref;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konfirmasi_pesanan);
        Firebase.setAndroidContext(this);
        Gref = new Firebase("https://farmartcorp.firebaseio.com");
        img_upload = (ImageView) findViewById(R.id.imgUpload);
        i = getIntent();
        keyCus = i.getStringExtra("kunciCus");
        nm = i.getStringExtra("namaCus");
        jmkh = i.getStringExtra("jumlahCus");
        hrg = i.getStringExtra("hargaCus");
        komo = i.getStringExtra("komoCus");
        md = i.getStringExtra("modeCus");
        tg = i.getStringExtra("timeCus");
        ttl = i.getStringExtra("totalCus");


        txtMode = (TextView) findViewById(R.id.txtmode);
        txtJumlah = (TextView) findViewById(R.id.txtjumlah);
        txtTotal = (TextView) findViewById(R.id.txttotal);
        txtTanggal = (TextView) findViewById(R.id.txttanggal);
        txtKomoditas = (TextView) findViewById(R.id.txtKomoditas);
        btnPublish = (Button) findViewById(R.id.btnKirim);
        btnPublish.setEnabled(false);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    isiDataPublish = new IsiDataPublish(filePath.getLastPathSegment(), hrg.toString(), "baik", keyCus, nm, komo);
                    Gref.child("buktipembayaran").push().setValue(isiDataPublish);
                    uploadFile(filePath.getLastPathSegment());

                } catch (Exception e) {
                    Toast.makeText(DetailKonfirmasiPesananActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(DetailKonfirmasiPesananActivity.this, "Anda Terhubung ke Server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        txtJumlah.setText(jmkh);
        txtKomoditas.setText(komo);
        txtTanggal.setText("on " + tg);
        txtTotal.setText(ttl);

    }

    public void Browse(View view) {

        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
            btnPublish.setEnabled(true);
        } catch (Exception e) {
            Toast.makeText(DetailKonfirmasiPesananActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    img_upload.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Toast.makeText(DetailKonfirmasiPesananActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void uploadFile(String nama) {
        storageReference = FirebaseStorage.getInstance().getReference();
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            String pic = "file/" + nama;
            StorageReference riversRef = storageReference.child(pic);
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                           // txtHarga.setText(null);
                            txtJumlah.setText(null);
                            txtKomoditas.setText(null);
                            txtTanggal.setText(null);
                            img_upload.setImageBitmap(null);
                            btnPublish.setEnabled(false);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }


}
