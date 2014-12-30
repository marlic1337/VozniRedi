package com.markvika.vozniredi;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vika Lampret on 25.12.2014.
 */
public class LPPMainActivity extends Activity {
    public static final String ADDRESS_BOTH = "http://www.trola.si/%s/%s";
    public static final String ADDRESS_SINGLE = "http://www.trola.si/%s";

    private Spinner odhodnaPostaja;
    private EditText stPostaje;
    private Button isci;
    private TextView prihodi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lppmain);
        odhodnaPostaja=(Spinner) findViewById(R.id.postaje);
        stPostaje=(EditText) findViewById(R.id.stPostaje);
        prihodi = (TextView) findViewById(R.id.prihodi);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.postajeLPP, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odhodnaPostaja.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lppmain, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void lookUpArrivals(View v) {
        final String currentStation = odhodnaPostaja.getSelectedItem().toString();
        final String currentLine = stPostaje.getText().toString().trim();

        final ArrivalsLookUp arrivals = new ArrivalsLookUp(getApplicationContext());
        arrivals.execute(currentStation, currentLine);
    }
    private class ArrivalsLookUp extends AsyncTask<String, Void, JSONObject> {

        private final Context context;

        public ArrivalsLookUp(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // naredi poizvedbo
            try {
                final String address;

                if (params[1].equals("")) {
                    address = String.format(ADDRESS_SINGLE, params[0]);
                } else {
                    address = String.format(ADDRESS_SINGLE, params[1]);
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

        @Override
        protected void onPostExecute(JSONObject result) {
            if (result == null) {
                Toast.makeText(context, "Prislo je do napake.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // prejeti objekt prikazemo na ekratnu
                prihodi.setText(result.toString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void lppIsci(View view) {

        final ArrivalsLookUp arrivals = new ArrivalsLookUp(getApplicationContext());
        arrivals.execute();
    }

    public void dodajPriljubljene(MenuItem item) {
    }

}
