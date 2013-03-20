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
import com.google.ads.AdView;
public class HelpActivity extends Activity {
	Button shake, help, search, more, list, fav, back, justshake, scrolllist,
			upgrade, justshare, rate;
	//Shaker shaker;
	int temp = 0;
	AdView adview;
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
		new AndroidScreenSize(HelpActivity.this,fl,800,480);
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
		help.setEnabled(false);
		help.setBackgroundResource(R.drawable.helpp);
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
				adview = (AdView)findViewById(R.id.ad);
				AdRequest re = new AdRequest();
				re.setTesting(true);
				adview.loadAd(re);}
				catch(Exception e){
					Log.v("add",e.toString());
				}
		} else {
		 adview = (AdView)findViewById(R.id.ad);
			adview.setVisibility(8);
		}
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
		    		  			
		    		  			startActivity(new Intent(HelpActivity.this, dance.class));
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
										finish();

										startActivity(new Intent(HelpActivity.this,
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
						startActivity(new Intent(HelpActivity.this,
								SearchActivity.class));
					}
				}
			});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(HelpActivity.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(HelpActivity.this, dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(HelpActivity.this, MoreActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(HelpActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(HelpActivity.this, ListActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(HelpActivity.this, UIActivity.class));
				finish();

			}
		});

		justshake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(HelpActivity.this, UIActivity.class));

			}
		});
		scrolllist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(HelpActivity.this, ListActivity.class));

			}
		});
		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(HelpActivity.this,
						UpgradeActivity.class));

			}
		});
		justshare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(HelpActivity.this,
						ShareActivity1.class));

			}
		});
		rate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// mListe.get(position).setCb1(true);
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						HelpActivity.this);

				// Setting Dialog Title
				alertDialog.setTitle("Rate This App.");

				// Setting Dialog Message
				alertDialog.setMessage("About app");

				// Setting Icon to Dialog
				// alertDialog.setIcon(R.drawable.delete);

				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton("Rate This App.",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
                       try {
                    	   Handler mHandler = new Handler();

                   		  mHandler.postDelayed(new Runnable() {
                   			@Override
                   			public void run() {
                   				
                   				mDialog.hide();
                   				

                   			}
                   		}, 3000);
                   	
                   			 mDialog.show();
                   			 mDialog.setCanceledOnTouchOutside(false);
						    new AppRater();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
								" problem in Rate this app", Toast.LENGTH_SHORT)
								.show();
					}
								// Write your code here to invoke NO event
								
							}
						});

				// Setting Negative "NO" Button

				alertDialog.setNeutralButton("Remind Me Later.",
						new DialogInterface.OnClickListener() {
							private Global global;

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to invoke NO event
								Toast.makeText(getApplicationContext(),
										"Neutral", Toast.LENGTH_SHORT).show();
							}
						});
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

