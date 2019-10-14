package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,txt21,txt22,txt23,txt24,txt25;
    public static JSONObject jsonObject;
    public TextView ussernameTxt;
    public static String ussernameString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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
        ussernameTxt=findViewById(R.id.textView3);
        ussernameTxt.setText(ussernameString+"");
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        podesiValuteIzmena();
    }
    public void podesiValuteIzmena() {
        try {
            jsonObject=getMetodaIzmena();
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
    public JSONObject getMetodaIzmena() throws IOException, JSONException {
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
    public void podFunk(String prvi,String drugi,EditText txt,JSONObject jsonObject) throws JSONException {
        txt.setText(jsonObject.getJSONObject(prvi.toString()).getString(drugi.toString()));
    }
    public void podFunkIzmeni(String prvi,EditText txt1,EditText txt2,EditText txt3,EditText txt4,EditText txt5,JSONObject jsonObject) throws JSONException {
        jsonObject.getJSONObject(prvi).remove("DIN");
        jsonObject.getJSONObject(prvi).remove("DOLAR");
        jsonObject.getJSONObject(prvi).remove("EVRO");
        jsonObject.getJSONObject(prvi).remove("FUNTA");
        jsonObject.getJSONObject(prvi).remove("KM");
        jsonObject.getJSONObject(prvi).put("DIN",Double.parseDouble(txt1.getText().toString()));
        jsonObject.getJSONObject(prvi).put("DOLAR",Double.parseDouble(txt2.getText().toString()));
        jsonObject.getJSONObject(prvi).put("EVRO",Double.parseDouble(txt3.getText().toString()));
        jsonObject.getJSONObject(prvi).put("FUNTA",Double.parseDouble(txt4.getText().toString()));
        jsonObject.getJSONObject(prvi).put("KM",Double.parseDouble(txt5.getText().toString()));
    }
    public void izmeni(View view) throws IOException, JSONException {
        podFunkIzmeni("DIN",txt1,txt2,txt3,txt4,txt5,jsonObject);
        podFunkIzmeni("DOLAR",txt6,txt7,txt8,txt9,txt10,jsonObject);
        podFunkIzmeni("EVRO",txt12,txt13,txt14,txt15,txt16,jsonObject);
        podFunkIzmeni("FUNTA",txt17,txt18,txt19,txt20,txt21,jsonObject);
        podFunkIzmeni("KM",txt22,txt23,txt24,txt25,txt11,jsonObject);
        POSTRequest(jsonObject);
        Intent i=new Intent(this,AdminPodmeni.class);
        startActivity(i);
        finish();
    }


    public void POSTRequest(JSONObject jsonObjectIzmenjen) throws IOException {
        JSONObject jsonObject1=jsonObjectIzmenjen;
        URL obj = new URL("http://192.168.0.15:5000/post");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("userId", "a1bcdefgh");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(jsonObject1.toString().getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            System.out.println(response.toString());

    }

    public void ponisti(View view) {
        Intent i=new Intent(this,AdminPodmeni.class);
        startActivity(i);
        finish();
    }
}
