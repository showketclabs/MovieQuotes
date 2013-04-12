 //Project Name: - Movie Quotes
// Purpose of file: -search list activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

public class searchUgrade extends Activity {
	private ListView lv;
	private EditText et;
	Button shake, help, search, more, list, fav, back;
	private String listview_array[] = {};
	private ArrayList<String> array_sort = new ArrayList<String>();
	int textlength = 0;
	// Shaker shaker;
	static ListView listView;
	private ShakeListener mShaker;
	 public static String[] movie;
	 public static  String[] year;
	 public static String[] quote;
	int temp = 0;
	private mylistadapter4 adapter;
	private FrameLayout fl;
	public static String searchmode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_upgrade);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(searchUgrade.this,fl,1184,720);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		temp = 0;
		  AppRater.app_launched(searchUgrade.this);
		listView = (ListView) findViewById(R.id.list_twoligne_itineraire);
		final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		try {
			movie=Global.movie.split("~, ");
			year=Global.year.split("~, ");
			quote=Global.quotes.split("~, ");
			Log.v("Error:", Global.movie);
			Log.v("Error:", Global.year);
			Log.v("Error:", Global.quotes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mShaker = new ShakeListener(this);
		
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
			@Override
			public void onShake() {

				if (temp == 0)

				{
					temp = 1;
					finish();
					Intent intent = new Intent(searchUgrade.this, dance.class);
					
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				}
			}
		});
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				SharedPreferences sharedPreferences = getSharedPreferences(
						"MY", MODE_PRIVATE);
				String strSavedMem1 = sharedPreferences.getString("MEM2", "");
				// Toast.makeText(UIActivity.this, strSavedMem1,
				// Toast.LENGTH_LONG)
				// .show();
				if (strSavedMem1 == "") {

					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							searchUgrade.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog.setMessage("Do you want to Upgrade your app to enable Search options ?");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ActivityContext.myList.add("UpgradeSearch");
									finish();
									Intent intent = new Intent(searchUgrade.this, UpgradeActivity.class);
									
									startActivity(intent);
									overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
								}
							});

					// Setting Negative "NO" Button

					alertDialog.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									dialog.cancel();
								}
							});

					// Showing Alert Message
					alertDialog.show();
				} else {

					// upgrade work here
					// Toast.makeText(
					// getApplicationContext(),
					// getPreferences(MODE_PRIVATE).getString("prouser",
					// "")
					// + "", 500).show();
					ActivityContext.myList.add("UpgradeSearch");
					finish();
					Intent intent = new Intent(searchUgrade.this, SearchActivity.class);
					
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");
				finish();
				Intent intent = new Intent(searchUgrade.this, HelpActivity.class);
			
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				Intent intent = new Intent(searchUgrade.this, dance.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");
				finish();
				Intent intent = new Intent(searchUgrade.this, MoreActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");
				finish();
				Intent intent = new Intent(searchUgrade.this, FavActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");
				finish();
				Intent intent = new Intent(searchUgrade.this, ListActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
				 Log.v("hello back class", act+",");
				if(act.equals("UIActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
//				else if(act.equals("UpgradeSearch")){
//					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//					finish();
//					    Intent intent = new Intent(searchUgrade.this, searchUgrade.class);
//						//intent.putExtra("token",act);
//						startActivity(intent);
//						
//						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//					
//					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//					
//				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("HelpActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, HelpActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				} 
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(searchUgrade.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
			}
		});
		searchmode = getIntent().getExtras().getString("mode");
		// Toast.makeText(getApplicationContext(), searchmode, 500).show();
		// Log.v("movies",searchmode+"");

		// Log.v("movies",Global.year1+"");
		// Log.v("movies",Global.qu_arr+"");
		try{
		if (searchmode.equals("moviesearch")) {

			array_sort.clear();
			for(int i=0;i<movie.length;i++){
			array_sort.add(movie[i]);
			// Toast.makeText(getApplicationContext(), array_sort.size()+"jhmj",500).show();
			// Log.v("arraysearch",movie[i]+"");
			}
			// 5000).show();
		} else if (searchmode.equals("quoteseach")) {
			array_sort.clear();
	
			for(int i=0;i<quote.length;i++){
				array_sort.add(quote[i]);
				// Log.v("arraysearch",quote[i]+"");
				 }
			
			
		} else {

			array_sort.clear();
			for(int i=0;i<year.length;i++){
				array_sort.add(year[i]);
				// Log.v("arraysearch",year[i]+"");
			// Toast.makeText(getApplicationContext(), array_sort.size()+"",
			// 5000).show();/
				 }
		
		}
		}catch(Exception e){
			Log.e("Error", e.toString());
		}
		lv = (ListView) findViewById(R.id.list_twoligne_itineraire);
		et = (EditText) findViewById(R.id.EditText01);
		loadlist();
	Log.v("size",array_sort.size()+"");
//		
	et.addTextChangedListener(new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			// Abstract Method of TextWatcher Interface.
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// Abstract Method of TextWatcher Interface.
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			ListView listView = (ListView) findViewById(R.id.list_twoligne_itineraire);
			List<itemthreelinereveal> mListe = new ArrayList<itemthreelinereveal>();

			textlength = et.getText().length();
			// array_sort.clear();
			for (int i = 0; i < array_sort.size(); i++) {
				if (textlength <= array_sort.get(i).length()) {
					if (et.getText()
							.toString()
							.equalsIgnoreCase(
									(String) array_sort.get(i).subSequence(
											0, textlength))) {
						
						mListe.add(new itemthreelinereveal(quote[i],movie[i],year[i]
								, false));
					}
				}
			}

			mylistadapter4 adapter = new mylistadapter4(searchUgrade.this,
					mListe);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View v,
						int position, long id) {

				}
			});
			

		}

	});


	}

	private void loadlist() {

		

		List<itemthreelinereveal> mListe = new ArrayList<itemthreelinereveal>();

		// Log.v("quotes:", Global.quotes1+"");
		Log.v("quotes lenghth:", quote.length+"");
		for (int i = 0; i < quote.length; i++) {
			
		
			mListe.add(new itemthreelinereveal(quote[i],movie[i],year[i]
					, false));
			
		}
		 adapter = new mylistadapter4(searchUgrade.this, mListe);
		lv.setAdapter(adapter);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
      
		System.gc();
		Runtime rt=Runtime.getRuntime();
		 long free = rt.freeMemory();
	        long total = rt.totalMemory();
	      long  used = total - free;
	        Log.v("free time",free/(1024*1024)+"");
	        Log.v("total",total/(1024*1024)+"");
	        Log.v("used",used/(1024*1024)+"");

	}

	@Override
	public void onResume() {
		mShaker.resume();
		super.onResume();
	}

	@Override
	public void onPause() {
		mShaker.pause();
		super.onPause();
	}
	 public void onBackPressed() {
		 String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
		 Log.v("hello back class", act+",");
		if(act.equals("UIActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, UIActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("ListActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, ListActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("SearchActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, SearchActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("FavActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, FavActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("MoreActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, MoreActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("InfoActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, InfoActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
//		else if(act.equals("UpgradeSearch")){
//			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//			finish();
//			    Intent intent = new Intent(searchUgrade.this, searchUgrade.class);
//				//intent.putExtra("token",act);
//				startActivity(intent);
//				
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//			
//			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//			
//		}
		else if(act.equals("UpgradeActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, UpgradeActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("RevealActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, RevealInfo.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("ShareActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, ShareActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("ShareActivity1")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, ShareActivity1.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		else if(act.equals("HelpActivity")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, HelpActivity.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		} 
		else if(act.equals("randomquotes")){
			ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			finish();
			    Intent intent = new Intent(searchUgrade.this, randomquote.class);
				//intent.putExtra("token",act);
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			
			//Toast.makeText(getApplicationContext(), "fdf", 500).show();
			
		}
		
			

         return;
     }   
}
