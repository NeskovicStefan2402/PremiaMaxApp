package com.example.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class listingPoruka extends AppCompatActivity {
    ListView lView;
    Adapter lAdapter;
    public static JSONArray jsonArray=new JSONArray();
    public LinkedList<String> mails = new LinkedList<>();
    public LinkedList<String> dates = new LinkedList<>();
    public LinkedList<String> poruke=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_poruka);

        mailovi(jsonArray);
        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new Adapter(listingPoruka.this, mails, dates);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                odgovor_Poruka.strTo=mails.get(i);
                odgovor_Poruka.strPor=poruke.get(i);
                klikFrame();
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    public void klikFrame(){
        Intent i=new Intent(this,odgovor_Poruka.class);
        startActivity(i);
        finish();
    }

    public void mailovi(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dates.add(jsonObject.getString("datum"));
                mails.add(jsonObject.getString("email"));
                poruke.add(jsonObject.getString("poruka"));
            }
        } catch (Exception e) {
        }
    }







}
