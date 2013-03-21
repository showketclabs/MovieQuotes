// Project Name: - Movie Quotes
// Purpose of file: - Default screen controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.flurry.android.FlurryAgent;

public class UIActivity extends Activity {
	Button shake, help, search, more, list, fav;
	// AccelerometerListener _accelerometerListener;
	public Intent i;
	//Shaker shaker;
	SharedPreferences prouser;
	int temp = 0;
	private ShakeListener mShaker;
	//flurry reg key
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(UIActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.main);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(UIActivity.this,fl,800,480);
		//shaker = new Shaker(UIActivity.this, 2.25d, 500, UIActivity.this);
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
					startActivity(new Intent(UIActivity.this, dance.class));
		      }}
		    });
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		temp = 0;
		// SavePreferences("prouser", "");
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
							UIActivity.this);

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

									startActivity(new Intent(UIActivity.this,
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
					startActivity(new Intent(UIActivity.this,
							SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(UIActivity.this, HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(UIActivity.this, dance.class));
				

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(UIActivity.this, MoreActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				// startActivity(new Intent(UIActivity.this,FavActivity.class));
				finish();
				startActivity(new Intent(UIActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				//finish();
				startActivity(new Intent(UIActivity.this, ListActivity.class));
				
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
			 finish();
			 

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

	// @Override
	// public void onDestroy() {
	// super.onDestroy();
	//
	// // shaker.close();
	// }
	//
//	public void shakingStarted() {
//		if (temp == 0)
//
//		{
//			temp = 1;
//			finish();
//		
//			startActivity(new Intent(UIActivity.this, dance.class));
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
