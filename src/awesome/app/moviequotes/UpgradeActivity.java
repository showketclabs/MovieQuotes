// Project Name: - Movie Quotes
// Purpose of file: - upgrade activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.flurry.android.FlurryAgent;


public class UpgradeActivity extends Activity {
	Button shake, help, purchase, back, upgrade;
	ListView l;
	ArrayList<String> m = new ArrayList<String>();
	ArrayList<String> q = new ArrayList<String>();
	ArrayList<String> y = new ArrayList<String>();
	String mov = "";
	String quote = "";
	String year = "";
	//Shaker shaker;
	private ShakeListener mShaker;
	int temp = 0;
	String pro = "";
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(UpgradeActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.upgrade_activity);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(UpgradeActivity.this,fl,800,480);
	//	shaker = new Shaker(this, 2.25d, 500, this);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);

		purchase = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		upgrade = (Button) findViewById(R.id.button2);
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
		    		  			
		    		  			startActivity(new Intent(UpgradeActivity.this, dance.class));
		    		  		}
		      }
		    });
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(UpgradeActivity.this,
						HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(UpgradeActivity.this,
						dance.class));

			}
		});

		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//startActivity(new Intent(UpgradeActivity.this,com.blundell.test.AppMainTest.class));
			//	finish();
				int count = 1;
				if (count == 1) {
					SavePreferences("MEM2", "pro");
					LoadPreferences();
					startActivity(new Intent(UpgradeActivity.this,
							SearchActivity.class));
					finish();
				} else {

					// code to return without purchase
				}
			}

		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(UpgradeActivity.this, UIActivity.class));
				finish();

			}
		});

		purchase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(UpgradeActivity.this, UIActivity.class));
				finish();

			}
		});

		// loadfavlist

	}

	// savving preference
	private void SavePreferences(String key, String value) {
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	private void LoadPreferences() {
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(MainActivity.this, strSavedMem1,
		// Toast.LENGTH_LONG).show();
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


}
