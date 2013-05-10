// Project Name: - Movie Quotes
// Purpose of file: - Activity to reveal quote info like movie name and year
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;
import java.util.Random;

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

public class RevealInfo extends Activity {

	TextView quote, movie, year;
	Button sharebtn, upgradebtn, back, b;
	Button shake, help, search, more, list, fav, reveal;

	String m, q, y;
	TextView tv;
	String f = "info";
	public static String data;
	static int index = 0;
	static int lindex = 0;
	String rquote, rmovie, ryear;
	ScrollView scrollview;
	// String rdata1[];
	public static String[] movie1;
	public static String[] year1;
	public static String[] quote1;
	// Shaker shaker;
	private ShakeListener mShaker;
	RelativeLayout L1, main;
	private int temp = 0;
	String rdata[] = {};
	String MY_KEY = "MWZY29QZHHSWXYQS8DYN";
	public String revdata;
	private FrameLayout fl;
	
	int idx;
    public static String emailstring="";
	@Override
	protected void onStart() {
		super.onStart();
		FlurryAgent.onStartSession(RevealInfo.this, MY_KEY);// MY_KEY is key
															// given by them.
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
		setContentView(R.layout.revealinfo);
		fl = (FrameLayout) findViewById(R.id.rv);
		main = (RelativeLayout) findViewById(R.id.id1);
		
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");

		
		
		new AndroidScreenSize(RevealInfo.this, fl, 1184, 720);
		movie1 = Global.movie.split("~, ");
		year1 = Global.year.split("~, ");
		quote1 = Global.quotes.split("~, ");
		Log.v("Error:", Global.movie);
		Log.v("Error:", Global.year);
		Log.v("Error:", Global.quotes);
		final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		mShaker = new ShakeListener(this);
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
			@Override
			public void onShake() {

				if (temp == 0)

				{
					ActivityContext.revealflag=true;
					temp = 1;
					 ActivityContext.revealflag=false;
					// if(ActivityContext.myList.size()>0)
//						{
//						String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
//						Log.v("hello back class", act + ",");
//						if (!act.equals("RevealActivity")) {
//							
//							ActivityContext.myList.add("RevealActivity");
//						}
//						}
					Intent intent = new Intent(RevealInfo.this, dance.class);

					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
					finish();
					
				}
			}
		});
		AppRater.app_launched(RevealInfo.this);
		movie = (TextView) findViewById(R.id.movie);
		L1 = (RelativeLayout) findViewById(R.id.id1);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		reveal = (Button) findViewById(R.id.revealbtn);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		b = (Button) findViewById(R.id.button1);
		scrollview = (ScrollView) findViewById(R.id.scroll);

		tv = (TextView) findViewById(R.id.movie);
		temp = 0;

		Log.i("stack.reveal Info........",ActivityContext.myList+"");
//		String act = ActivityContext.myList.get(ActivityContext.myList
//				.size() - 1);
//		Log.i("inside reveal", "inside random revea"+act);
//		
//		if (act.equals("RevealActivity")) {
//			ActivityContext.myList.remove(ActivityContext.myList
//				.size() - 1);
//		}
//		if(ActivityContext.revealflag==false){
//		rdata = Global.datatodispaly.split("~");
//
//		tv.setText(rdata[0]);
//		}
//		else{
		if(ActivityContext.fromupgrade==true){
			//Toast.makeText(getApplicationContext(), "in ifff", 500).show();
			String datafromrandom[]=Global.datatodispaly.split("~");
			tv.setText((datafromrandom[0]));
			rdata=datafromrandom;
			}
		else{
			loadquoterandom();
			rdata=new String[3];
			rdata[0]=quote1[idx]+"\n";
			rdata[1]="\n"+movie1[idx]+"\n";
			rdata[2]="\n"+year1[idx];
		}
		//}

		ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(
				RevealInfo.this);
		if(ActivityContext.forswipecheck==true)
		{
		scrollview.setOnTouchListener((OnTouchListener) activitySwipeDetector);
		ActivityContext.forswipecheck=false;
		}
	
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
							RevealInfo.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog
							.setMessage("Do you want to Upgrade your app to enable Search options ?");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ActivityContext.myList
											.add("RevealActivity");
								
									Intent intent = new Intent(RevealInfo.this,
											UpgradeActivity.class);

									startActivity(intent);
									overridePendingTransition(
											R.anim.slide_in_left,
											R.anim.slide_out_left);
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
					ActivityContext.myList.add("RevealActivity");
					
					Intent intent = new Intent(RevealInfo.this,
							SearchActivity.class);

					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
					finish();
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("RevealActivity");
				
				Intent intent = new Intent(RevealInfo.this, HelpActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				//ActivityContext.revealflag=true;
				 ActivityContext.revealflag=false;
//				 if(ActivityContext.myList.size()>0)
//					{
//					String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
//					Log.v("hello back class", act + ",");
//					if (!act.equals("RevealActivity")) {
//						
//						ActivityContext.myList.add("RevealActivity");
//					}
//					}
				Intent intent = new Intent(RevealInfo.this, dance.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("RevealActivity");
				
				Intent intent = new Intent(RevealInfo.this, MoreActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("RevealActivity");
				
				Intent intent = new Intent(RevealInfo.this, ListActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("RevealActivity");
				
				Intent intent = new Intent(RevealInfo.this, FavActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String act = ActivityContext.myList.get(ActivityContext.myList
						.size() - 1);
				Log.v("hello back class", act + ",");
				
				
				if (act.equals("UIActivity")) {
					//ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
				
					Intent intent = new Intent(RevealInfo.this,
							UIActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ListActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							ListActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("SearchActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							SearchActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("FavActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							FavActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("MoreActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							MoreActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("InfoActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							InfoActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("UpgradeSearch")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							searchUgrade.class);
					intent.putExtra("mode", searchUgrade.searchmode);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("UpgradeActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							UpgradeActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
				// else if(act.equals("RevealActivity")){
				// ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				// finish();
				// Intent intent = new Intent(RevealInfo.this,
				// RevealInfo.class);
				// //intent.putExtra("token",act);
				// startActivity(intent);
				//
				// overridePendingTransition(R.anim.slide_in_right,
				// R.anim.slide_out_right);
				//
				// //Toast.makeText(getApplicationContext(), "fdf", 500).show();
				//
				// }
				else if (act.equals("ShareActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							ShareActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ShareActivity1")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							ShareActivity1.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
				else if (act.equals("InfoActivity2")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							Info_reveal.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
				else if (act.equals("HelpActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							HelpActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("randomquotes")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					
					Intent intent = new Intent(RevealInfo.this,
							randomquote.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}

			}
		});

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						RevealInfo.this);

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

							}

							private void creatdatabase() {
								SQLiteDatabase db = null;
								// CREATING DATABASE
								try {
									db = openOrCreateDatabase("EMPDATABASE.db",
											SQLiteDatabase.CREATE_IF_NECESSARY,
											null);
									// Toast.makeText(InfoActivity.this,
									// "Database Created",
									// Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									Toast.makeText(
											RevealInfo.this,
											"Database Error... "
													+ e.getMessage(),
											Toast.LENGTH_SHORT).show();
								}
								// CREATING TABLE
								try {
									final String Create_CashBook = "CREATE TABLE IF NOT EXISTS fav1 ("

											+ "movie TEXT,"
											+ "year TEXT,"
											+ "quote Varchar2)";
									db.execSQL(Create_CashBook);
								} catch (Exception e) {
									// Toast.makeText(InfoActivity.this,
									// "Table Already exists " + e.getMessage(),
									// Toast.LENGTH_SHORT).show();

								}

								// CHECK IF ALREADY ADDED
								// String randomdata =
								// movie.getText().toString();
								// String[] rando_arr = randomdata.split("~");
								// Log.v("one", rando_arr[0] + "");
								// Log.v("two", rando_arr[1] + "");
								// Log.v("three", rando_arr[2] + "");
								try {
									String sql = "SELECT * FROM fav1 WHERE movie == '"
											+ rdata[1]
											+ "' and quote=='"
											+ (rdata[0].replaceAll("'", "`"))
											+ "' and year=='" + rdata[2] + "'";
									Cursor data = db.rawQuery(sql, null);
									if (data.moveToFirst()) {
										// Toast.makeText(c, "record exists",
										// 500).show();

									} else {

										try {

											String Insert_Data = "INSERT INTO fav1 VALUES('"
													+ rdata[1].toString()
													+ "','"
													+ rdata[2].toString()
													+ "','"
													+ rdata[0].replaceAll("'",
															"`").toString()
													+ "');";

											db.execSQL(Insert_Data);
											// Toast.makeText(InfoActivity.this,
											// "Insertion successful",
											// Toast.LENGTH_SHORT)
											// .show();
											db.close();
										} catch (Exception e) {
											Log.v("e", e.toString());
										}

										// Toast.makeText(getApplicationContext(),
										// "not exists",
										// 500).show();
									}

								} catch (Exception e) {
									Log.v("indbase", e.toString());

								}

								// INSERTING DATA INTO TABLE
								db.close();
							}
						});

				alertDialog.show();
			}
		});

		sharebtn = (Button) findViewById(R.id.shareb);

		sharebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String[] myStringArray = new String[1];
				String datatoshare[] = tv.getText().toString()
						.replaceAll("\n", "").split("~");
				myStringArray[0] = tv.getText().toString();
				// Toast.makeText(getApplicationContext(), ""+myStringArray[0],
				// 500).show();
				ArrayList<String> data = new ArrayList<String>();
				ArrayList<String> tdata = new ArrayList<String>();
			//	if(ActivityContext.revealflag!=true){
				for (int i = 0; i < myStringArray.length; i++) {
					emailstring="Quote:  " + datatoshare[0] +"<br><br><br>"+ "Guess The Movie Name..?";
					tdata.add("Quote:  " + rdata[0] + "\n\n" + "Movie:  "
							+ rdata[1] + "\n\n" + "Year:  " + rdata[2] + "\n\n");
					data.add("Quote:  " + rdata[0] + "\n\n\n"
							+ "Guess The Movie Name..?");
				}
				Global global = new Global(data, data);
				//}
//			    else{
//			    	emailstring="Quote:  " + quote1[idx] +"<br><br><br>"+ "Guess The Movie Name..?";
//			    	Log.i("data when revel:::", quote1[idx]+"");
//				tdata.add("Quote:  " +quote1[idx] + "\n\n" + "Movie:  "
//						+ movie1[idx] + "\n\n" + "Year:  " + year1[idx] + "\n\n");
//				data.add("Quote:  " + quote1[idx] + "\n\n\n"
//						+ "Guess The Movie Name..?");
//				Global global = new Global(data, data);
//				
//		    	}
				
				data = null;
				tdata = null;
				System.gc();
				// Global appState = ((Global)getApplication());
				// appState.fbdata=data;
				// appState.textdata=tdata;
				View v1 = L1.getRootView();
				v1.setDrawingCacheEnabled(true);
				Bitmap bmp = v1.getDrawingCache();
				ActivityContext.bmp = bmp;
				
				ActivityContext.myList.add("RevealActivity");
				
				// Log.v("DATA",data+"");
				Intent i = new Intent(RevealInfo.this, ShareActivity.class);
				// i.putExtra("picture", byteArray);

				startActivity(i);
				
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
			}

		});
		//
		reveal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				if(ActivityContext.revealflag==true){
//				tv.setText(rdata[0]  + "\n"+ "~" + "\n"+ rdata[1]  + "\n"+ "~" + "\n"+ rdata[2]);
//				}
//				else{
				if(ActivityContext.fromupgrade==true){
					String datafromrandom[]=Global.datatodispaly.split("~");
					tv.setText(datafromrandom[0] + "~" + datafromrandom[1] + "~" + datafromrandom[2]);
					ActivityContext.fromupgrade=false;
				}
				else{
					tv.setText(rdata[0] + "~" + rdata[1] + "~" + rdata[2]);
				}
//					data = quote1[idx] + "\n" + "~" + "\n" + movie1[idx] + "\n" + "~" + "\n" + year1[idx];
//					tv.setText(data);
				//}
				// reveal.setEnabled(false);
				// b.setEnabled(true);

			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// shaker.close();
		unbindDrawables(findViewById(R.id.mainlayout));
		movie1 = null;
		year1 = null;
		quote1 = null;
		System.gc();
		Runtime rt = Runtime.getRuntime();
		long free = rt.freeMemory();
		long total = rt.totalMemory();
		long used = total - free;
		Log.v("free time", free / (1024 * 1024) + "");
		Log.v("total", total / (1024 * 1024) + "");
		Log.v("used", used / (1024 * 1024) + "");

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

		String act = ActivityContext.myList.get(ActivityContext.myList
				.size() - 1);
		Log.i("inside reveal", "inside random revea"+act);
		
		if (act.equals("UIActivity")) {
			//ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
		
			Intent intent = new Intent(RevealInfo.this,
					UIActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();

			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} 
		
		else if (act.equals("RevealActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					ListActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();

			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		}
		else if (act.equals("ListActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					ListActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();

			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("SearchActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					SearchActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("FavActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					FavActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("MoreActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					MoreActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("InfoActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					InfoActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} 
		
		else if (act.equals("InfoActivity2")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					Info_reveal.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		}
		else if (act.equals("UpgradeSearch")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					searchUgrade.class);
			intent.putExtra("mode", searchUgrade.searchmode);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("UpgradeActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					UpgradeActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		}
		// else if(act.equals("RevealActivity")){
		// ActivityContext.myList.remove(ActivityContext.myList.size()-1);
		// finish();
		// Intent intent = new Intent(RevealInfo.this,
		// RevealInfo.class);
		// //intent.putExtra("token",act);
		// startActivity(intent);
		//
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_right);
		//
		// //Toast.makeText(getApplicationContext(), "fdf", 500).show();
		//
		// }
		else if (act.equals("ShareActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					ShareActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("ShareActivity1")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					ShareActivity1.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		}

		else if (act.equals("HelpActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					HelpActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		} else if (act.equals("randomquotes")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			
			Intent intent = new Intent(RevealInfo.this,
					randomquote.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf",
			// 500).show();

		}

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

		public ActivitySwipeDetector(Activity activity) {
			this.activity = activity;
		}

		public void onRightToLeftSwipe() {
			
			
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

				tv.setText(data);
			
			
			
//			if (index > 0) {
//				String data = quote1[index];
//
//				revdata = quote1[index].replaceAll("~", " ") + "\n" + "~"
//						+ "\n" + year1[index].replaceAll("~", " ") + "\n" + "~"
//						+ "\n" + movie1[index].replaceAll("~", " ");
//				rdata = revdata.split("~");
//				Log.v("revdata", revdata + "");
//
//				tv.setText(data);
//
//				// lindex--;
//				index--;
//				if (index == 0) {
//					index = quote1.length - 1;
//					String data1 = quote1[index];
//					String q11 = quote1[index].replaceAll("~", " ");
//					String y11 = year1[index].replaceAll("~", " ");
//					String m11 = movie1[index].replaceAll("~", " ");
//					revdata = q11 + "\n" + "~" + "\n" + m11 + "\n" + "~" + "\n"
//							+ y11;
//					rdata = revdata.split("~");
//					Log.v("revdata", revdata + "");
//
//					tv.setText(data1);
//
//				}
//			} else {
//
//			}

			// rdata.notifyAll();
			// Toast.makeText(activity, "RightToLeftSwipe", 1000).show();
			// Log.e(logTag, "RightToLeftSwipe!");
		}

		public void onLeftToRightSwipe() {
			
			 index=index-1;
			   
			   if(index<0){
				   index=quote1.length-1;
				}
			   
			    String data = quote1[index];
				String q1 = quote1[index].replaceAll("~", " ");
				String y1 = year1[index].replaceAll("~", " ");
				String m1 = movie1[index].replaceAll("~", " ");
				revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n" + y1;
				rdata = revdata.split("~");
				Log.v("revdata", revdata + "");

				tv.setText(data);
			
			
			
			
			
			
			
			
//			if (index < quote1.length) {
//
//				String data = quote1[index];
//				String q1 = quote1[index].replaceAll("~", " ");
//				String y1 = year1[index].replaceAll("~", " ");
//				String m1 = movie1[index].replaceAll("~", " ");
//				revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n" + y1;
//				rdata = revdata.split("~");
//				Log.v("revdata", revdata + "");
//
//				tv.setText(data);
//				q1 = null;
//				y1 = null;
//				m1 = null;
//				System.gc();
//				// tv.setText(Global.quotes1.get(index));
//				index++;
//				if (index == quote1.length) {
//					index = 0;
//					String data1 = quote1[index];
//					String q11 = quote1[index].replaceAll("~", " ");
//					String y11 = year1[index].replaceAll("~", " ");
//					String m11 = movie1[index].replaceAll("~", " ");
//					revdata = q1 + "\n" + "~" + "\n" + m1 + "\n" + "~" + "\n"
//							+ y1;
//					rdata = revdata.split("~");
//					Log.v("revdata", revdata + "");
//
//					tv.setText(data1);
//
//				}
//				// lindex = index - 2;
//			} else {
//				// Toast.makeText(InfoActivity.this, "right Ended",
//				// Toast.LENGTH_SHORT).show();
//			}
			// rdata.notifyAll();
			// Toast.makeText(activity, "RightToLeftSwipe", 1000).show();
			// Log.e(logTag, "LeftToRightSwipe!");
		}

		public void onTopToBottomSwipe() {
			//Toast.makeText(activity, "RightToLeftSwipe", 1000).show();
			Log.i(logTag, "onTopToBottomSwipe!");
		}

		public void onBottomToTopSwipe() {
			//Toast.makeText(activity, "BottomToTopSwipe", 1000).show();
			Log.i(logTag, "onBottomToTopSwipe!");
		}

		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
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
				if (Math.abs(deltaX) > MIN_DISTANCE) {
					// left or right
					if (deltaX < 0) {
						this.onLeftToRightSwipe();
						return true;
					}
					if (deltaX > 0) {
						this.onRightToLeftSwipe();
						return true;
					}
				} else {
					Log.i(logTag, "Swipe was only " + Math.abs(deltaX)
							+ " long, need at least " + MIN_DISTANCE);
					return false; // We don't consume the event
				}

				// // swipe vertical?
				// if(Math.abs(deltaY) > MIN_DISTANCE){
				// // top or down
				// if(deltaY < 0) { this.onTopToBottomSwipe(); return true; }
				// if(deltaY > 0) { this.onBottomToTopSwipe(); return true; }
				// }
				// else {
				// Log.i(logTag, "Swipe was only " + Math.abs(deltaX) +
				// " long, need at least " + MIN_DISTANCE);
				// return false; // We don't consume the event
				// }

				return true;
			}
			}
			return false;
		}

	}
	private void loadquoterandom() {
		   
		
	   idx = new Random().nextInt(quote1.length);

		data = quote1[idx];

		tv.setText(data);
		
	}
}
