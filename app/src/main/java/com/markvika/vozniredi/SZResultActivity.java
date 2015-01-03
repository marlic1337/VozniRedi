package com.markvika.vozniredi;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class SZResultActivity extends Activity {
	private JSONObject jsonRez;
	private TextView rezultatTV;
	private String test = "";
	private ArrayList<Trip> seznamRez = new ArrayList<Trip>();
	private ArrayList<String> as;
	private ArrayList<String> ad;
	private ArrayList<String> ds;
	private ArrayList<String> dd;
	private ArrayList<String> tn;
	private String pl;
//	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_szresult);
		try {
			jsonRez = new JSONObject(getIntent().getStringExtra("json"));
			rezultatTV = (TextView) findViewById(R.id.jsonRez);
			JSONArray jar = new JSONArray(jsonRez.getString("routes"));
			Trip tempTrip = new Trip();
			for (int i = 0; i < jar.length(); i++) {
				JSONArray jar1 = new JSONArray (jar.getJSONObject(i).getString("route"));
				as = new ArrayList<String>();
				ad = new ArrayList<String>();
				ds = new ArrayList<String>();
				dd = new ArrayList<String>();
				tn = new ArrayList<String>();
				pl = "";
				for (int j = 0; j < jar1.length(); j++) {
					as.add(j, jar1.getJSONObject(j).getJSONObject("arrival station").getString("name"));
					ad.add(j, jar1.getJSONObject(j).getJSONObject("arrival station").getString("trainHour"));
					ds.add(j, jar1.getJSONObject(j).getJSONObject("departure station").getString("name"));
					dd.add(j, jar1.getJSONObject(j).getJSONObject("departure station").getString("trainHour"));
					tn.add(j, jar1.getJSONObject(j).getJSONObject("departure station").getString("trainNumber"));

				}
				pl = jar1.getJSONObject(0).getString("price_link");
				tempTrip.setAd(ad);
				tempTrip.setAs(as);
				tempTrip.setDd(dd);
				tempTrip.setDs(ds);
				tempTrip.setTn(tn);
				tempTrip.setPriceLink(pl);
				seznamRez.add(i, tempTrip);
				test += tempTrip.toString();
			}

			rezultatTV.setText(test);
			rezultatTV.setMovementMethod(new ScrollingMovementMethod());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
