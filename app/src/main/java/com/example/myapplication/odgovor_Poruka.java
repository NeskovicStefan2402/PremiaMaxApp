package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class odgovor_Poruka extends AppCompatActivity {
    public TextView txtTo,txtFrom,txtPoruka;
    public static String strTo="",strPor="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odgovor__poruka);
        txtFrom=findViewById(R.id.textViewFrom);
        txtTo=findViewById(R.id.textViewTo);
        txtPoruka=findViewById(R.id.textViewPoruka);
        txtTo.setText(strTo);
        txtPoruka.setText(strPor);
        txtFrom.setText(MainActivity.username);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void odgovori(View view) {
        Intent i=new Intent(this,listingPoruka.class);
        startActivity(i);
        finish();
    }

    public void ponistiOdg(View view) {
        Intent i=new Intent(this,listingPoruka.class);
        startActivity(i);
        finish();
    }



}
