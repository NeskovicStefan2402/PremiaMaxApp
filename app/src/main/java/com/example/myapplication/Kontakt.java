package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class Kontakt extends FragmentActivity implements OnMapReadyCallback  {
    GoogleMap mMap;
    public EditText email,poruka;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursna_lista);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng position = new LatLng(44.8198125,20.4656875);
                mMap.addMarker(new MarkerOptions().position(position).title("Premia Max"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));


    }

    public void kontaktNas(View view) {
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            final LayoutInflater inflater=getLayoutInflater();
            final View promptsView = inflater.inflate(R.layout.kontakt_message_alert, null);

            builder.setView(promptsView).setPositiveButton(
                    "Posalji", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            email=promptsView.findViewById(R.id.email1);
                            poruka=promptsView.findViewById(R.id.message1);
                            if(email.getText().toString().contains("@") && !poruka.getText().toString().equals("")){
                                try {
                                    POSTKontakt(email.getText().toString(),poruka.getText().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                Toast.makeText(Kontakt.this, "Pograsno ste uneli podatke!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setTitle("Kontaktirajte nas:");
            builder.create().show();
        }
    public void POSTKontakt(String mail,String message) throws IOException, JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("email",mail);
        jsonObject.put("message",message);
        URL obj = new URL("http://192.168.0.15:5000/post/message");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("userId", "a1bcdefgh");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(jsonObject.toString().getBytes());
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

    public void OnamaKlikKontakt(View view) {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }

    public void kursnaktKlikKontakt(View view) {
        Intent i =new Intent(this,KursnaLista.class);
        startActivity(i);
        finish();

    }

    public void kalkulatorKlikKontakt(View view) {
        Intent i =new Intent(this,kalkulatorActivity.class);
        startActivity(i);
        finish();
    }
}
