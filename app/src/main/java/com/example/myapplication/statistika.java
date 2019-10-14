package com.example.myapplication;

import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class statistika extends AppCompatActivity {
    public static JSONArray jsonArray=new JSONArray();
    public static String prvi="DIN",drugi="DIN";
    public GraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistika);
        graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.VISIBLE);
        graph.setTitle("Vremenske promene kursa:");

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*DataPoint[] lista=new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(1,2),
                new DataPoint(2,3),
                new DataPoint(3,4)};*/


        final Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"DIN", "DOLAR", "EVRO","FUNTA","KM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Spinner dropdown1 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown1.setAdapter(adapter1);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                    prvi=parent.getItemAtPosition(position).toString();

            }@Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                drugi=parent.getItemAtPosition(position).toString();

            }@Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    public DataPoint[] ispisiDINDIN(String prvi,String drugi) {
        DataPoint[] pointi=new DataPoint[jsonArray.length()];
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double x = jsonObject.getJSONObject(prvi).getDouble(drugi);
                DataPoint dataPoint = new DataPoint(x, i);
                pointi[i] = dataPoint;
            }
        }catch (Exception e){

        }

        return pointi;
    }


    public void crtaj(View view) {
        DataPoint[] dataPoints=ispisiDINDIN(prvi,drugi);
        LineGraphSeries <DataPoint> serija=new LineGraphSeries<>(dataPoints);
        graph.addSeries(serija);
    }
}
