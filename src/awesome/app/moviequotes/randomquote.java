// Project Name: - Movie Quotes
// Purpose of file: - Controls display of quotes after shake
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.io.ByteArrayOutputStream;
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
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;


public class randomquote extends Activity {

	TextView quote, movie, year;
	Button addtofavbtn, sharebtn, upgradebtn;
	Button shake, help, search, more, list, fav, back;

	TextView tv;
	String f = "info";
	private ShakeListener mShaker;
	public static String data;
	static int index = 0;
	static int lindex = 0;
	RelativeLayout L1;
	 public static String[] movie1;
	 public static  String[] year1;
	 public static String[] quote1;
	//Shaker shaker;
	private int temp = 0;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(randomquote.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.random_quote);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(randomquote.this,fl,800,480);
		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.flayout);
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			frameLayout.setVisibility(View.VISIBLE);
		} else {
			frameLayout.setVisibility(View.INVISIBLE);
		}
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
		    		  
		    		  			startActivity(new Intent(randomquote.this, dance.class));
		    		  		}
		      }
		    });
		L1 = (RelativeLayout) findViewById(R.id.id1);
		temp = 0;
		movie = (TextView) findViewById(R.id.movie);

		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		fav = (Button) findViewById(R.id.btnfav);
		tv = (TextView) findViewById(R.id.movie);
		tv.setMovementMethod(new ScrollingMovementMethod());

		loadquoterandom();

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
							randomquote.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog.setMessage("About app");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();

									startActivity(new Intent(randomquote.this,
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
					startActivity(new Intent(randomquote.this,
							SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(randomquote.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(randomquote.this, dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(randomquote.this, MoreActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(randomquote.this, ListActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(randomquote.this, FavActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(randomquote.this, UIActivity.class));
				finish();
			}
		});

		addtofavbtn = (Button) findViewById(R.id.favoriteb);
		addtofavbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						randomquote.this);

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

								//

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
					// Toast.makeText(InfoActivity.this, "Database Created",
					// Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(randomquote.this,
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
				String randomdata = movie.getText().toString();
				String[] rando_arr = randomdata.split("~");
				//Log.v("one", rando_arr[0] + "");
			//	Log.v("two", rando_arr[1] + "");
				//Log.v("three", rando_arr[2] + "");
				try {

					String sql = "SELECT * FROM fav1 WHERE movie == '"
							+ rando_arr[1] + "' and quote=='"
							+ rando_arr[0].replaceAll("'", "`")
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

					tdata.add("Quote:  " + datatoshare[0] + "\n\n" + "Movie:  "
							+ datatoshare[1] + "\n\n" + "Year:  "
							+ datatoshare[2] + "\n\n");
					data.add("Quote:  " + datatoshare[0] + "\n\n\n"
							+ "Guess The Movie Name..?");
				}
				Global global = new Global(data, tdata);
				//Global appState = ((Global)getApplication());
				//appState.fbdata=data;
				//appState.textdata=data;
				View v1 = L1.getRootView();
				v1.setDrawingCacheEnabled(true);
				Bitmap bmp = v1.getDrawingCache();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();

				// Log.v("DATA",data+"");
				finish();
				Intent i = new Intent(randomquote.this, ShareActivity.class);
				i.putExtra("picture", byteArray);
				startActivity(i);
				

			}

		});
		upgradebtn = (Button) findViewById(R.id.upgradeb);
		upgradebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivity(new
				// Toast.makeText(getApplicationContext(), "upgrade ac", 500)
				// .show();
				finish();
				startActivity(new Intent(randomquote.this,
						UpgradeActivity.class));

			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	
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

	private void loadquoterandom() {
	   
	
		int idx = new Random().nextInt(quote1.length);

		data = quote1[idx] + "\n" + "~" + "\n" + movie1[idx] + "\n" + "~" + "\n" + year1[idx];

		tv.setText(data);
		
	}



}