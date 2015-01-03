package com.markvika.vozniredi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Vika Lampret on 1.1.2015.
 */
public class LPPPriljubljeniActivity extends LPPMainActivity {
    private LPPDatabase mDatabaseHelper;
    private ArrayAdapter<CharSequence> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpppriljubljeni);
        mDatabaseHelper = new LPPDatabase(this);
        /*mDatabaseHelper.delete(LPPDatabase.TABLE_POSTAJE,0);
        mDatabaseHelper.delete(LPPDatabase.TABLE_POSTAJE,1);
        mDatabaseHelper.delete(LPPDatabase.TABLE_POSTAJE,2);
        mDatabaseHelper.delete(LPPDatabase.TABLE_POSTAJE,3);*/
        Spinner spin = (Spinner) findViewById(R.id.pr);
        adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
        String result;
        Cursor cursor = mDatabaseHelper.query(LPPDatabase.TABLE_POSTAJE, LPPDatabase.COL_NAME);
        while (cursor.moveToNext()){
            result=cursor.getString(1);
            adapter.add(result);
        }
        mDatabaseHelper.close();
        btn=(Button) findViewById(R.id.isci);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //posredovanje podatkov v LPPRezultatiMainActivity
                Intent intent = new Intent(LPPPriljubljeniActivity.this, LPPRezultatiMainActivity.class);
                Spinner spinner = (Spinner)findViewById(R.id.pr);
                String text = spinner.getSelectedItem().toString();
                intent.putExtra("ET1", text.toString());
                intent.putExtra("ET2", "");
                startActivity(intent);
            }
        });
    }
    public void pocistiSeznam(View view) {
        int result;
        mDatabaseHelper = new LPPDatabase(this);
        Cursor cursor = mDatabaseHelper.query(LPPDatabase.TABLE_POSTAJE, LPPDatabase.COL_ID);
        while (cursor.moveToNext()){
            result=cursor.getInt(0);
            mDatabaseHelper.delete(LPPDatabase.TABLE_POSTAJE,result);

        }
        Spinner spin = (Spinner) findViewById(R.id.pr);
        spin.setAdapter(null);
        mDatabaseHelper.close();

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

    public void LPPRezultatiMain(View view) {
        // create an Intent to launch the ViewContact Activity
        Intent viewContact = new Intent(LPPPriljubljeniActivity.this, LPPRezultatiMainActivity.class);

        // pass the selected contact's row ID as an extra with the Intent
        startActivity(viewContact); // start the ViewContact Activity
        // end method onItemClick // end viewContactListener
    }
}
