package com.example.fujimiya.farmartrevisi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String kunci,nama,password;
    public static Double lat,lon;
    DialogInterface.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        kunci = i.getStringExtra("key");
        nama = i.getStringExtra("nama");
        password = i.getStringExtra("password");
        lat = i.getDoubleExtra("lat",0.000000);
        lon = i.getDoubleExtra("lon",0.000000);

        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapsFragment mapsFragment = new MapsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmen_maps,mapsFragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakan anda tetap ingin menutup aplikasi?");
        builder.setCancelable(false);

        listener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    finishAffinity();
                    System.exit(0);
                }

                if(which == DialogInterface.BUTTON_NEGATIVE){
                    dialog.cancel();
                }
            }
        };
        builder.setPositiveButton("Ya",listener);
        builder.setNegativeButton("Tidak", listener);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            MapsFragment mapsFragment = new MapsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmen_maps,mapsFragment);
            fragmentTransaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_publis_komoditi) {
            Intent i = new Intent(UserActivity.this, PublishActivity.class);
            i.putExtra("kunci",kunci);
            i.putExtra("nama",nama);
            startActivity(i);

        } else if (id == R.id.nav_penawaran) {

            ChatListFragment chatListFragment = new ChatListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("nama",nama);
            chatListFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmen_maps,chatListFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_penjualan) {
            try {
                ListPenjualanFragment listPenjualanFragment = new ListPenjualanFragment();
                Bundle bundle = new Bundle();
                bundle.putString("kunci",kunci);
                listPenjualanFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmen_maps,listPenjualanFragment );
                fragmentTransaction.commit();

            }catch (Exception e){

            }


        }else if (id == R.id.nav_keluar) {
            finish();
            Intent i = new Intent(UserActivity.this, SignInActivity.class);
            startActivity(i);
            System.exit(0);
        } else if (id == R.id.nav_setting){
            Intent i = new Intent(UserActivity.this, SettingAkunUserMainActivity.class);
            i.putExtra("kunci",kunci);
            i.putExtra("nama",nama);
            i.putExtra("password",password);
            startActivity(i);
        }else if (id == R.id.nav_pesanan){
            try {
                ListPesananFragment listPesananFragment = new ListPesananFragment();
                //Bundle bundle = new Bundle();
                //bundle.putString("kunci",kunci);
                //listPenjualanFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmen_maps,listPesananFragment);
                fragmentTransaction.commit();

            }catch (Exception e){

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
