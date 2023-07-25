package com.pkmunla.asus.homedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView Kajian,Ustazd,Masjid,Lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi cardview
        Kajian = (CardView) findViewById(R.id.cv_kajian);
        Ustazd = (CardView) findViewById(R.id.cv_ustadz);
        Masjid = (CardView) findViewById(R.id.cv_masjid);
        Lokasi = (CardView) findViewById(R.id.cv_lokasi);

        //add click listener to cardview
        Kajian.setOnClickListener(this);
        Ustazd.setOnClickListener(this);
        Masjid.setOnClickListener(this);
        Lokasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cv_kajian : i = new Intent(this,HomeActivity.class);startActivity(i);break;

            case R.id.cv_ustadz : i = new Intent(this, UstadzActivity.class);startActivity(i); break;

            case R.id.cv_masjid : i = new Intent(this, MasjidActivity.class);startActivity(i); break;

            case R.id.cv_lokasi : i = new Intent(this, LokasiActivity.class);startActivity(i); break;

            default: break;
        }
    }
}
