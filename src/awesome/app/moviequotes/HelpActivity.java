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
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
public class HelpActivity extends Activity {
	Button shake, help, search, more, list, fav, back, justshake, scrolllist,u_ext,
			upgrade, justshare, rate;
	//Shaker shaker;
	int temp = 0;
	AdView adview;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private ShakeListener mShaker;
	private FrameLayout fl;
	private ProgressDialog mDialog;
	RelativeLayout addlay;
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
		adview = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4");  
		 addlay = (RelativeLayout)findViewById(R.id.add);
		 mDialog = new ProgressDialog(HelpActivity.this);
		
         mDialog.setMessage("Loading...");
		temp = 0;
		
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			try{
				      
			           
			    addlay.addView(adview);
			    AdRequest request = new AdRequest();
			    request.setTesting(false);
			    adview.loadAd(request);
				}
				catch(Exception e){
					Log.v("add",e.toString());
				}
		} else {
		
			adview.setVisibility(8);
			addlay.setVisibility(8);
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
//		        vibe.vibrate(100);
//		        new AlertDialog.Builder(UIActivity.this)
//		          .setPositiveButton(android.R.string.ok, null)
//		          .setMessage("Shooken!")
//		          .show();
		    	  if (temp == 0)
		    		  
		    		  		{
		    		  			temp = 1;
		    		  			finish();
		    					Intent intent = new Intent(HelpActivity.this, dance.class);
		    				//	intent.putExtra("token", "HelpActivity");
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
										finish();
										Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
										//intent.putExtra("token", "HelpActivity");
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
						ActivityContext.myList.add("HelpActivity");
						finish();
						Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
					//	intent.putExtra("token", "HelpActivity");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					}
				}
			});
			u_ext.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// Toast.makeText(getApplicationContext(), "in", 500).show();
					ActivityContext.myList.add("HelpActivity");
					finish();
					startActivity(new Intent(HelpActivity.this, UpgradeActivity.class));
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

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
				finish();
				Intent intent = new Intent(HelpActivity.this, dance.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, FavActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, ListActivity.class);
				//intent.putExtra("token", "HelpActivity");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

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
					finish();
					    Intent intent = new Intent(HelpActivity.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, searchUgrade.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, ShareActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(HelpActivity.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
					

			}
		});

		justshake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, UIActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		scrolllist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, ListActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		justshare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ActivityContext.myList.add("HelpActivity");
				finish();
				Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
			
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		rate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AppRater.showRateDialog(HelpActivity.this, null);
//
//				// mListe.get(position).setCb1(true);
//				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//						HelpActivity.this);
//
//				// Setting Dialog Title
//				alertDialog.setTitle("Rate This App");
//
//				// Setting Dialog Message
//				alertDialog.setMessage("We need your help! Please take a moment to rate this app. It will help us continue to make great apps for you. If you love this app, please give us lots of stars");
//
//				// Setting Icon to Dialog
//				// alertDialog.setIcon(R.drawable.delete);
//
//				// Setting Positive "Yes" Button
//				alertDialog.setPositiveButton("Rate This App",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//                       try {
//                    	   Handler mHandler = new Handler();
//
//                   		  mHandler.postDelayed(new Runnable() {
//                   			@Override
//                   			public void run() {
//                   				
//                   				mDialog.hide();
//                   				
//
//                   			}
//                   		}, 3000);
//                   	
//                   			 mDialog.show();
//                   			 mDialog.setCanceledOnTouchOutside(false);
//						    new AppRater();
//					} catch (Exception e) {
//						Toast.makeText(getApplicationContext(),
//								" problem in Rate this app", Toast.LENGTH_SHORT)
//								.show();
//					}
//								// Write your code here to invoke NO event
//								
//							}
//						});
//
//				// Setting Negative "NO" Button
//
//				alertDialog.setNeutralButton("Remind Me Later",
//						new DialogInterface.OnClickListener() {
//							private Global global;
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								  try {
//									new AppRater().app_launched(getApplicationContext());
//								} catch (Exception e) {
//									Toast.makeText(getApplicationContext(),
//											e.toString(), Toast.LENGTH_SHORT).show();
//								}
//								// Write your code here to invoke NO event
//								Toast.makeText(getApplicationContext(),
//										"Neutral", Toast.LENGTH_SHORT).show();
//							}
//						});
//				alertDialog.setNegativeButton("No Thanks",
//						new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//
//								dialog.cancel();
//							}
//						});
//
//				// Showing Alert Message
//				alertDialog.show();
//
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
				finish();
				    Intent intent = new Intent(HelpActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(HelpActivity.this, randomquote.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
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

