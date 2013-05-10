// Project Name: - Movie Quotes
// Purpose of file: - Controls display of quotes after shake
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import clabs.androidscreenlibrary.AndroidScreenSize;

import com.flurry.android.FlurryAgent;
import com.revmob.RevMob;
import com.revmob.ads.banner.RevMobBanner;


public class Info_reveal extends Activity{

	TextView quote, year;
	Button addtofavbtn, sharebtn, upgradebtn,reveal;
	Button shake, help, search, more, list, fav, back;
	String m, q, y;
	TextView tv;
	String f = "info";
	 public static String[] movie1;
	 public static  String[] year1;
	 public static String[] quote1;
	public static String data;
	static int index = 0;
	static int findex = 0;
	RelativeLayout L1,main;
	ScrollView scrollView;
	String strSavedMem1;
	//Shaker shaker;
	private ShakeListener mShaker;
	private RevMob revmob;
	ViewGroup view;
	private int temp = 0;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	String act=null;
	String rdata[] = {};
	String rando_arr[] = {};
	String revdata="";
	String randomdata;
	public static String emailString="";
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(Info_reveal.this, MY_KEY);//MY_KEY is key given by them.
        FlurryAgent.onEvent("Application started");
    }
    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
	
			 setContentView(R.layout.info_activity);
	
		
		 
		
		
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		fl=(FrameLayout)findViewById(R.id.rv);
		main=(RelativeLayout)findViewById(R.id.id1);
		revmob = RevMob.start(Info_reveal.this, "517a0db434a9464b16000031");
		view = (ViewGroup) findViewById(R.id.banner);
		new AndroidScreenSize(Info_reveal.this,fl,1184,720);
		if (strSavedMem1 == "") {
			view.setVisibility(view.VISIBLE);
			
			
		} else {
			view.setVisibility(view.INVISIBLE);
			
			 main.setBackgroundResource(R.drawable.screen_5_1);
		}
            try{
			
			
			RevMobBanner banner = revmob.createBanner(Info_reveal.this);
		
			view.addView(banner);
		    }
			catch(Exception e){
				//Log.v("add",e.toString());
			}
		movie1=Global.movie.split("~, ");
		year1=Global.year.split("~, ");
		quote1=Global.quotes.split("~, ");
		
		Log.i("stack..list info reveal.......",ActivityContext.myList+"");
		try {
			dbase.getfromdata(getApplicationContext());
			Log.v("favmoview",dbase.f_movies+"");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		    mShaker = new ShakeListener(this);
		    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
		      @Override
			public void onShake()
		      {
		       
		    	  if (temp == 0)
		    		  
		    		  		{
		    		  ActivityContext.revealflag=false;
		    		  			temp = 1;
		    		  			
		    		  			String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
		 						Log.v("hello back class", act + ",");
		 						if (!act.equals("InfoActivity2")) {
		 							
		 							ActivityContext.myList.add("InfoActivity2");
		 						}
		    					Intent intent = new Intent(Info_reveal.this, dance.class);
		    					//intent.putExtra("token", "Info_reveal");
		    					startActivity(intent);
		    					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    					finish();
		    		  		}
		      }
		    });
		    AppRater.app_launched(Info_reveal.this);
		//shaker = new Shaker(this, 2.25d, 500, this);
		L1 = (RelativeLayout) findViewById(R.id.id1);
		temp = 0;
		

		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		fav = (Button) findViewById(R.id.btnfav);
		reveal = (Button) findViewById(R.id.revealbtn);
		upgradebtn = (Button) findViewById(R.id.upgradeb);
		tv = (TextView) findViewById(R.id.movie);
		scrollView=(ScrollView)findViewById(R.id.scroll);
		tv.setMovementMethod(new ScrollingMovementMethod());

		// if (Global.cnt == 1) {
		if (strSavedMem1 == "") {
			reveal.setVisibility(View.INVISIBLE);
			upgradebtn.setVisibility(View.VISIBLE);
		tv.setText((Global.datatodispaly));
		//tv.setText(rdata[0]+"\n"+rdata[1]+"\n"+rdata[2]);
		}
		else{
			reveal.setVisibility(View.VISIBLE);
			upgradebtn.setVisibility(View.INVISIBLE);
			rdata=(Global.datatodispaly).split("~");
			tv.setText(rdata[0]);
		}
		
	
			ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(Info_reveal.this);        
			scrollView.setOnTouchListener((OnTouchListener) activitySwipeDetector);
		reveal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tv.setText(rdata[0] + "~" + rdata[1] + "~" + rdata[2]);
				
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
							Info_reveal.this);

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
									ActivityContext.myList.add("InfoActivity2");
									
									Intent intent = new Intent(Info_reveal.this, UpgradeActivity.class);
									//intent.putExtra("token", "Info_reveal");
									startActivity(intent);
									
									overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
									finish();
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
					ActivityContext.myList.add("InfoActivity2");
					
					Intent intent = new Intent(Info_reveal.this, SearchActivity.class);
					//intent.putExtra("token", "Info_reveal");
					startActivity(intent);
					finish();
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("InfoActivity2");
				
				Intent intent = new Intent(Info_reveal.this, HelpActivity.class);
			//	intent.putExtra("token", "Info_reveal");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				 ActivityContext.revealflag=false;
					String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
						Log.v("hello back class", act + ",");
						if (!act.equals("InfoActivity2")) {
							
							ActivityContext.myList.add("InfoActivity2");
						}
				Intent intent = new Intent(Info_reveal.this, dance.class);
				//intent.putExtra("token", "Info_reveal");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("InfoActivity2");
				
				Intent intent = new Intent(Info_reveal.this, MoreActivity.class);
			//	intent.putExtra("token", "Info_reveal");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("InfoActivity2");
				
				Intent intent = new Intent(Info_reveal.this, ListActivity.class);
			//	intent.putExtra("token", "Info_reveal");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("InfoActivity2");
				
				Intent intent = new Intent(Info_reveal.this, FavActivity.class);
				//intent.putExtra("token", "Info_reveal");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
				 Log.v("hello back class", act+",");
				if(act.equals("UIActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
//				else if(act.equals("Info_reveal")){
//					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//					finish();
//					    Intent intent = new Intent(Info_reveal.this, Info_reveal.class);
//						//intent.putExtra("token",act);
//						startActivity(intent);
//						
//						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//					
//					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//					
//				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, searchUgrade.class);
					    intent.putExtra("mode", searchUgrade.searchmode);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
					    Intent intent = new Intent(Info_reveal.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("HelpActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
					    Intent intent = new Intent(Info_reveal.this, HelpActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(Info_reveal.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
					

			}
		});

		addtofavbtn = (Button) findViewById(R.id.favoriteb);
		addtofavbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						Info_reveal.this);

				// Setting Dialog Title
				alertDialog.setTitle("Message");

				// Setting Dialog Message
				alertDialog.setMessage("Quote Added To Your Favorite List.");

				// Setting Icon to Dialog
				// alertDialog.setIcon(R.drawable.delete);

				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							private Global global;

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								creatdatabase();
								mylistadapter2.cnt++;

								//addtofavbtn.setEnabled(false);	

							}
						});

				alertDialog.show();

			}

			private void creatdatabase() {
				SQLiteDatabase db = null;
				// CREATING DATABASE
				try {
					db = openOrCreateDatabase("EMPDATABASE.db",
							SQLiteDatabase.CREATE_IF_NECESSARY, null);
					// Toast.makeText(Info_reveal.this, "Database Created",
					// Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(Info_reveal.this,
							"Database Error... " + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
				// CREATING TABLE
				try {
					final String Create_CashBook = "CREATE TABLE IF NOT EXISTS fav1 ("

							+ "movie TEXT," + "year TEXT," + "quote Varchar2)";
					db.execSQL(Create_CashBook);
				} catch (Exception e) {
					// Toast.makeText(Info_reveal.this,
					// "Table Already exists " + e.getMessage(),
					// Toast.LENGTH_SHORT).show();

				}

				// CHECK IF ALREADY ADDED
				int index=0;
				
				
				
				
				//Log.v("quote aasgayayayayajklll,,,,,,,,,", randomdata);
				//Toast.makeText(getApplicationContext(), tv.getText()+"",500).show();
				//Log.v("randomdata", randomdata+"");
				if(strSavedMem1==""){
				try {
					randomdata = tv.getText().toString();
					 rando_arr = randomdata.split("~");
				} catch (Exception e1) {
					
				}
				Log.i("in ifff", "iffffff");
				}
				else{
					randomdata = rdata[0] + "~" + rdata[1] + "~" + rdata[2];
					 rando_arr = randomdata.split("~");
					
				}
				
				try {

					String sql = "SELECT * FROM fav1 WHERE movie == '"
							+ rando_arr[1] + "' and quote=='"
							+ (rando_arr[0].replaceAll("'", "`"))
							+ "' and year=='" + rando_arr[2] + "'";
					Cursor data = db.rawQuery(sql, null);
					if (data.moveToFirst()) {
						// Toast.makeText(getApplicationContext(),
						// "record exists", 500).show();

					} else {

						try {

							String Insert_Data = "INSERT INTO fav1 VALUES('"
									+ rando_arr[1].toString()
									+ "','"
									+ rando_arr[2].toString()
									+ "','"
									+ rando_arr[0].replaceAll("'", "`")
											.toString() + "');";

							db.execSQL(Insert_Data);
							// Toast.makeText(Info_reveal.this,
							// "Insertion successful", Toast.LENGTH_SHORT)
							// .show();
							db.close();
						} catch (Exception e) {
							Log.v("e", e.toString());
						}

						// Toast.makeText(getApplicationContext(), "not exists",
						// 500).show();
					}

				} catch (Exception e) {
					Log.v("indbase", e.toString());

				}

				// INSERTING DATA INTO TABLE
				
			}
		});
		sharebtn = (Button) findViewById(R.id.shareb);
		sharebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in shaere",
				// 500).show();
				String[] myStringArray = new String[1];
				String datatoshare[] = tv.getText().toString()
						.replaceAll("\n", "").split("~");
				myStringArray[0] = tv.getText().toString();
				ArrayList<String> data = new ArrayList<String>();
				ArrayList<String> tdata = new ArrayList<String>();
				for (int i = 0; i < myStringArray.length; i++) {
					emailString="Quote:  " + datatoshare[0] +"<br><br><br>"+ "Guess The Movie Name..?";
					tdata.add("Quote:  " + datatoshare[0] + "\n\n" );
					data.add("Quote:  " + datatoshare[0] + "\n\n"
							+ "Guess The Movie Name..?");
				}
				Global global = new Global(data, data);
				//Global appState = ((Global)getApplicationContext());
				//appState.fbdata=data;
			//	appState.textdata=tdata;
				View v1 = L1.getRootView();
				v1.setDrawingCacheEnabled(true);
				Bitmap bmp = v1.getDrawingCache();
				ActivityContext.bmp=bmp;
//				ByteArrayOutputStream stream = new ByteArrayOutputStream();
//				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//				byte[] byteArray = stream.toByteArray();
				ActivityContext.myList.add("InfoActivity2");
				
				
				// Log.v("DATA",data+"");
				Intent i = new Intent(Info_reveal.this, ShareActivity.class);
				
			//	i.putExtra("token", "Info_reveal");
				startActivity(i);
				
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}

		});
		
		upgradebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivity(new
				// Toast.makeText(getApplicationContext(), "upgrade ac", 500)
				// .show();
				ActivityContext.myList.add("InfoActivity2");
				
				Intent intent = new Intent(Info_reveal.this, UpgradeActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//shaker.close();
		movie1=null;
		year1=null;
		quote1=null;
		dbase.f_movies.clear();
		dbase.f_quotes.clear();
		dbase.f_year.clear();
		unbindDrawables(findViewById(R.id.mainlayout));
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
	  public void onResume()
	  {
	    mShaker.resume();
	    super.onResume();
	  }
	  @Override
	  public void onPause()
	  {
	    mShaker.pause();
	    super.onPause();
	  }
	  public void onBackPressed() {
		  String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
			 Log.v("hello back class", act+",");
			if(act.equals("UIActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("Info_reveal")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(Info_reveal.this, Info_reveal.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, UpgradeActivity.class);
				    intent.putExtra("mode", searchUgrade.searchmode);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(Info_reveal.this, randomquote.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
				


	         return;
	     }   
	private void unbindDrawables(View view) {
		try {
			if (view.getBackground() != null) {
				view.getBackground().setCallback(null);
			}

			if (view instanceof ViewGroup) {
				for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
					unbindDrawables(((ViewGroup) view).getChildAt(i));
				}
				try {
					((ViewGroup) view).removeAllViews();
				} catch (UnsupportedOperationException ignore) {
					// if cant remove vie no prob
				}
			}
		} catch (Exception e) {

		}
	}
	   public class ActivitySwipeDetector implements View.OnTouchListener {

	       static final String logTag = "ActivitySwipeDetector";
	       private Activity activity;
	       static final int MIN_DISTANCE = 8;
	       private float downX, downY, upX, upY;

	       public ActivitySwipeDetector(Activity activity){
	           this.activity = activity;
	       }

	       public void onRightToLeftSwipe(){
	    	 
	    	  
	    	   if (strSavedMem1 == "") {
	    	   
	    		   index=index+1;
    			   
    			   if(index >= quote1.length){
    				   index=0;
    				}
    			   
    			   String data =quote1[index] + "\n" + "~" + "\n"
							+movie1[index] + "\n" + "~" + "\n"
							+ year1[index];
					tv.setText(data);
	    		   
	    		   
	    		   
	    	   
//	    	   if (index > 0) {
//					String data =quote1[index] + "\n" + "~" + "\n"
//							+movie1[index] + "\n" + "~" + "\n"
//							+ year1[index];
//					tv.setText(data);
//					
//					index--;
//               if(index==0){
//               	index=quote1.length-1;
//               	String data1 =quote1[index] + "\n" + "~" + "\n"
//							+movie1[index] + "\n" + "~" + "\n"
//							+ year1[index];
//					tv.setText(data1);
//						
//						
//					}
//				} 
	       }
	    	   else{
	    		   
	    		   index=index+1;
    			   
    			   if(index >= quote1.length){
    				   index=0;
    				}
    			   
    			   String data = quote1[index];

	   				revdata = quote1[index].replaceAll("~", " ") + "\n" + "~"
	   						+ "\n" + year1[index].replaceAll("~", " ") + "\n" + "~"
	   						+ "\n" + movie1[index].replaceAll("~", " ");
	   				rdata = revdata.split("~");
	   				Log.v("revdata", revdata + "");
	   				randomdata=revdata;
	   				tv.setText(data);
	    		   
	    		   
	    		   
	    		   
	    		   
	    		   
//	    		   if (index > 0) {
//	   				String data = quote1[index];
//
//	   				revdata = quote1[index].replaceAll("~", " ") + "\n" + "~"
//	   						+ "\n" + year1[index].replaceAll("~", " ") + "\n" + "~"
//	   						+ "\n" + movie1[index].replaceAll("~", " ");
//	   				rdata = revdata.split("~");
//	   				Log.v("revdata", revdata + "");
//	   				randomdata=revdata;
//	   				tv.setText(data);
//
//	   				// lindex--;
//	   				index--;
//	   				if (index == 0) {
//	   					index = quote1.length - 1;
//	   					String data1 = quote1[index];
//	   					String q11 = quote1[index].replaceAll("~", " ");
//	   					String y11 = year1[index].replaceAll("~", " ");
//	   					String m11 = movie1[index].replaceAll("~", " ");
//	   					revdata = q11 + "\n" + "~" + "\n" + m11 + "\n" + "~" + "\n"
//	   							+ y11;
//	   					rdata = revdata.split("~");
//	   					Log.v("revdata", revdata + "");
//	   					randomdata=revdata;
//	   					tv.setText(data1);
//
//	   				}
//	   			}
	    		   
	    		   
	    		   
	    	   }

	       }

	       public void onLeftToRightSwipe(){
	    	   
	    	   if (strSavedMem1 == "") {
	    		   
	    		   index=index-1;
    			   
    			   if(index<0){
    				   index=quote1.length-1;
    				}
    			   
    			   String data =quote1[index] + "\n" + "~" + "\n"
							+movie1[index] + "\n" + "~" + "\n"
							+ year1[index];
					tv.setText(data);
	    		   
	    		   
	    		   
	    		   
	    		   
	    		   
//	    	   if (index < quote1.length) {
//
//					String data =quote1[index] + "\n" + "~" + "\n"
//							+movie1[index] + "\n" + "~" + "\n"
//							+ year1[index];
//					tv.setText(data);
//					// tv.setText(Global.quotes1.get(index));
//					index++;
//					//lindex = index - 2;
//					if(index==quote1.length){
//						index=0;
//						String data1 =quote1[index] + "\n" + "~" + "\n"
//								+movie1[index] + "\n" + "~" + "\n"
//								+ year1[index];
//						tv.setText(data1);
//						
//						
//					}
//				} 
	    	   }
	    	   else
	    	   {
	    		   
	    		   index=index-1;
    			   
    			   if(index<0){
    				   index=quote1.length-1;
    				}
    			   
    			   String data1 = quote1[index];
					String q1 = quote1[index].replaceAll("~", " ");
					String y1 = year1[index].replaceAll("~", " ");
					String m1 = movie1[index].replaceAll("~", " ");
					revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n"
							+ y1;
					rdata = revdata.split("~");
					Log.v("revdata", revdata + "");
					randomdata=revdata;
					tv.setText(data1);
	    		   
	    		   


//					String data = quote1[index];
//					String q1 = quote1[index].replaceAll("~", " ");
//					String y1 = year1[index].replaceAll("~", " ");
//					String m1 = movie1[index].replaceAll("~", " ");
//					revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n" + y1;
//					rdata = revdata.split("~");
//					Log.v("revdata", revdata + "");
//					randomdata=revdata;
//					tv.setText(data);
//					q1 = null;
//					y1 = null;
//					m1 = null;
//					System.gc();
//					// tv.setText(Global.quotes1.get(index));
//					index++;
//					if (index == quote1.length) {
//						index = 0;
//						String data1 = quote1[index];
//						String q11 = quote1[index].replaceAll("~", " ");
//						String y11 = year1[index].replaceAll("~", " ");
//						String m11 = movie1[index].replaceAll("~", " ");
//						revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n"
//								+ y1;
//						rdata = revdata.split("~");
//						Log.v("revdata", revdata + "");
//						randomdata=revdata;
//						tv.setText(data1);
//
//					}
					// lindex = index - 2;
				
	    	   }
	    	   
//	    	
	       }

	       public void onTopToBottomSwipe(){
	           Toast.makeText(activity, "RightToLeftSwipe", 1000).show();
	           Log.i(logTag, "onTopToBottomSwipe!");           
	       }

	       public void onBottomToTopSwipe(){
	           Toast.makeText(activity, "BottomToTopSwipe", 1000).show();
	           Log.i(logTag, "onBottomToTopSwipe!");
	       }

	       public boolean onTouch(View v, MotionEvent event) {
	           switch(event.getAction()){
	               case MotionEvent.ACTION_DOWN: {
	                   downX = event.getX();
	                   downY = event.getY();
	                   return true;
	               }
	               case MotionEvent.ACTION_UP: {
	                   upX = event.getX();
	                   upY = event.getY();

	                   float deltaX = downX - upX;
	                   float deltaY = downY - upY;

	                   // swipe horizontal?
	                   if(Math.abs(deltaX) > MIN_DISTANCE){
	                       // left or right
	                       if(deltaX < 0) { this.onLeftToRightSwipe(); return true; }
	                       if(deltaX > 0) { this.onRightToLeftSwipe(); return true; }
	                   }
	                   else {
	                           Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
	                           return false; // We don't consume the event
	                   }

//	                  
	                   return true;
	               }
	           }
	           return false;
	       }

	       }
	
}
