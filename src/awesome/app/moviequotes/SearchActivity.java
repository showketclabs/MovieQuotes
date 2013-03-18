// Project Name: - Movie Quotes
// Purpose of file: - Controls search activity
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


public class SearchActivity extends Activity  {
	Button shake, help, search, more, list, fav, back, searchquote,
			searchmovie, searchyear;
FrameLayout fl;
	private ShakeListener mShaker;
	int temp = 0;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(SearchActivity.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.search);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(SearchActivity.this,fl,800,480);
		//shaker = new Shaker(this, 2.25d, 500, this);
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
		    		  			
		    		  			startActivity(new Intent(SearchActivity.this, dance.class));
		    		  		}
		      }
		    });
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		temp = 0;
		searchquote = (Button) findViewById(R.id.searchbyquote);
		searchmovie = (Button) findViewById(R.id.searchbymovie);
		searchyear = (Button) findViewById(R.id.searchbyyear);
		back = (Button) findViewById(R.id.btnback);
		search.setEnabled(false);
		search.setBackgroundResource(R.drawable.searchp);
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
							SearchActivity.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog.setMessage("About app");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();

									startActivity(new Intent(
											SearchActivity.this,
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
					startActivity(new Intent(SearchActivity.this,
							SearchActivity.class));
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(SearchActivity.this,
						HelpActivity.class));

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(SearchActivity.this,
						dance.class));

			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(SearchActivity.this,
						MoreActivity.class));

			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(SearchActivity.this, FavActivity.class));

			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				startActivity(new Intent(SearchActivity.this,
						ListActivity.class));

			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SearchActivity.this, UIActivity.class));
				finish();

			}
		});
		searchquote.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

				Intent mov = new Intent(SearchActivity.this, searchUgrade.class);
				//mov.addFlags(mov.FLAG_ACTIVITY_CLEAR_TOP);
				mov.putExtra("mode", "quoteseach");
				startActivity(mov);
			}
		});
		searchmovie.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();

				Intent mov = new Intent(SearchActivity.this, searchUgrade.class);
				//mov.addFlags(mov.FLAG_ACTIVITY_CLEAR_TOP);
				mov.putExtra("mode", "moviesearch");
				startActivity(mov);

			}
		});
		searchyear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

				Intent mov = new Intent(SearchActivity.this, searchUgrade.class);
				//mov.addFlags(mov.FLAG_ACTIVITY_CLEAR_TOP);
				mov.putExtra("mode", "yearsearch");
				startActivity(mov);

			}
		});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
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
