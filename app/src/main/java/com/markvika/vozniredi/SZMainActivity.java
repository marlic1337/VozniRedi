package com.markvika.vozniredi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class SZMainActivity extends Activity {

//	@Override

	private Spinner odhodnaPostaja;
	private Spinner prihodnaPostaja;
	private DatePicker szDate;
	private int[] idPostaje = {44652,44704,43856,44706,44651,42510,43403,42006,44716,44715,44712,44004,42901,44504,42101,42005,44001,42002,43100,43911,44656,43452,43351,42505,44356,44200,99996,42001,43805,42503,44655,43506,43202,42352,44506,44653,43500,42363,44800,43405,43912,43653,42704,42311,42908,44102,43775,42504,44709,43702,43200,42800,43354,43303,43777,43503,42361,42201,44357,44202,44710,42603,44903,43854,42707,43603,42353,42902,42400,42209,42354,42356,42355,44654,44705,43353,44902,44719,44352,44502,43455,44101,42307,44501,42208,42004,43002,42210,42313,42003,43402,43703,42207,99993,99994,99995,42316,42300,42357,42351,42365,99998,42212,42804,42317,44011,42302,42805,42222,43700,43605,44006,42102,43774,43400,43420,43401,43413,43304,43414,42303,42304,43604,43801,42502,42907,42601,42711,42905,44707,43356,43704,44901,44713,44002,44700,42600,42512,42511,43651,44601,43302,43600,43357,43205,42310,42506,43407,43906,43602,43451,43901,42904,44100,44007,44703,44711,43853,43855,44301,44717,43505,44708,42309,43802,43408,43203,43904,42708,43201,42602,44009,44251,43300,44003,44010,44300,43502,43851,44600,43355,43771,43601,43301,42103,42705,42312,44008,44303,43501,42305,43001,43808,42360,44201,43803,43804,42501,42508,43404,43415,43406,42206,42507,42100,44500,43204,42315,43852,44701,43652,44505,43800,43352,43417,43806,43418,43776,44603,43903,43453,43102,42703,42906,43251,42706,43360,42306,42802,42362,43252,42801,43905,43907,44503,42701,43101,43807,43412,43416,42203,42700,42909,42359,42366,42358,42903,42509,43910,43909,42702,43358,44005,43701,43809,44718,42709,44602,43410,43409,43411,42204,43359,44302,42200,43902,42710,42314};
	private String[] imePostaje = {"Ajdovščina","Anhovo","Atomske Toplice hotel","Avče","Batuje","Birčna vas","Bistrica ob Dravi","Blanca","Bled Jezero","Bohinjska Bela","Bohinjska Bistrica","Borovnica","Boštanj","Branik","Breg","Brestanica","Brezovica","Brežice","Celje","Celje Lava","Cesta","Cirknica","Cirkovce","črnomelj","črnotiče","Divača","Divača(Škocjanske jame)","Dobova","Dobovec","Dobravice","Dobravlje","Dobrije","Dolga Gora","Domžale","Dornberk","Dornberk vas","Dravograd","Duplica-Bakovnik","Dutovlje","Fala","Florjan","Frankovci","Gaber","Globoko","Gomila","Gornje Ležeče","Gornji Petrovci","Gradac","Grahovo","Grlava","Grobelno","Grosuplje","Hajdina","Hoče","Hodoš","Holmec","Homec pri Kamniku","Hrastnik","Hrastovlje","Hrpelje-Kozina","Hudajužna","Hudo","Ilirska Bistrica","Imeno","Ivančna Gorica","Ivanjkovci","Jarše-Mengeš","Jelovec","Jesenice","Jevnica","Kamnik","Kamnik Graben","Kamnik mesto","Kamnje","Kanal","Kidričevo","Kilovče","Kočna","Koper","Kopriva","Košaki","Košana","Kranj","Kreplje","Kresnice","Krško","Laško","Laze","Lesce-Bled","Libna","Limbuš","Lipovci","Litija","Litija(Dom-Tisje)","Litija(Šmartno-Cerkovnik)","Litija(Zavrstnik pri Tatjani)","Litostroj","Ljubljana","Ljubljana Brinje","Ljubljana črnuče","Ljubljana Ježica","Ljubljana Moste (BTC)","Ljubljana Polje","Ljubljana Rakovnik","Ljubljana Stegne","Ljubljana Tivoli","Ljubljana Vižmarje","Ljubljana Vodmat","Ljubljana Zalog","Ljutomer","Ljutomer mesto","Logatec","Loka","Mačkovci","Maribor","Maribor Sokolska","Maribor Studenci","Maribor Tabor","Maribor Tezno","Marles","Medno","Medvode","Mekotnjak","Mestinje","Metlika","Mirna","Mirna Peč","Mlačevo","Mokronog","Most na Soči","Moškanjci","Murska Sobota","Narin","Nomenj","Notranje Gorice","Nova Gorica","Novo mesto","Novo mesto Center","Novo mesto Kandija","Obrež","Okroglica","Orehova vas","Ormož","Osluševci","Ostrožno","Otoče","Otovec","Ožbalt","Paška vas","Pavlovci","Pesnica","Petrovče","Pijavice","Pivka","Planina","Plave","Podbrdo","Podčetrtek","Podčetrtek Toplice","Podgorje","Podhom","Podklanc","Podmelec","Podnart","Podplat","Podvelka","Poljčane","Polzela","Polževo","Ponikva","Ponikve na Dolenjskem","Postojna","Povir","Pragersko","Preserje","Prestranek","Prešnica","Prevalje","Pristava","Prvačina","Ptuj","Puconci","Pušenci","Rače","Radeče","Radohova vas","Radovljica","Rakek","Rakitovec","Ravne na Koroškem","Reteče","Rimske Toplice","Rjavica","Rodica","Rodik","Rogaška Slatina","Rogatec","Rosalnice","Rožni Dol","Ruše","Ruše tovarna","Ruta","Sava","Semič","Sevnica","Sežana","Slovenska Bistrica","Slovenski Javornik","Sodna vas","Solkan","Središče","Steske","Stranje","Strnišče","Sveti Danijel","Sveti Rok ob Sotli","Sveti Vid","Šalovci","Šempeter pri Gorici","Šempeter v Savinjski dolini","Šentilj","Šentjur","Šentlovrenc","Šentrupert","Šentvid pri Grobelnem","Šentvid pri Stični","Šikole","Škofja Loka","Škofljica","Šmarca","Šmarje pri Jelšah","Šmarje-Sap","Šmartno ob Paki","Šoštanj","Štanjel","Štefan","Štore","Tekačevo","Trbonje","Trbonjsko jezero","Trbovlje","Trebnje","Trebnje Kamna Gora","Trzin","Trzin ind. cona","Trzin Mlake","Tržišče","Uršna sela","Velenje","Velenje Pesje","Velika Loka","Velika Nedelja","Verd","Veržej","Vidina","Vintgar","Višnja Gora","Volčja Draga","Vuhred","Vuhred elektrarna","Vuzenica","Zagorje","Zamušani","Zazid","Zidani Most","Žalec","Žalna","Žirovnica"};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_szmain);

		odhodnaPostaja = (Spinner) findViewById(R.id.szOdhod);
		prihodnaPostaja = (Spinner) findViewById(R.id.szPrihod);
		szDate = (DatePicker) findViewById(R.id.SZDatePicker);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.seznamPostaj, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		odhodnaPostaja.setAdapter(adapter);
		prihodnaPostaja.setAdapter(adapter);

	}

	private class ArrivalsLookUp extends AsyncTask<String, Void, JSONObject> {

		private final Context context;
		private String address="";
		private int x = 0, y = 0;
		private int dan, mesec, leto;

		public ArrivalsLookUp(Context ctx) {
			this.context = ctx;
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			try {

				for (int i = 0; i < idPostaje.length; i++) {
					if(imePostaje[i].equals(String.valueOf(odhodnaPostaja.getSelectedItem()))) {
						x = idPostaje[i];

					} else if(imePostaje[i].equals(String.valueOf(prihodnaPostaja.getSelectedItem()))) {
						y = idPostaje[i];

					}
				}

//				y = 42300; //lj
//				x = 44008; //rakek
//				y = 44352; //koper
//				x = 43400; //maribor

				dan = szDate.getDayOfMonth();
				mesec = szDate.getMonth()+1;
				leto = szDate.getYear();
				address = "http://www.makroplus.si/iphone/slozel/slozel40.php?s_in="+x+"&s_out="+y+"&date="+dan+"."+mesec+"."+leto;

				final HttpGet request = new HttpGet(address);
				final HttpClient hcl = new DefaultHttpClient();
				final HttpResponse response = hcl.execute(request);
				final HttpEntity entity = response.getEntity();
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
				if(y == 0){
					Toast.makeText(context, "Prosimo izberite različne postaje.", Toast.LENGTH_SHORT).show();
				} else {
					Intent json = new Intent(SZMainActivity.this, SZResultActivity.class);
					json.putExtra("json", result.toString());
					startActivity(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void szIsci(View view) {
		final ArrivalsLookUp arrivals = new ArrivalsLookUp(getApplicationContext());
		arrivals.execute();
	}
}
