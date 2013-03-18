// Project Name: - Movie Quotes
// Purpose of file: - Stores favorite quotes to database
// Developed by Showket Ahmad,..Clicklabs pvt. ltd.

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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdView;


public class FavActivity extends Activity {
	Button shake, help, search, more, list, fav, back, sharelist;
	static ListView l;
	static ArrayList<String> m = new ArrayList<String>();
	static ArrayList<String> q = new ArrayList<String>();
	static ArrayList<String> y = new ArrayList<String>();
	String mov = "";
	String quote = "";
	String year = "";
	static mylistadapter2 adapter;
	RelativeLayout L1;
	//Shaker shaker;
	private int temp = 0;
	private ShakeListener mShaker;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	public ListView listView;
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(FavActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			setContentView(R.layout.favorites_activity);
		} else {
			setContentView(R.layout.favorite_activity1);
		}
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(FavActivity.this,fl,800,480);
		try{
			AdView adview = (AdView)findViewById(R.id.ad);
			AdRequest re = new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);}
			catch(Exception e){
				//Log.v("add",e.toString());
			}
		temp = 0;
		 final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		    mShaker = new ShakeListener(this);
		    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
		      @Override
			public void onShake()
		      {
//		        vibe.vibrate(100);
//		        new AlertDialog.Builder(UIActivity.this)
//		          .setPositiveButton(android.R.string.ok, null)
//		          .setMessage("Shooken!")
//		          .show();
		    	  if (temp == 0)
		    		  
		    		  		{
		    		  			temp = 1;
		    		  			finish();
		    		  			
		    		  			startActivity(new Intent(FavActivity.this, dance.class));
		    		  		}
		      }
		    });
		//shaker = new Shaker(this, 2.25d, 500, this);
		 listView = (ListView) findViewById(R.id.listView1);
		L1 = (RelativeLayout) findViewById(R.id.id1);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		sharelist = (Button) findViewById(R.id.btnsharelist);
		fav.setEnabled(false);
		fav.setBackgroundResource(R.drawable.favouritep);
		sharelist.setEnabled(false);
		l = (ListView) findViewById(R.id.listView1);

		l.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
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
							FavActivity.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog.setMessage("Do you want to upgrade your app enable Search options ?");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();

									startActivity(new Intent(FavActivity.this,
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
					startActivity(new Intent(FavActivity.this,
							SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, MoreActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, ListActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(FavActivity.this, UIActivity.class));
				finish();

			}
		});

		sharelist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View v1 = L1.getRootView();
				v1.setDrawingCacheEnabled(true);
				Bitmap bmp = v1.getDrawingCache();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();

				Intent i = new Intent(FavActivity.this, ShareActivity.class);
				i.putExtra("picture", byteArray);
				startActivity(i);
				finish();
			}
		});

		databasedata();
		loadlist();

	}

	public ListView getListView() {
		return l;
	}

	public void getSwipeItem(boolean isRight, int position) {

	}

	public void onItemClickListener(ListAdapter adapter, int position) {
		// Toast.makeText(this, "Single tap on item position " + position,
		// Toast.LENGTH_SHORT).show();
	}

	public void databasedata() {

		SQLiteDatabase db = null;
		// CREATING DATABASE

		try {
			// database creatin and check
			try {
				db = openOrCreateDatabase("EMPDATABASE.db",
						SQLiteDatabase.CREATE_IF_NECESSARY, null);
				// Toast.makeText(FavActivity.this,
				// "Database Created",Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				// Toast.makeText(FavActivity.this,
				// "Database not Created "+e.getMessage(),Toast.LENGTH_SHORT).show();
			}

			try {
				Cursor allrows = db.rawQuery("SELECT * FROM fav1", null);
				allrows.moveToFirst();
				m.clear();
				q.clear();
				y.clear();
				while (allrows.isAfterLast() == false) {
					m.add(allrows.getString(0));
					q.add(allrows.getString(2));
					y.add(allrows.getString(1));
					allrows.moveToNext();
				}
				if (m.size() != 0) {
					sharelist.setEnabled(true);
				}
				ArrayList<String> data = new ArrayList<String>();
				for (int i = 0; i < m.size(); i++) {

					data.add("Quote:  " + q.get(i).replaceAll("`", "'")
							+ "\n\n" + "Movie:  " + m.get(i) + "\n\n"
							+ "Year:  " + y.get(i) + "\n\n");
				}
				Global global = new Global(data, data);
				//Global appState = ((Global)getApplicationContext());
				//appState.fbdata=data;
				//appState.textdata=data;
			} catch (Exception e) {
				// Log.v("mov name",e.toString());

			}
			db.close();
		} catch (Exception e) {
			Log.v("indbase", e.toString());

		}
	}

	private void loadlist() {

	    adapter = new mylistadapter2(FavActivity.this,getApplicationContext(),q,m,y);
		listView.setAdapter(adapter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		listView.setAdapter(null);
m.clear();
y.clear();
q.clear();
//m=new ArrayList<String>();
//y=new ArrayList<String>();
//q=new ArrayList<String>();
System.gc();
Runtime rt=Runtime.getRuntime();
 long free = rt.freeMemory();
    long total = rt.totalMemory();
  long  used = total - free;
    Log.v("free time",free/(1024*1024)+"");
    Log.v("total",total/(1024*1024)+"");
    Log.v("used",used/(1024*1024)+"");
		//shaker.close();

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


}
