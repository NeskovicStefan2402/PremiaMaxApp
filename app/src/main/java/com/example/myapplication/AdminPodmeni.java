package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminPodmeni extends AppCompatActivity {
    public static String usernameString="";
    public TextView usernameTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_podmeni);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        usernameTxt=findViewById(R.id.textView4);
        usernameTxt.setText(usernameString);
    }

    public void statistika(View view) {
        povezi();
        Intent i=new Intent(this,statistika.class);
        startActivity(i);
    }
    public void izmeni1(View view) {
        Intent i =new Intent(this,AdminActivity.class);
        startActivity(i);
    }

    public void odjava(View view) {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void listing(View view) {
        ucitaj1();
        Intent i=new Intent(this,listingPoruka.class);
        startActivity(i);
    }
    public void ucitaj1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listingPoruka.jsonArray=GETPoruke1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void povezi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    statistika.jsonArray=getMetoda();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public JSONArray getMetoda() throws IOException, JSONException {
        URL urlForGetRequest = new URL("http://192.168.0.15:5000/promeneKursa");
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
            JSONArray jsonArray=new JSONArray(response.toString());
            return jsonArray;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }
    public JSONArray GETPoruke1() throws IOException, JSONException {
        URL urlForGetRequest = new URL("http://192.168.0.15:5000/poruke");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            JSONArray jsonArray = new JSONArray(response.toString());
            return jsonArray;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }

}
