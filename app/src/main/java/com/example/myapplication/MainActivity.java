package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public static String username="";
    public String password="";
    public EditText un,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void kursnaListaKlik(View view) {
        Intent i=new Intent(this,KursnaLista.class);
        startActivity(i);
        finish();
    }
    public void kalkulatorKlik(View view) {
        Intent i=new Intent(this,kalkulatorActivity.class);
        startActivity(i);
        finish();
    }
    public void kontaktKlik(View view) {
        Intent i=new Intent(this,Kontakt.class);
        startActivity(i);
        finish();
    }
    public void odobreno(){
        Intent i =new Intent(this,AdminPodmeni.class);
        startActivity(i);
        finish();
    }
    public void Login(View view) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final LayoutInflater inflater=getLayoutInflater();
        final View promptsView = inflater.inflate(R.layout.dialog_alert, null);
        builder.setView(promptsView).setPositiveButton(
                "Prijavi se", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        un=promptsView.findViewById(R.id.username1);
                        ps=promptsView.findViewById(R.id.password1);
                        if(un.getText().toString().equals("Dejopop69@gmail.com") && ps.getText().toString().equals("sifra")){
                            odobreno();
                            username="Dejopop69@gmail.com";
                            AdminActivity.ussernameString=un.getText().toString();
                            AdminPodmeni.usernameString=un.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Pograsno ste uneli podatke!", Toast.LENGTH_SHORT).show();
                       }
                    }
                }).setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setTitle("Molimo Vas da se prijavite:");
    builder.create().show();
    }


}
