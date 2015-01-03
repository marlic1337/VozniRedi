package com.markvika.vozniredi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        // create a new Intent to launch the AddEditContact Activity
        Intent avtorja = new Intent(MainActivity.this, Avtorja.class);
        startActivity(avtorja); // start the AddEditContact Activity
        return super.onOptionsItemSelected(item); // call super's method
	}

	public void SZMain(View view) {
		// create an Intent to launch the ViewContact Activity
		Intent viewContact = new Intent(MainActivity.this, SZMainActivity.class);

		// pass the selected contact's row ID as an extra with the Intent
		startActivity(viewContact); // start the ViewContact Activity
	 // end method onItemClick // end viewContactListener
	}
    public void LPPMain (View view) {
        // create an Intent to launch the ViewContact Activity
        Intent viewContact = new Intent(MainActivity.this, LPPMainActivity.class);

        // pass the selected contact's row ID as an extra with the Intent
        startActivity(viewContact); // start the ViewContact Activity
        // end method onItemClick // end viewContactListener
    }
}
