// Project Name: - Movie Quotes
// Purpose of file: - Control display of quotes on Quote Activity
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import clabs.androidscreenlibrary.AndroidScreenSize;

import com.flurry.android.FlurryAgent;
import com.revmob.RevMob;
import com.revmob.ads.banner.RevMobBanner;


public class ListActivity extends Activity{
	Button shake, help, search, more, list, fav, req, back;
	//Shaker shaker;
	int temp = 0;
	ProgressBar bar;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private ShakeListener mShaker;
	ListView listView;
	String urlOfImageToDownload="http://i45.tinypic.com/36b6s.png";
	 Uri mediaStoreImageUri=null;
	 File destinationFile=null;
	 Intent i;
	 private RevMob revmob;
	 static final int responce = 1;  
	    static final int resul = 0;
	private MyListAdapterTwoLine adapter;
	private FrameLayout fl;
	ActivityContext ac;
	  
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(ListActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		if (strSavedMem1 == "") {
			setContentView(R.layout.listactivity_nexus);
			try{
				
				
				revmob = RevMob.start(ListActivity.this, "517a0db434a9464b16000031");
				RevMobBanner banner = revmob.createBanner(ListActivity.this);
				ViewGroup view = (ViewGroup) findViewById(R.id.banner);
				view.addView(banner);
			}
			catch(Exception e){
				Log.v("add",e.toString());
			}
		} else {
			setContentView(R.layout.list_activity_2);
		}
		fl=(FrameLayout)findViewById(R.id.rv);
		
		
		Log.i("activity context", ActivityContext.myList+"");
		new AndroidScreenSize(ListActivity.this,fl,1184,720);
		//caller=getIntent().getExtras().getString("token");
	
		Log.i("stack..list Actity.......",ActivityContext.myList+"");
		
		
		temp = 0;
	
		 final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		    mShaker = new ShakeListener(this);
		    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
		      @Override
			public void onShake()
		      {
//		       
		    	  if (temp == 0)
		    		  
		    		  		{
		    		  ActivityContext.revealflag=false;
		    		  ActivityContext.revealflag=false;
						String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
						Log.v("hello back class", act + ",");
						if (!act.equals("ListActivity")) {
							
							ActivityContext.myList.add("ListActivity");
						}
		    		  			temp = 1;
		    		  			//ActivityContext.myList.add("dance.class");
		    		  			finish();
		    					Intent intent = new Intent(ListActivity.this, dance.class);
		    					//intent.putExtra("token", "ListActivity");
		    					
		    					startActivity(intent);
		    		  			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    		  		}
		      }
		    });
		    AppRater.app_launched(ListActivity.this);
		// shaker = new Shaker(this, 2.25d, 500, this);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);

		fav = (Button) findViewById(R.id.btnfav);
		req = (Button) findViewById(R.id.btnreq);
		back = (Button) findViewById(R.id.btnback);
		list.setEnabled(false);
		list.setBackgroundResource(R.drawable.quotespressed);
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
							ListActivity.this);

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
									ActivityContext.myList.add("ListActivity");
									finish();
									Intent intent = new Intent(ListActivity.this, UpgradeActivity.class);
									//ActivityContext.myList.add("UpgradeActivity.class");
								//	intent.putExtra("token", "ListActivity");
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
					ActivityContext.myList.add("ListActivity");
					finish();
					Intent intent = new Intent(ListActivity.this, SearchActivity.class);
					
					//intent.putExtra("token", "ListActivity");
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ListActivity");
				finish();
				Intent intent = new Intent(ListActivity.this, HelpActivity.class);
			//	intent.putExtra("token", "ListActivity");
				//ActivityContext.myList.add("FavActivity.class");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
			//	ActivityContext.myList.add("dance.class");
				ActivityContext.revealflag=false;
				String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
				Log.v("hello back class", act + ",");
				if (!act.equals("ListActivity")) {
					
					ActivityContext.myList.add("ListActivity");
				}
				//Toast.makeText(getApplicationContext(), "in"+ActivityContext.myList, 500).show();
				finish();
				Intent intent = new Intent(ListActivity.this, dance.class);
				//intent.putExtra("token", "ListActivity");
			//	ActivityContext.myList.add("dance.class");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ListActivity");
				finish();
				Intent intent = new Intent(ListActivity.this, MoreActivity.class);
				//intent.putExtra("token", "ListActivity");
			//	ActivityContext.myList.add("MoreActivity.class");
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("ListActivity");
				finish();
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				Intent intent = new Intent(ListActivity.this, FavActivity.class);
				//intent.putExtra("token", "ListActivity");
			//	ActivityContext.myList.add("FavActivity.class");
				startActivity(intent);
			
				//startActivity(new Intent(ListActivity.this, FavActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		req.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						ListActivity.this);

				// Setting Dialog Title
				alertDialog.setTitle("Message");

				// Setting Dialog Message
				alertDialog
						.setMessage("Are we missing quotes from your favorite 'Movie'? "
								+ "Or misssing one of your fav 'Movie Quotes'? Let us know! "
								+ "We're constantly updating our database. Send us an email and we'll add it.");

				
				alertDialog.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

//					
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
//								
								try {
									new emai_s().execute();
								} catch (Exception e) {
									Toast.makeText(getApplicationContext(), "No Email Support on Your device.",500).show();
								}
							
								
							}

							private void copyFile(InputStream in,
									OutputStream out) throws IOException {
								   byte[] buffer = new byte[1024];
								   int read;
								   while ((read = in.read(buffer)) != -1) {
									out.write(buffer, 0, read);
								  }
							}
					});

				// Setting Negative "NO" Button

				alertDialog.setNegativeButton("No Thanks",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								dialog.cancel();
							}
						});

				// Showing Alert Message
				alertDialog.show();

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
//				finish();
//				startActivity(new Intent(ListActivity.this, ListActivity.class));
//				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				 String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
				 Log.v("hello back class", act+",");
				if(act.equals("UIActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(ListActivity.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
//				else if(act.equals("ListActivity")){
//					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//					finish();
//					    Intent intent = new Intent(ListActivity.this, ListActivity.class);
//						//intent.putExtra("token",act);
//						startActivity(intent);
//						
//						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//					
//					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//					
//				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(ListActivity.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(ListActivity.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(ListActivity.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(ListActivity.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity2")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
					    Intent intent = new Intent(ListActivity.this, Info_reveal.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, searchUgrade.class);
					    intent.putExtra("mode", searchUgrade.searchmode);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				 
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("HelpActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, HelpActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ListActivity.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
			

			}
		});
		// back=(Button)findViewById(R.id.button1);
		loadlist();

	}

	private void loadlist() {
     
		try {
			listView = (ListView) findViewById(R.id.list_twoligne_itineraire);

			adapter = new MyListAdapterTwoLine(ListActivity.this,getApplicationContext());
			listView.setAdapter(adapter);
		} catch (Exception e) {
			Log.v("load list in list act", e.toString());
		}

	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		listView.setAdapter(null);
		adapter=null;
		System.gc();
		Runtime rt=Runtime.getRuntime();
		 long free = rt.freeMemory();
	        long total = rt.totalMemory();
	      long  used = total - free;
	        Log.v("free time",free/(1024*1024)+"");
	        Log.v("total",total/(1024*1024)+"");
	        Log.v("used",used/(1024*1024)+"");
	       
	        	super.onDestroy();
	        	

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
				    Intent intent = new Intent(ListActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("ListActivity")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(ListActivity.this, ListActivity.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity2")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, Info_reveal.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, searchUgrade.class);
				    intent.putExtra("mode", searchUgrade.searchmode);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
		
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ListActivity.this, randomquote.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			} 

	         return;
	     }   
	
	  public class emai_s extends AsyncTask<Void, Void, String> {

		  private Drawable drawable;
		  private Uri screenshotUri;
		  ProgressDialog mDialog = null;
		private Activity activity=ListActivity.this;
		String path;
		@Override
		protected void onPreExecute() {
			
							//bar.setVisibility(View.VISIBLE);
							 mDialog = new ProgressDialog(activity);
				             mDialog.setMessage("Please Wait...");
				             mDialog.show();
						}
			//
		  @Override
		  protected String doInBackground(Void... params) {
		   try {
			   
		    InputStream is;
		    try {
			     is = (InputStream) new URL(
					       "http://54.243.28.185/sendgrid/images/MovieQuotes_512.png")
					       .getContent();

					     // storing image from stream
					     drawable = Drawable.createFromStream(is, "srcName");
					     is.close();
					      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
					     // Store image in Devise database to send image to mail
					     path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "title",null);
					     screenshotUri = Uri.parse(path);

					    } catch (MalformedURLException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }

		   

		   } catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } 
		   return null;
		  }

		  @Override
		  protected void onPostExecute(String result) {
		   // Toast.makeText(activity, "hello", 5000).show();
		  // pd.dismiss();
		 //  dialog1.cancel();
			//  bar.setVisibility(View.INVISIBLE);
			 
			  setProgressBarIndeterminateVisibility(false);
			  mDialog.hide();
		   Handler handler = new Handler();
		   handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {

		     final Intent emailIntent1 = new Intent(
		     android.content.Intent.ACTION_SEND);
		     emailIntent1.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@gazilliontogo.com" });
		     emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		     emailIntent1.putExtra(Intent.EXTRA_STREAM, screenshotUri);
		     emailIntent1.putExtra(Intent.EXTRA_SUBJECT,
		       "Gazillion Movie Quote Request!");
		     // emailIntent1.putExtra(Intent.EXTRA_TEXT,"Your friend has invited you to start racing in Word Derby.Free for Android, iPhone and iPad. Your friend's Word Derby username is '"
		     // + data.username + "'. ");

		     emailIntent1.putExtra(Intent.EXTRA_TEXT,
		       Html.fromHtml("https://play.google.com/store/apps/details?id=awesome.app.moviequotes"));
		     emailIntent1.setType("image/rfc822");

		     // emailIntent1.setType("image/png");
		     activity.startActivity(Intent.createChooser(emailIntent1,
		       "Send email using"));
		     overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

		    }
		   }, 300);

		  }

		 }

}
