
// Project Name: - Movie Quotes
// Purpose of file: - Controls more activity
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;




import android.app.ActionBar.LayoutParams;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;


public class MoreActivity extends Activity {
	Button shake, help, search, more, list, fav, back;
	
	//Shaker shaker;
	int temp = 0;
	//WebView mWebView;
	AdView adview;
	
	 ShakeListener mShaker;
	  String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	 // ProgressDialog mDialog;
	private FrameLayout fl;

	private RelativeLayout layout;
		@Override
		protected void onStart() {
	        super.onStart();
	        FlurryAgent.onStartSession(MoreActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.more_activity);
		// mDialog = new ProgressDialog(MoreActivity.this);
		adview = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4"); 
		 layout = (RelativeLayout)findViewById(R.id.add);  
        // mDialog.setMessage("Loading...");
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			try{
				       
			        
			    layout.addView(adview);
			    AdRequest request = new AdRequest();
			    request.setTesting(false);
			    adview.loadAd(request);
			    }
				catch(Exception e){
					Log.v("add",e.toString());
				}
		} else {
			
	     	adview.setVisibility(8);
	     	layout.setVisibility(8);
		}
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(MoreActivity.this,fl,1184,720);
		//caller=getIntent().getExtras().getString("token");
	
		temp = 0;
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
		    					Intent intent = new Intent(MoreActivity.this, dance.class);
		    				//	intent.putExtra("token", "MoreActivity");
		    					startActivity(intent);
		    		  			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    		  		}
		      }
		    });
		    AppRater.app_launched(MoreActivity.this);
		// shaker = new Shaker(this, 2.25d, 500, this);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		more.setEnabled(false);
		more.setBackgroundResource(R.drawable.morep);
		

		
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
							MoreActivity.this);

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
									ActivityContext.myList.add("MoreActivity");
									finish();
									Intent intent = new Intent(MoreActivity.this, UpgradeActivity.class);
									
									
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
					ActivityContext.myList.add("MoreActivity");
					finish();
					Intent intent = new Intent(MoreActivity.this, SearchActivity.class);
					
					
					startActivity(intent);
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("MoreActivity");
				finish();
				Intent intent = new Intent(MoreActivity.this, HelpActivity.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				Intent intent = new Intent(MoreActivity.this, dance.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("MoreActivity");
				finish();
				Intent intent = new Intent(MoreActivity.this, FavActivity.class);
			
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("MoreActivity");
				finish();
				Intent intent = new Intent(MoreActivity.this, ListActivity.class);
								
				startActivity(intent);
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
						    Intent intent = new Intent(MoreActivity.this, UIActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ListActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, ListActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("SearchActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, SearchActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("FavActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, FavActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
				
					else if(act.equals("InfoActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, InfoActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("UpgradeSearch")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, searchUgrade.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("UpgradeActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, UpgradeActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("RevealActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, RevealInfo.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ShareActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, ShareActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ShareActivity1")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, ShareActivity1.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("HelpActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, HelpActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
						
					}
					else if(act.equals("randomquotes")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(MoreActivity.this, randomquote.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
				
						
					}


			}
		});
		Handler mHandler = new Handler();

		mHandler.post(new Runnable() {
			@Override
			public void run() {
		
				
			
				

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
				    Intent intent = new Intent(MoreActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("MoreActivity")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(MoreActivity.this, MoreActivity.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(MoreActivity.this, randomquote.class);
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

	//

	

//	public void shakingStarted() {
//		if (temp == 0)
//
//		{
//			temp = 1;
//			finish();
//
//			startActivity(new Intent(MoreActivity.this, dance.class));
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
