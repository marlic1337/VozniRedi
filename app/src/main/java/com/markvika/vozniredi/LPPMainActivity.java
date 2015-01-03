package com.markvika.vozniredi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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



/**
 * Created by Vika Lampret on 25.12.2014.
 */
public class LPPMainActivity extends Activity {
    private LPPDatabase mDatabaseHelper;
    private Spinner odhodnaPostaja;
    Button btn;
    private EditText stPostaje;
    private TextView prihodi;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lppmain);
        odhodnaPostaja=(Spinner) findViewById(R.id.postaje);
        stPostaje=(EditText) findViewById(R.id.stPostaje);
        mDatabaseHelper = new LPPDatabase(this);
        // call super's onCreate
        /*Cursor c = mDatabaseHelper.query(LPPDatabase.TABLE_POSTAJE, LPPDatabase.COL_NAME);

        String[] from = new String[]{LPPDatabase.COL_NAME, LPPDatabase.COL_NAME};
        int[] to = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, from, to, 0);
        super.onCreate(savedInstanceState); // call super's onCreate*/


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.postajeLPP, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odhodnaPostaja.setAdapter(adapter);

        //Cursor c = mDatabaseHelper.query(LPPDatabase.TABLE_POSTAJE, LPPDatabase.COL_NAME);
        //SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, from, to, 0);
        btn=(Button) findViewById(R.id.Isci);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //posredovanje podatkov v LPPRezultatiMainActivity
                Intent intent = new Intent(LPPMainActivity.this, LPPRezultatiMainActivity.class);
                Spinner spinner = (Spinner)findViewById(R.id.postaje);
                String text = spinner.getSelectedItem().toString();
                intent.putExtra("ET1", text.toString());
                intent.putExtra("ET2", stPostaje.getText().toString());
                startActivity(intent);
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*public void lookUpArrivals(View v) {
        final String currentStation = odhodnaPostaja.getSelectedItem().toString();
        final String currentLine = stPostaje.getText().toString().trim();
        LPPRezultatiActivity l=new LPPRezultatiActivity(currentStation,currentLine);
        /*final ArrivalsLookUp arrivals = new ArrivalsLookUp(getApplicationContext());
        arrivals.execute(currentStation, currentLine);
    }*/

    private void addStation(String name) {

        int st=5;
        ContentValues values = new ContentValues();
        String[] ime=new String[1];
        ime[0]=name;
        String selectQuery = "SELECT COUNT(*) FROM postaje WHERE name=?";
        Cursor cursor = mDatabaseHelper.getReadableDatabase().rawQuery(selectQuery,ime);
        try {

            if (cursor.moveToFirst()) {
                st=cursor.getInt(0);
            }

        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (mDatabaseHelper != null) {
                mDatabaseHelper.close();
            }
        }

        if(st==0) {
            if (name != null) {

                values.put(LPPDatabase.COL_NAME, name);

            }
            try {

                mDatabaseHelper.insert(LPPDatabase.TABLE_POSTAJE, values);

            } catch (LPPDatabase.NotValidException e) {

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }}
        else {
            Toast.makeText(this, "Zadeva je ze v bazi", Toast.LENGTH_SHORT).show();
        }
    }
    public void LPPRezultatiMain(View view) {
        // create an Intent to launch the ViewContact Activity
        Intent viewContact = new Intent(LPPMainActivity.this, LPPRezultatiMainActivity.class);

        // pass the selected contact's row ID as an extra with the Intent
        startActivity(viewContact); // start the ViewContact Activity
        // end method onItemClick // end viewContactListener
    }

    public void dodajPriljubljene(MenuItem item) {
        String t = odhodnaPostaja.getSelectedItem().toString();
        addStation(t);
    }
    public void LPPPriljubljeni(View view) {
        // create an Intent to launch the ViewContact Activity
        Intent viewContact = new Intent(LPPMainActivity.this, LPPPriljubljeniActivity.class);

        // pass the selected contact's row ID as an extra with the Intent
        startActivity(viewContact); // start the ViewContact Activity
        // end method onItemClick // end viewContactListener
    }



}
