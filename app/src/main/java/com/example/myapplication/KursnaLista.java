package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KursnaLista extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,txt21,txt22,txt23,txt24,txt25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txt1=findViewById(R.id.etextView17);
        txt2=findViewById(R.id.textView18);
        txt3=findViewById(R.id.textView19);
        txt4=findViewById(R.id.textView20);
        txt5=findViewById(R.id.textView21);
        txt6=findViewById(R.id.textView23);
        txt7=findViewById(R.id.textView24);
        txt8=findViewById(R.id.textView25);
        txt9=findViewById(R.id.textView26);
        txt10=findViewById(R.id.textView27);
        txt12=findViewById(R.id.textView29);
        txt13=findViewById(R.id.textView30);
        txt14=findViewById(R.id.textView31);
        txt15=findViewById(R.id.textView32);
        txt16=findViewById(R.id.textView33);
        txt17=findViewById(R.id.textView35);
        txt18=findViewById(R.id.textView36);
        txt19=findViewById(R.id.textView37);
        txt20=findViewById(R.id.textView38);
        txt21=findViewById(R.id.textView39);
        txt22=findViewById(R.id.textView41);
        txt23=findViewById(R.id.textView42);
        txt24=findViewById(R.id.textView43);
        txt25=findViewById(R.id.textView44);
        txt11=findViewById(R.id.textView45);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        podesiValute();
    }
    public void kalkulatorKlik1(View view) {
        Intent i=new Intent(this,kalkulatorActivity.class);
        startActivity(i);
        finish();
    }

    public void kontaktKlik1(View view) {
        Intent i=new Intent(this,Kontakt.class);
        startActivity(i);
        finish();
    }

    public void OnamaKlik1(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
    public JSONObject getMetoda() throws IOException, JSONException {
        URL urlForGetRequest = new URL("http://192.168.0.15:5000/kurs");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            JSONObject jsonObject=new JSONObject(response.toString());
            return jsonObject;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }
    public void podesiValute() {
        JSONObject jsonObject= null;
        try {
            jsonObject=getMetoda();
            podFunk("DIN","DIN",txt1,jsonObject);
            podFunk("DIN","DOLAR",txt2,jsonObject);
            podFunk("DIN","EVRO",txt3,jsonObject);
            podFunk("DIN","FUNTA",txt4,jsonObject);
            podFunk("DIN","KM",txt5,jsonObject);
            podFunk("DOLAR","DIN",txt6,jsonObject);
            podFunk("DOLAR","DOLAR",txt7,jsonObject);
            podFunk("DOLAR","EVRO",txt8,jsonObject);
            podFunk("DOLAR","FUNTA",txt9,jsonObject);
            podFunk("DOLAR","KM",txt10,jsonObject);
            podFunk("EVRO","DIN",txt12,jsonObject);
            podFunk("EVRO","DOLAR",txt13,jsonObject);
            podFunk("EVRO","EVRO",txt14,jsonObject);
            podFunk("EVRO","FUNTA",txt15,jsonObject);
            podFunk("EVRO","KM",txt16,jsonObject);
            podFunk("FUNTA","DIN",txt17,jsonObject);
            podFunk("FUNTA","DOLAR",txt18,jsonObject);
            podFunk("FUNTA","EVRO",txt19,jsonObject);
            podFunk("FUNTA","FUNTA",txt20,jsonObject);
            podFunk("FUNTA","KM",txt21,jsonObject);
            podFunk("KM","DIN",txt22,jsonObject);
            podFunk("KM","DOLAR",txt23,jsonObject);
            podFunk("KM","EVRO",txt24,jsonObject);
            podFunk("KM","FUNTA",txt25,jsonObject);
            podFunk("KM","KM",txt11,jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void podFunk(String prvi,String drugi,TextView txt,JSONObject jsonObject) throws JSONException {
        txt.setText(jsonObject.getJSONObject(prvi.toString()).getString(drugi.toString()));
    }

}
