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
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;


public class FavActivity extends Activity {
	Button shake, help, search, more, list, fav, back, sharelist,upg_ext;
	static ListView l;
	static ArrayList<String> m = new ArrayList<String>();
	static ArrayList<String> q = new ArrayList<String>();
	static ArrayList<String> y = new ArrayList<String>();
	String mov = "";
	String quote = "";
	String year = "";
	public static mylistadapter2 adapter;
	RelativeLayout L1;
	//Shaker shaker;
	private int temp = 0;
	AdView adView;
	private ShakeListener mShaker;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	public ListView listView;
	private FrameLayout fl;
	public static String emailist="";
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
		new AndroidScreenSize(FavActivity.this,fl,1184,720);
		try{
			adView = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4");        
		    RelativeLayout layout = (RelativeLayout)findViewById(R.id.add);        
		    layout.addView(adView);
		    AdRequest request = new AdRequest();
		    request.setTesting(false);
		    adView.loadAd(request);
		    }
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
		    		  			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    		  		}
		      }
		    });
		    AppRater.app_launched(FavActivity.this);
		//shaker = new Shaker(this, 2.25d, 500, this);
		 listView = (ListView) findViewById(R.id.listView1);
		L1 = (RelativeLayout) findViewById(R.id.id1);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		upg_ext = (Button) findViewById(R.id.upgrade_ext);
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
					alertDialog.setMessage("Do you want to Upgrade your app to enable Search options ?");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ActivityContext.myList.add("FavActivity");
									finish();

									startActivity(new Intent(FavActivity.this,
											UpgradeActivity.class));
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
					ActivityContext.myList.add("FavActivity");
					finish();
					startActivity(new Intent(FavActivity.this,
							SearchActivity.class));
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				}
			}
		});
		upg_ext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("FavActivity");
				finish();
				startActivity(new Intent(FavActivity.this, UpgradeActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("FavActivity");
				finish();
				startActivity(new Intent(FavActivity.this, HelpActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(FavActivity.this, dance.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("FavActivity");
				finish();
				startActivity(new Intent(FavActivity.this, MoreActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
//				ActivityContext.myList.add("MoreActivity");
//				finish();
//				startActivity(new Intent(FavActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("FavActivity");
				finish();
				startActivity(new Intent(FavActivity.this, ListActivity.class));
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
					    Intent intent = new Intent(FavActivity.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, searchUgrade.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
//				else if(act.equals("ShareActivity1")){
//					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//					finish();
//					    Intent intent = new Intent(ShareActivity1.this, ShareActivity1.class);
//						//intent.putExtra("token",act);
//						startActivity(intent);
//						
//						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//					
//					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//					
//				}
				else if(act.equals("HelpActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, HelpActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				} 
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(FavActivity.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}

			}
		});

		sharelist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (mylistadapter2.cnt!=0) {
					View v1 = L1.getRootView();
					v1.setDrawingCacheEnabled(true);
					Bitmap bmp = v1.getDrawingCache();
					ActivityContext.bmp=bmp;
					//ByteArrayOutputStream stream = new ByteArrayOutputStream();
//					bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//					byte[] byteArray = stream.toByteArray();
//					Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0,
//							byteArray.length);
					ActivityContext.myList.add("FavActivity");
					finish();
					Intent i = new Intent(FavActivity.this, ShareActivity.class);
					
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
				}
				else{
					
					Toast.makeText(getApplicationContext(), "No quote added to your favorite list.", 500).show();
				}
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
emailist="Quote:  " + q.get(i).replaceAll("`", "'")
+ "<br><br>" + "Movie:  " + m.get(i) + "<br><br>"
+ "Year:  " + y.get(i) + "<br><br>";
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
		ActivityContext.bmp=null;
		listView.setAdapter(null);
m.clear();
y.clear();
q.clear();
adapter=null;
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
	  public void onBackPressed() {
		  String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
			 Log.v("hello back class", act+",");
			if(act.equals("UIActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("ShareActivity1")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(ShareActivity1.this, ShareActivity1.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			} 
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(FavActivity.this, randomquote.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}

	         return;
	     }   

}
