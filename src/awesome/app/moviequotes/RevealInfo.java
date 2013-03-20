// Project Name: - Movie Quotes
// Purpose of file: - Activity to reveal quote info like movie name and year
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.io.ByteArrayOutputStream;
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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdView;


public class RevealInfo extends Activity  {

	TextView quote, movie, year;
	Button sharebtn, upgradebtn, back,b;
	Button shake, help, search, more, list, fav, reveal;
	String m, q, y;
	TextView tv;
	String f = "info";
	public static String data;
	static int index = 0;
	static int lindex = 0;
	String rquote, rmovie, ryear;
	//String rdata1[];
	 public static String[] movie1;
	 public static  String[] year1;
	 public static String[] quote1;
	//Shaker shaker;
	private ShakeListener mShaker;
	RelativeLayout L1;
	private int temp = 0;
	 String rdata[]={};
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	public String revdata;
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(RevealInfo.this, MY_KEY);//MY_KEY is key given by them.
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
		AdView adview = (AdView)findViewById(R.id.ad);
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		
		if (strSavedMem1 == "") {
			adview.setVisibility(adview.VISIBLE);
		} else {
			adview.setVisibility(adview.INVISIBLE);
		}
		try{
			
			AdRequest re = new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);}
			catch(Exception e){
				//Log.v("add",e.toString());
			}
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(RevealInfo.this,fl,800,480);
		movie1=Global.movie.split("~, ");
		year1=Global.year.split("~, ");
		quote1=Global.quotes.split("~, ");
		 final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		    mShaker = new ShakeListener(this);
		    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
		      @Override
			public void onShake()
		      {
		       
		    	  if (temp == 0)
		    		  
		    		  		{
		    		  			temp = 1;
		    		  			finish();
		    		  
		    		  			startActivity(new Intent(RevealInfo.this, dance.class));
		    		  		}
		      }
		    });
		
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
		
	
		tv = (TextView) findViewById(R.id.movie);
		temp = 0;
	
	     rdata =  Global.datatodispaly.split("~");
	
		tv.setText(rdata[0]);
		
	
		tv.setOnTouchListener(new onswipeforinfo() {
			@Override
			public void onSwipeRight() {
				
				if (index <quote1.length) {

					String data = quote1[index] ; 
					String q1=quote1[index].replaceAll("~", " ");
					String y1=year1[index].replaceAll("~", " ");
					String m1=movie1[index].replaceAll("~", " ");
				revdata=q1+ "\n" + "~" + "\n"
						+ m1 + "\n" + "~" + "\n"
						+ y1;
				rdata=revdata.split("~");
				Log.v("revdata",revdata+"");
				
					tv.setText(data);
					q1=null;y1=null;m1=null;System.gc();
					// tv.setText(Global.quotes1.get(index));
					index++;
					lindex = index - 2;
				} else {
					// Toast.makeText(InfoActivity.this, "right Ended",
					// Toast.LENGTH_SHORT).show();
				}
				rdata.notifyAll();
			}

			@Override
			public void onSwipeLeft() {
				// Toast.makeText(InfoActivity.this, "left", Toast.LENGTH_SHORT)
				// .show();

				if (index >= 0) {
					String data = quote1[lindex] ; 
					
				revdata=quote1[lindex].replaceAll("~", " ")+ "\n" + "~" + "\n"
						+ year1[lindex].replaceAll("~", " ") + "\n" + "~" + "\n"
						+ movie1[lindex].replaceAll("~", " ");
					rdata=revdata.split("~");
					Log.v("revdata",revdata+"");
					
					tv.setText(data);
					
					lindex--;
					index = lindex + 1;
				} else {
					// Toast.makeText(InfoActivity.this, "Left Ended",
					// Toast.LENGTH_SHORT).show();

				}
				if (lindex < 0) {
					lindex = 0;
					index = 0;
				}
				rdata.notifyAll();
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
							RevealInfo.this);

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
									finish();

									startActivity(new Intent(RevealInfo.this,
											UpgradeActivity.class));
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

					finish();
					startActivity(new Intent(RevealInfo.this, SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(RevealInfo.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(RevealInfo.this, dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(RevealInfo.this, MoreActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(RevealInfo.this, ListActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(RevealInfo.this, FavActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(RevealInfo.this, UIActivity.class));
				finish();
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

								

							}

							private void creatdatabase() {
								SQLiteDatabase db = null;
								// CREATING DATABASE
								try {
									db = openOrCreateDatabase("EMPDATABASE.db",
											SQLiteDatabase.CREATE_IF_NECESSARY, null);
									// Toast.makeText(InfoActivity.this, "Database Created",
									// Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									Toast.makeText(RevealInfo.this,
											"Database Error... " + e.getMessage(),
											Toast.LENGTH_SHORT).show();
								}
								// CREATING TABLE
								try {
									final String Create_CashBook = "CREATE TABLE IF NOT EXISTS fav1 ("

											+ "movie TEXT," + "year TEXT," + "quote Varchar2)";
									db.execSQL(Create_CashBook);
								} catch (Exception e) {
									// Toast.makeText(InfoActivity.this,
									// "Table Already exists " + e.getMessage(),
									// Toast.LENGTH_SHORT).show();

								}

								// CHECK IF ALREADY ADDED
//								String randomdata = movie.getText().toString();
//								String[] rando_arr = randomdata.split("~");
//								Log.v("one", rando_arr[0] + "");
//								Log.v("two", rando_arr[1] + "");
//								Log.v("three", rando_arr[2] + "");
								try {
									String sql = "SELECT * FROM fav1 WHERE movie == '" + rdata[1]
											+ "' and quote=='" + rdata[0].replaceAll("'", "`")
											+ "' and year=='" + rdata[2] + "'";
									Cursor data = db.rawQuery(sql, null);
									if (data.moveToFirst()) {
										// Toast.makeText(c, "record exists", 500).show();

									
									
									} else {

										try {
											
											String Insert_Data = "INSERT INTO fav1 VALUES('"
													+ rdata[1].toString()
													+ "','"
													+ rdata[2].toString()
													+ "','"
													+ rdata[0].replaceAll("'", "`")
															.toString() + "');";

											db.execSQL(Insert_Data);
											// Toast.makeText(InfoActivity.this,
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
			//	Toast.makeText(getApplicationContext(), ""+myStringArray[0], 500).show();
				ArrayList<String> data = new ArrayList<String>();
				ArrayList<String> tdata = new ArrayList<String>();
				for (int i = 0; i < myStringArray.length; i++) {

					tdata.add("Quote:  " + rdata[0] + "\n\n" + "Movie:  "
							+ rdata[1] + "\n\n" + "Year:  "
							+ rdata[2] + "\n\n");
					data.add("Quote:  " + rdata[0] + "\n\n\n"
							+ "Guess The Movie Name..?");
				}
				Global global = new Global(data, tdata);
				data=null;
				tdata=null;
				System.gc();
				//Global appState = ((Global)getApplication());
				//appState.fbdata=data;
				//appState.textdata=tdata;
				View v1 = L1.getRootView();
				v1.setDrawingCacheEnabled(true);
				Bitmap bmp = v1.getDrawingCache();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();

				// Log.v("DATA",data+"");
				Intent i = new Intent(RevealInfo.this, ShareActivity.class);
				i.putExtra("picture", byteArray);
				startActivity(i);
				finish();

			}

		});
//		
		reveal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				tv.setText(rdata[0] + "~" + rdata[1] + "~"
						+ rdata[2]);
			//	reveal.setEnabled(false);
				//b.setEnabled(true);
				
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	//	shaker.close();
		unbindDrawables(findViewById(R.id.mainlayout));
		movie1=null;
		year1=null;
		quote1=null;
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

	

}
