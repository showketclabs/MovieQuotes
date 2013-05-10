// Project Name: - Movie Quotes
// Purpose of file: - Controls help activity.
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import clabs.androidscreenlibrary.AndroidScreenSize;

import com.flurry.android.FlurryAgent;
import com.revmob.RevMob;
import com.revmob.ads.banner.RevMobBanner;
public class HelpActivity extends Activity {
	Button shake, help, search, more, list, fav, back, justshake, scrolllist,u_ext,
			upgrade, justshare, rate;
	//Shaker shaker;
	int temp = 0;
	private RevMob revmob;
	ViewGroup view;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private ShakeListener mShaker;
	private FrameLayout fl;
	private ProgressDialog mDialog;
	
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(HelpActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.help_activity);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(HelpActivity.this,fl,1184,720);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		justshake = (Button) findViewById(R.id.button1);
		scrolllist = (Button) findViewById(R.id.button2);
		upgrade = (Button) findViewById(R.id.button3);
		justshare = (Button) findViewById(R.id.button4);
		rate = (Button) findViewById(R.id.button5);
		u_ext=(Button) findViewById(R.id.upgrade_ext);
		help.setEnabled(false);
		help.setBackgroundResource(R.drawable.info1press);
		revmob = RevMob.start(HelpActivity.this, "517a0db434a9464b16000031");
		view = (ViewGroup) findViewById(R.id.banner);
		 mDialog = new ProgressDialog(HelpActivity.this);
		
         mDialog.setMessage("Loading...");
		temp = 0;
		Log.i("stack..list Help.......",ActivityContext.myList+"");
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			try{
				
				 RevMobBanner banner = revmob.createBanner(HelpActivity.this);
				 
				 view.addView(banner);    
			           
			   
				}
				catch(Exception e){
					Log.v("add",e.toString());
				}
		} else {
		
			view.setVisibility(8);
			
			u_ext.setEnabled(false);
			u_ext.setVisibility(8);
			
		}
		  AppRater.app_launched(HelpActivity.this);
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
		    		  			 ActivityContext.revealflag=false;
		 						String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
		 						Log.v("hello back class", act + ",");
		 						if (!act.equals("HelpActivity")) {
		 							
		 							ActivityContext.myList.add("HelpActivity");
		 						}
		    					Intent intent = new Intent(HelpActivity.this, dance.class);
		    				//	intent.putExtra("token", "HelpActivity");
		    					startActivity(intent);
		    		  			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    		  			finish();
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
								HelpActivity.this);

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
										ActivityContext.myList.add("HelpActivity");
										
										Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
										//intent.putExtra("token", "HelpActivity");
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
						ActivityContext.myList.add("HelpActivity");
						
						Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
					//	intent.putExtra("token", "HelpActivity");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
						finish();
					}
				}
			});
			u_ext.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// Toast.makeText(getApplicationContext(), "in", 500).show();
					ActivityContext.myList.add("HelpActivity");
					
					startActivity(new Intent(HelpActivity.this, UpgradeActivity.class));
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					finish();

				}
			});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
//				finish();
//				startActivity(new Intent(HelpActivity.this, HelpActivity.class));
//				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				 ActivityContext.revealflag=false;
				 String act = ActivityContext.myList.get(ActivityContext.myList.size() - 1);
					Log.v("hello back class", act + ",");
					if (!act.equals("HelpActivity")) {
						
						ActivityContext.myList.add("HelpActivity");
					}
			
				Intent intent = new Intent(HelpActivity.this, dance.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, FavActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, ListActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
         Log.v("actihhh:",ActivityContext.myList+"");
				
				 String act=ActivityContext.myList.get(ActivityContext.myList.size()-1);
//				
				 Log.v("hello back class", act+",");
				if(act.equals("UIActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity2")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, Info_reveal.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, searchUgrade.class);
					    intent.putExtra("mode", searchUgrade.searchmode);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					
					    Intent intent = new Intent(HelpActivity.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						finish();
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
					

			}
		});

		justshake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, UIActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		scrolllist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, ListActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences sharedPreferences = getSharedPreferences(
						"MY", MODE_PRIVATE);
				String strSavedMem1 = sharedPreferences.getString("MEM2",
						"");

				Log.v("strSavedMem1", strSavedMem1+"");
				if (strSavedMem1 =="") {
					
					
					ActivityContext.myList.add("HelpActivity");
					
					Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
					
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					finish();
					
					
				} else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							HelpActivity.this);
					// Setting Dialog Title
					alertDialog.setTitle("Message");
					// Setting Dialog Message
					alertDialog
							.setMessage("You have already purchased app.");
					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								private Global global;

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							});

					alertDialog.show();

				}
				
				
				
				
				
			}
		});
		justshare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				
				Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
			
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
				finish();
			}
		});
		rate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AppRater.showRateDialog(HelpActivity.this, null);

		}
		});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//shaker.close();
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
				
				    Intent intent = new Intent(HelpActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
			
				    Intent intent = new Intent(HelpActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity2")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, Info_reveal.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, searchUgrade.class);
				    intent.putExtra("mode", searchUgrade.searchmode);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					finish();
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				
				    Intent intent = new Intent(HelpActivity.this, randomquote.class);
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

//	public void shakingStarted() {
//		if (temp == 0)
//
//		{
//			temp = 1;
//			finish();
//		
//			startActivity(new Intent(HelpActivity.this, dance.class));
//		}
//		// Log.d("ShakerDemo", "Shaking started!");
//		// transcript.setText(transcript.getText().toString()+"Shaking started\n");
//		// scroll.fullScroll(View.FOCUS_DOWN);
//	}
//
//	public void shakingStopped() {
//		// startActivity(new Intent(UIActivity.this,ShakeActivity.class));
//		// Log.d("ShakerDemo", "Shaking stopped!");
//		// transcript.setText(transcript.getText().toString()+"Shaking stopped\n");
//		// scroll.fullScroll(View.FOCUS_DOWN);
//	}
}
//

