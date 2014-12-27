package com.markvika.vozniredi;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class SZResultActivity extends Activity {
	private JSONObject obj;
	private TextView rezultatTV;
//	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_szresult);

		try {
			obj = new JSONObject(getIntent().getStringExtra("json"));
			rezultatTV = (TextView) findViewById(R.id.jsonRez);
			rezultatTV.setText(obj.toString());
			rezultatTV.setMovementMethod(new ScrollingMovementMethod());

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_szresult, menu);
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
}
