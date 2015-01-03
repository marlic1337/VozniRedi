package com.markvika.vozniredi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void SZMain(View view) {
		Intent viewContact = new Intent(MainActivity.this, SZMainActivity.class);
		startActivity(viewContact);
	}

	public void LPPMain (View view) {
		Intent viewContact = new Intent(MainActivity.this, LPPMainActivity.class);
		startActivity(viewContact);
	}

	public void About(View view) {
		Intent avtorja = new Intent(MainActivity.this, Avtorja.class);
		startActivity(avtorja);
	}
}
