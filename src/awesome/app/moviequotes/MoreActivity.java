// Project Name: - Movie Quotes
// Purpose of file: - Controls more activity
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
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import awesome.app.moviequotes.SplashScreen.myAsyncTask1;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdView;


public class MoreActivity extends Activity {
	Button shake, help, search, more, list, fav, back;
	
	//Shaker shaker;
	int temp = 0;
	WebView mWebView;
	AdView adview;
	private ShakeListener mShaker;
	  String URL="http://widgets.itunes.apple.com/appstore.html?wtype=11&app_id=null&country=us&partnerId=30&affiliate_id=http%3A//click.linksynergy.com/fs-bin/stat%3Fid%3DPk5SplnlMSY%26offerid%3D146261%26type%3D3%26subid%3D0%26tmpid%3D1826%26RD_PARM1%3D&cul=FFFFFF&cur=FFFFFF&cll=FFFFFF&clr=FFFFFF&wh=382&ww=320&t=More%20Great%20Movie%20Apps&d=Available%20in%20the%20App%20Store&pl=307906541,342792525,307840047,381823315,334774848,386602645";

	  ProgressBar loadingProgressBar,loadingTitle;
	  String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	  ProgressDialog mDialog;
	private FrameLayout fl;
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
		 mDialog = new ProgressDialog(MoreActivity.this);
         mDialog.setMessage("Loading...");
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
			adview= (AdView)findViewById(R.id.ad);
	     	adview.setVisibility(8);
		}
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(MoreActivity.this,fl,800,480);
		try{
			AdView adview = (AdView)findViewById(R.id.ad);
			AdRequest re = new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);}
			catch(Exception e){
				Log.v("add",e.toString());
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
		    		  			
		    		  			startActivity(new Intent(MoreActivity.this, dance.class));
		    		  		}
		      }
		    });
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
									finish();

									startActivity(new Intent(MoreActivity.this,
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
					startActivity(new Intent(MoreActivity.this,
							SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(MoreActivity.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(MoreActivity.this, dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(MoreActivity.this, MoreActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(MoreActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(MoreActivity.this, ListActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(MoreActivity.this, UIActivity.class));
				finish();

			}
		});
		Handler mHandler = new Handler();

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// frameAnimation.stop();
			//	mDialog.hide();
				mDialog.hide();
				

			}
		}, 2000);
		try {
			 mDialog.show();
			 mDialog.setCanceledOnTouchOutside(false);
          
		} catch (Exception e) {
			Log.v("responce", e.toString());
		}
		
		   mWebView = (WebView) findViewById(R.id.webview);
		   mWebView.getSettings().setJavaScriptEnabled(true);
		   mWebView.loadUrl(URL);
		  
		   mWebView.setWebViewClient(new MyWebViewClient());
		   mWebView.setHorizontalScrollBarEnabled(false);

		   mWebView.setVerticalScrollBarEnabled(true);
		  
		   
		   mWebView.setWebChromeClient(new WebChromeClient() {

		  

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

	 @Override
	   public boolean onKeyDown(int keyCode, KeyEvent event) {
	   
	   if(keyCode == KeyEvent.KEYCODE_BACK){
	   finish();
	   }
	   return super.onKeyDown(keyCode, event);
	   }

	   private class MyWebViewClient extends WebViewClient {

	 
	 @Override
	 public boolean shouldOverrideUrlLoading(WebView view, String url) {

	 view.loadUrl(url);
	 return true;
	 }
	 }
}
