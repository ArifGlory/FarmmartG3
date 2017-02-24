package com.example.fujimiya.farmartrevisi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PublishActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    ImageView gambar;
    EditText Fharga, Fspesifikasi, Fnama;
    Bitmap bitmap;
    String Sgambar, kunci, nama;
    Firebase Fref;
    Button publish;
    private StorageReference storageReference;
    IsiDataPublish isiDataPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        Intent i = getIntent();
        kunci = i.getStringExtra("kunci");
        nama = i.getStringExtra("nama");
        gambar = (ImageView) findViewById(R.id.image);

        Fharga = (EditText) findViewById(R.id.harga);
        Fspesifikasi = (EditText) findViewById(R.id.spesifikasi);
        Fnama = (EditText) findViewById(R.id.nama);
        publish = (Button) findViewById(R.id.publis);

        publish.setEnabled(false);

        Firebase.setAndroidContext(this);
        Fref = new Firebase("https://farmartcorp.firebaseio.com");
        Fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(PublishActivity.this, "Anda Terhubung ke Server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void browse(View view) {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
            publish.setEnabled(true);
        } catch (Exception e) {
            Toast.makeText(PublishActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
                    gambar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Toast.makeText(PublishActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
                            Fharga.setText(null);
                            Fnama.setText(null);
                            Fspesifikasi.setText(null);
                            gambar.setImageBitmap(null);
                            publish.setEnabled(false);
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


    public void publish(View view) {
        try {
            isiDataPublish = new IsiDataPublish(filePath.getLastPathSegment(), Fharga.getText().toString(), Fspesifikasi.getText().toString(), kunci, nama, Fnama.getText().toString());
            Fref.child("registrasi").push().setValue(isiDataPublish);
            uploadFile(filePath.getLastPathSegment());

        } catch (Exception e) {
            Toast.makeText(PublishActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
