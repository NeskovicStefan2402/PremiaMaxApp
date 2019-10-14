package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.EmptyStackException;

public class kalkulatorActivity extends AppCompatActivity {
    TextView dinar,evro,dolar,funta,km,iznos,dinarI,evroI,dolarI,funtaI,kmI;
    EditText ulog;
    public int brojac,brojacI;
    public int mnozilac;
    public String valutaUlog="";
    public String valutaIznos="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ulog=findViewById(R.id.editTextUlog);
        evro=findViewById(R.id.textEvro);
        dolar=findViewById(R.id.textDolar);
        km=findViewById(R.id.textKM);
        funta=findViewById(R.id.textFunta);
        dinar=findViewById(R.id.textDinar);
        evroI=findViewById(R.id.textEvroI);
        dolarI=findViewById(R.id.textDolarI);
        kmI=findViewById(R.id.textKMI);
        funtaI=findViewById(R.id.textFuntaI);
        dinarI=findViewById(R.id.textDinarI);
        iznos=findViewById(R.id.TextViewIznos);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void izaberiEvro(View view) {
        if(brojac==0) {
            brojac=1;
            oznaci(evro);
        }else{
            oznaci(evro);
            skiniOznaku(dolar);
            skiniOznaku(km);
            skiniOznaku(funta);
            skiniOznaku(dinar);
        }
        valutaUlog="Evro";
    }
    public void oznaci(TextView txt){
        txt.setBackgroundResource(R.color.siva);
    }
    public void skiniOznaku(TextView txt){
        txt.setBackgroundResource(R.color.svBela);
    }

    public void izaberiDolar(View view) {
        if(brojac==0) {
            brojac=1;
            oznaci(dolar);
        }else{
            oznaci(dolar);
            skiniOznaku(evro);
            skiniOznaku(km);
            skiniOznaku(funta);
            skiniOznaku(dinar);
        }

        valutaUlog="Dolar";
    }
    public void izaberiFuntu(View view) {
        if(brojac==0) {
            brojac=1;
            oznaci(funta);
        }else{
            oznaci(funta);
            skiniOznaku(dolar);
            skiniOznaku(km);
            skiniOznaku(evro);
            skiniOznaku(dinar);
        }
        valutaUlog="Funta";
    }
    public void izaberiKM(View view) {
        if(brojac==0) {
            brojac=1;
            oznaci(km);
        }else{
            oznaci(km);
            skiniOznaku(dolar);
            skiniOznaku(evro);
            skiniOznaku(funta);
            skiniOznaku(dinar);
        }
        valutaUlog="KM";
    }
    public void izaberiDolarI(View view) {
        if(brojac==0) {
            brojacI=1;
            oznaci(dolarI);
        }else{
            oznaci(dolarI);
            skiniOznaku(evroI);
            skiniOznaku(kmI);
            skiniOznaku(funtaI);
            skiniOznaku(dinarI);
        }
        valutaIznos="Dolar";
    }
    public void izaberiFuntuI(View view) {
        if(brojac==0) {
            brojacI=1;
            oznaci(funtaI);
        }else{
            oznaci(funtaI);
            skiniOznaku(dolarI);
            skiniOznaku(kmI);
            skiniOznaku(evroI);
            skiniOznaku(dinarI);
        }
        valutaIznos="Funta";
    }
    public void izaberiKMI(View view) {
        if(brojac==0) {
            brojacI=1;
            oznaci(kmI);
        }else{
            oznaci(kmI);
            skiniOznaku(dolarI);
            skiniOznaku(evroI);
            skiniOznaku(funtaI);
            skiniOznaku(dinarI);
        }
        valutaIznos="KM";
    }
    public void izaberiDinar(View view) {
        if(brojac==0) {
            brojac=1;
            oznaci(dinar);
        }else{
            oznaci(dinar);
            skiniOznaku(dolar);
            skiniOznaku(km);
            skiniOznaku(funta);
            skiniOznaku(evro);
        }
        valutaUlog="Din";
    }

    public void izaberiDinarI(View view) {
        if(brojac==0) {
            brojacI=1;
            oznaci(dinarI);
        }else{
            oznaci(dinarI);
            skiniOznaku(dolarI);
            skiniOznaku(kmI);
            skiniOznaku(funtaI);
            skiniOznaku(evroI);
        }
        valutaIznos="Din";
    }
    public void izaberiEvroI(View view) {
        if(brojac==0) {
            brojacI=1;
            oznaci(evroI);
        }else{
            oznaci(evroI);
            skiniOznaku(dolarI);
            skiniOznaku(kmI);
            skiniOznaku(funtaI);
            skiniOznaku(dinarI);
        }
        valutaIznos="Evro";
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



    public void izracunaj(View view) throws JSONException, IOException {
        JSONObject jsonObject=getMetoda();
        if(!valutaIznos.isEmpty() && !valutaUlog.isEmpty() && !ulog.getText().toString().isEmpty()){
            JSONObject jsonObject1= (JSONObject) jsonObject.get(valutaUlog.toUpperCase());
            double mnoz=jsonObject1.getDouble(valutaIznos.toUpperCase());
            double pr=mnoz*Double.parseDouble(ulog.getText().toString());
            iznos.setText(pr+"");
        }else{
            Toast.makeText(this, "Popunite korektno sve parametre!", Toast.LENGTH_SHORT).show();
        }
    }


    public void KursnaLista(View view) {
        Intent i=new Intent(this,KursnaLista.class);
        startActivity(i);
        finish();
    }

    public void Kontakt(View view) {
        Intent i=new Intent(this,Kontakt.class);
        startActivity(i);
        finish();
    }

    public void Onama(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
