package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    public static String Sto,Sfrom;

    EditText Fpesan;
    Firebase Fref;


    public static String name;
    String mesage;
    String userid;
    String timestamp;

    RecyclerView rv;
    MyRecyclerAdapterChat adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent i = getIntent();
        Sto =  i.getStringExtra("to");
        Sfrom = i.getStringExtra("from");
        name = i.getStringExtra("nama");

        Toast.makeText(this,"To : "+Sto+" From : "+Sfrom+" Nama : "+name,Toast.LENGTH_LONG).show();

        Firebase.setAndroidContext(this);
        Fref = new Firebase("https://farmartcorp.firebaseio.com/chat");

        Fpesan = (EditText) findViewById(R.id.messageEdit);

        rv = (RecyclerView) findViewById(R.id.messagesList);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //Adapter
        if (adapter == null) {
            adapter = new MyRecyclerAdapterChat(this);
            rv.setAdapter(adapter);
        }

    }

    public void kirim(View view) {
        mesage = Fpesan.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int bulan = calendar.get(Calendar.MONTH)+1;
        //timestamp = ""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR);

        long oneDayInMillis = 24 * 60 * 60 * 1000;
        long timeDifference = System.currentTimeMillis() - System.currentTimeMillis();

        if(timeDifference < oneDayInMillis){
            //formattedTime = DateFormat.format("hh:mm a", System.currentTimeMillis()).toString();
            //Toast.makeText(this,""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR)+" "+DateFormat.format("hh:mm a", System.currentTimeMillis()).toString(),Toast.LENGTH_LONG).show();
            timestamp = ""+calendar.get(Calendar.DATE)+"-"+bulan+"-"+calendar.get(Calendar.YEAR)+" "+ DateFormat.format("hh:mm a", System.currentTimeMillis()).toString();

        }else{
            //formattedTime = DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString();
            //Toast.makeText(this,""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR)+" "+DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString(),Toast.LENGTH_LONG).show();
            timestamp = ""+calendar.get(Calendar.DATE)+" - "+bulan+" - "+calendar.get(Calendar.YEAR)+" "+DateFormat.format("dd MMM - hh:mm a", System.currentTimeMillis()).toString();

        }

        ChatModel chatModel = new ChatModel(name,mesage,timestamp,name,Sto);
        Fref.push().setValue(chatModel);
        Fpesan.setText(null);

    }
}
