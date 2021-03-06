package com.markvika.vozniredi;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View.OnClickListener;

import java.util.ArrayList;

/**
 * Created by Vika Lampret on 1.1.2015.
 */
public class LPPRezultatiMainActivity extends LPPMainActivity{
    public static final String ADDRESS_SINGLE = "http://www.trola.si/%s";
    TextView prihodi;
    String imePostaje;
    String stPostaje;
    TextView text;
    ArrayList<String> ime;
    private CursorAdapter contactAdapter;
    private ProgressDialog pDialog;
    ArrayList<String> arr=new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        ime=new ArrayList<String>();

        Intent intent = getIntent();
        imePostaje = intent.getStringExtra("ET1");
        stPostaje = intent.getStringExtra("ET2");
        lookUpArrivals();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpprezultatimain);


        //adapter.setListAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lpprezultatimain, menu);
        return true;
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void lookUpArrivals() {


        final ArrivalsLookUp arrivals = new ArrivalsLookUp(getApplicationContext());
        arrivals.execute(imePostaje, stPostaje);
    }

    private class ArrivalsLookUp extends AsyncTask<String, Void, JSONObject> {

        private final Context context;

        public ArrivalsLookUp(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(LPPRezultatiMainActivity.this);
            pDialog.setMessage("Iskanje voznega reda glede na izbrano postajo");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected JSONObject doInBackground(String... params) {
            // naredi poizvedbo
            try {
                final String address;
                if (params[1].equals("")) {

                    String para=params[0].replace(" ","%20");
                    address = String.format(ADDRESS_SINGLE, para);
                } else {
                    String para=params[1].replaceAll(" ","%20");
                    address = String.format(ADDRESS_SINGLE, para);
                }

                final HttpGet request = new HttpGet(address);
                // nastavimo zaglavje, da dobimo odgovor
                // v formatu JSON (sicer bo v HTML)
                request.addHeader("Accept", "application/json");

                final HttpClient hcl = new DefaultHttpClient();

                // spodnji klic dejansko naredi poizvedbo
                final HttpResponse response = hcl.execute(request);
                final HttpEntity entity = response.getEntity();

                // odgovor pretvorimo v niz, nato pa niz
                // pretvorimo v JSON objekt
                return new JSONObject(EntityUtils.toString(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(JSONObject result) {
            if (result == null) {
                Toast.makeText(context, "Prislo je do napake.", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
                return;

            }
            try {
                    // prejeti objekt prikazemo na ekratnu
                    prihodi = (TextView) findViewById(R.id.prihodi);
                    String post_id = "";
                    JSONArray arr = result.getJSONArray("stations");
                    int[] najkrajse = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
                    ArrayList<String> izpis=new ArrayList<String>();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONArray arr2 = arr.getJSONObject(i).getJSONArray("buses");
                        for (int j = 0; j < arr2.length(); j++) {
                            JSONArray arr3 = arr2.getJSONObject(j).getJSONArray("arrivals");
                            izpis.add(arr2.getJSONObject(j).getString("direction"));
                            int z=0;
                            for (int k = 0; k < arr3.length(); k++) {

                                if(z<3) {
                                    izpis.add((arr3.getInt(k)+""));
                                    z++;
                                }
                            }
                        }
                    }

                    prihodi.append("  Minute do prihodov avtobusov\n");
                    prihodi.append("\n");
                    int k=1;
                    for (int i = 0; i < izpis.size(); i++) {
                        try {
                            int m=Integer.parseInt(izpis.get(i));
                            prihodi.append(m+"min");
                            if(i!=izpis.size()-1) {
                                try {
                                    int n=Integer.parseInt(izpis.get(i+1));
                                    prihodi.append(", ");
                                } catch(NumberFormatException mm) {
                                }
                            }
                        } catch(NumberFormatException e) {
                            if(i!=0) {
                            prihodi.append("\n");}
                            prihodi.append(izpis.get(i)+" - ");
                            if(i!=izpis.size()-1) {
                                try {
                                    int n=Integer.parseInt(izpis.get(i+1));
                                } catch(NumberFormatException m) {
                                    prihodi.append("Ni prihodov");
                                }
                            }
                        }
                        k++;
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                prihodi.setText("Vnesena je bila napacna stevilka. Prosim vrnite se na prejsnjo stran in jo popravite.");

                }
                pDialog.dismiss();
            }

        }


}
