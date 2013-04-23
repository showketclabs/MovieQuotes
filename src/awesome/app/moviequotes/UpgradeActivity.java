// Project Name: - Movie Quotes
// Purpose of file: - upgrade activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;


import awesome.app.moviequotes.Db;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;

import com.example.android.trivialdrivesample.util.Purchase;

import com.example.android.trivialdrivesample.util.IabHelper;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class UpgradeActivity extends Activity {
	Button shake, help, purchase, back, upgrade;
	ListView l;
	ArrayList<String> m = new ArrayList<String>();
	ArrayList<String> q = new ArrayList<String>();
	ArrayList<String> y = new ArrayList<String>();
	String mov = "";
	String quote = "";
	String year = "";
	// Shaker shaker;
	private ShakeListener mShaker;
	int temp = 0;
	String pro = "";

	String MY_KEY = "MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;

	@Override
	protected void onStart() {
		super.onStart();
		FlurryAgent.onStartSession(UpgradeActivity.this, MY_KEY);// MY_KEY is
																	// key given
																	// by them.
		FlurryAgent.onEvent("Application started");
	}

	@Override
	protected void onStop() {
		super.onStop();
		FlurryAgent.onEndSession(this);
	}

	String[] arr1;

	// Does the user have the premium upgrade?
	boolean mIsPremium = false;

	// inapp_product id
	static final String SKU_PREMIUM = "remove_ads";

	// (arbitrary) request code for the purchase flow
	static final int RC_REQUEST = 10001;

	// The helper object
	IabHelper mHelper;

	// private static final int DIALOG_BILLING_NOT_SUPPORTED_ID = 2;
	//
	// private String mSku;
	//
	// private AbstractBillingObserver mBillingObserver;
	//
	//
	// public void onBillingChecked(boolean supported) {
	// if (supported) {
	//
	// // mBuyButton.setEnabled(true);
	// } else {
	// showDialog(DIALOG_BILLING_NOT_SUPPORTED_ID);
	// }
	// }
	private AdView adview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upgrade_activity);
		fl = (FrameLayout) findViewById(R.id.rv);
		new AndroidScreenSize(UpgradeActivity.this, fl, 1184, 720);
		// shaker = new Shaker(this, 2.25d, 500, this);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);

		purchase = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		upgrade = (Button) findViewById(R.id.button2);
		temp = 0;

		// creatng data base for in app
		Db ob = new Db(UpgradeActivity.this);
		ob.open();
		int i = ob.get_inapp_row();
		if (i == 0) {
			ob.CreateEntry_INAPP(0);
		}
		ob.close();

		adview = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4");
		AppRater.app_launched(UpgradeActivity.this);
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			try {

				RelativeLayout layout = (RelativeLayout) findViewById(R.id.add);
				layout.addView(adview);
				AdRequest request = new AdRequest();
				request.setTesting(false);
				adview.loadAd(request);
			} catch (Exception e) {

			}
		} else {

			adview.setVisibility(8);
		}

		final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		mShaker = new ShakeListener(this);
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
			@Override
			public void onShake() {
				// vibe.vibrate(100);
				// new AlertDialog.Builder(UIActivity.this)
				// .setPositiveButton(android.R.string.ok, null)
				// .setMessage("Shooken!")
				// .show();
				if (temp == 0)

				{
					temp = 1;

					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							dance.class);

					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeActivity");
				finish();
				Intent intent = new Intent(UpgradeActivity.this,
						HelpActivity.class);

				startActivity(intent);

				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				
				finish();
				Intent intent = new Intent(UpgradeActivity.this, dance.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
			}
		});

		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// startActivity(new
				// Intent(UpgradeActivity.this,com.blundell.test.AppMainTest.class));
				// finish();

				if (haveNetworkConnection()) {

					SharedPreferences sharedPreferences = getSharedPreferences(
							"MY", MODE_PRIVATE);
					String strSavedMem1 = sharedPreferences.getString("MEM2",
							"");

					// testing purpose
					//SavePreferences("MEM2", "pro");
					// Toast.makeText(UIActivity.this, strSavedMem1,
					// Toast.LENGTH_LONG)
					// .show();
					if (strSavedMem1 == "") {
						Log.v("billl", "billing");
						try {
							String payload = "";

							mHelper.launchPurchaseFlow(UpgradeActivity.this,
									SKU_PREMIUM, RC_REQUEST,
									mPurchaseFinishedListener, payload);
							// upgrade code here on click
							Log.d("TAG",
									"Upgrade button clicked; launching purchase flow for upgrade.");
							
								
							

							// BillingController.requestPurchase(getApplicationContext(),
							// "remove_ads");
						} catch (Exception e) {
							Log.v("billl", "billing" + e.toString());
						}
					} else {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								UpgradeActivity.this);
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
				} else {

					Toast.makeText(getApplicationContext(),
							"Check your internet connection.", 500).show();
				}
			}

		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String act = ActivityContext.myList.get(ActivityContext.myList
						.size() - 1);
				Log.v("hello back class", act + ",");
				if (act.equals("UIActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							UIActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ListActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							ListActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("SearchActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							SearchActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("FavActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							FavActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("MoreActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							MoreActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("InfoActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							InfoActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("UpgradeSearch")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							searchUgrade.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
				
				else if (act.equals("RevealActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							RevealInfo.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ShareActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							ShareActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ShareActivity1")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							ShareActivity1.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("HelpActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							HelpActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}

				else if (act.equals("randomquotes")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
					finish();
					Intent intent = new Intent(UpgradeActivity.this,
							randomquote.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);

					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}

			}
		});

		purchase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (haveNetworkConnection()) {

					SharedPreferences sharedPreferences = getSharedPreferences(
							"MY", MODE_PRIVATE);
					String strSavedMem1 = sharedPreferences.getString("MEM2",
							"");
					// Toast.makeText(UIActivity.this, strSavedMem1,
					// Toast.LENGTH_LONG)
					// .show();

					if (strSavedMem1 == "") {
						Log.v("billl", "billing");
						try {
							String payload = "";

							mHelper.launchPurchaseFlow(UpgradeActivity.this,
									SKU_PREMIUM, RC_REQUEST,
									mPurchaseFinishedListener, payload);
							// upgrade code here on click
							Log.d("TAG",
									"Upgrade button clicked; launching purchase flow for upgrade.");
						} catch (Exception e) {
							Log.v("billl", "billing" + e.toString());
						}
					} else {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								UpgradeActivity.this);
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
				} else {

					Toast.makeText(getApplicationContext(),
							"Check your internet connection.", 500).show();
				}

			}
		});

		
		/*
		 * base64EncodedPublicKey should be YOUR APPLICATION'S PUBLIC KEY (that
		 * you got from the Google Play developer console). This is not your
		 * developer public key, it's the *app-specific* public key.
		 * 
		 * Instead of just storing the entire literal string here embedded in
		 * the program, construct the key at runtime from pieces or use bit
		 * manipulation (for example, XOR with some other string) to hide the
		 * actual key. The key itself is not secret information, but we don't
		 * want to make it easy for an attacker to replace the public key with
		 * one of their own and then fake messages from the server.
		 */
		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmz9CPYDBbFDsVAS8hZ5cAEIcim7jL/MT2g5Jvbl6h/Cq3VufonISXtwJieHKlI16PCH2HMoMwqSHz3gQK/l0J6xDA9uGnPBt3+VF9iQD0N1pI0ohfhWaMJLC/SfwKNF0gFGHDJncFjTIrWlxYIT/K3sExykOaZX/+amu7gZhibdZ6aAPBEyOoi9dDFae6CFw6grBI/hc/Kgomm1QHnGf2QE6zcrTIVPFop25hMDGiPO8g+See8UINPkpvA/czSZIfsvlJO5APrfbRETfqiv8D8tlZ+1fpRRXI/3SqmQG0FHG7ZmobHZ7mljpq0JHVEN/c/a3Hd7WxlhX1TAscytoQIDAQAB";

		// Some sanity checks to see if the developer (that's you!) really
		// followed the
		// instructions to run this sample (don't put these checks on your app!)
		if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
			throw new RuntimeException(
					"Please put your app's public key in MainActivity.java. See README.");
		}
		if (getPackageName().startsWith("com.example")) {
			throw new RuntimeException(
					"Please change the sample's package name! See README.");
		}

		// Create the helper, passing it our context and the public key to
		// verify signatures with
		Log.d("TAG", "Creating IAB helper.");
		mHelper = new IabHelper(this, base64EncodedPublicKey);

		// enable debug logging (for a production application, you should set
		// this to false).
		mHelper.enableDebugLogging(true);

		// Start setup. This is asynchronous and the specified listener
		// will be called once setup completes.
		Log.d("TAG", "Starting setup.");
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				Log.d("TAG", "Setup finished.");

				if (!result.isSuccess()) {
					// Oh noes, there was a problem.
					complain("Problem setting up in-app billing: " + result);
					return;
				}

				// Hooray, IAB is fully set up. Now, let's get an inventory of
				// stuff we own.
				Log.d("TAG", "Setup successful. Querying inventory.");
				mHelper.queryInventoryAsync(mGotInventoryListener);
			}
		});

	}

	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		public void onQueryInventoryFinished(IabResult result,
				Inventory inventory) {
			Log.d("TAG", "Query inventory finished.");
			if (result.isFailure()) {
				complain("Failed to query inventory: " + result);
				return;
			}

			Log.d("TAG", "Query inventory was successful.");

			/*
			 * Check for items we own. Notice that for each purchase, we check
			 * the developer payload to see if it's correct! See
			 * verifyDeveloperPayload().
			 */

			// Do we have the premium upgrade?
			Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
			mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
			Log.d("TAG", "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
			updateUi();
			// setWaitScreen(false);
			Log.d("TAG", "Initial inventory query finished; enabling main UI.");
		}
	};

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
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	       Log.d("TAG", "onActivityResult(" + requestCode + "," + resultCode + "," + data);

	       // Pass on the activity result to the helper for handling
	       if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
	           // not handled, so handle it ourselves (here's where you'd
	           // perform any handling of activity results not related to in-app
	           // billing...
	           super.onActivityResult(requestCode, resultCode, data);
	       }
	       else {
	           Log.d("TAG", "onActivityResult handled by IABUtil.");
	       }
	   }
	   
	   /** Verifies the developer payload of a purchase. */
	   boolean verifyDeveloperPayload(Purchase p) {
	       String payload = p.getDeveloperPayload();
	       
	       /*
	        * TODO: verify that the developer payload of the purchase is correct. It will be
	        * the same one that you sent when initiating the purchase.
	        * 
	        * WARNING: Locally generating a random string when starting a purchase and 
	        * verifying it here might seem like a good approach, but this will fail in the 
	        * case where the user purchases an item on one device and then uses your app on 
	        * a different device, because on the other device you will not have access to the
	        * random string you originally generated.
	        *
	        * So a good developer payload has these characteristics:
	        * 
	        * 1. If two different users purchase an item, the payload is different between them,
	        *    so that one user's purchase can't be replayed to another user.
	        * 
	        * 2. The payload must be such that you can verify it even when the app wasn't the
	        *    one who initiated the purchase flow (so that items purchased by the user on 
	        *    one device work on other devices owned by the user).
	        * 
	        * Using your own server to store and verify developer payloads across app
	        * installations is recommended.
	        */
	       
	       return true;
	   }


	   // Callback for when a purchase is finished
	   IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
	       public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
	           Log.d("TAG", "Purchase finished: " + result + ", purchase: " + purchase);
	           if (result.isFailure()) {
	               complain("Error purchasing: " + result);
	             //  setWaitScreen(false);
	               return;
	           }
	           if (!verifyDeveloperPayload(purchase)) {
	               complain("Error purchasing. Authenticity verification failed.");
	             //  setWaitScreen(false);
	               return;
	           }

	           Log.d("TAG", "Purchase successful.");

	          if (purchase.getSku().equals(SKU_PREMIUM)) {
	               // bought the premium upgrade!
	               Log.d("TAG", "Purchase is premium upgrade. Congratulating user.");
	               alert("Thank you for upgrading to premium!");
	               mIsPremium = true;
	               updateUi();
	            //   setWaitScreen(false);
	           }
	       }
	       };
	@Override
	public void onDestroy() {
		super.onDestroy();
		// BillingController.unregisterObserver(mBillingObserver);
		// shaker.close();
		 Log.d("TAG", "Destroying helper.");
         if (mHelper != null) mHelper.dispose();
         mHelper = null;
         
		unbindDrawables(findViewById(R.id.mainlayout));
		System.gc();
		Runtime rt = Runtime.getRuntime();
		long free = rt.freeMemory();
		long total = rt.totalMemory();
		long used = total - free;
		Log.v("free time", free / (1024 * 1024) + "");
		Log.v("total", total / (1024 * 1024) + "");
		Log.v("used", used / (1024 * 1024) + "");

	}
	 // updates UI to reflect model
    public void updateUi() {
 	   //	Toast.makeText(getApplicationContext(), "updateUi called", Toast.LENGTH_SHORT).show();
 	   	if(mIsPremium==true){

 	     SavePreferences("MEM2", "pro");
 		//ActivityContext.myList.add("UpgradeActivity");
		finish();
		Intent intent = new Intent(UpgradeActivity.this,
				SearchActivity.class);

		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);
 	     
 	   	}
 	   	else{
 	   	 SavePreferences("MEM2", "");	
 	   		
 	   	}
       

           
    }

    void complain(String message) {
        Log.e("TAG", "**** MANANEG APP ERROR: " + message);
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d("TAG", "Showing alert dialog: " + message);
        bld.create().show();
    }
	@Override
	public void onResume() {
		mShaker.resume();
		super.onResume();
	}

	@Override
	public void onPause() {
		mShaker.pause();
		super.onPause();
	}

	public void onBackPressed() {
		String act = ActivityContext.myList
				.get(ActivityContext.myList.size() - 1);
		Log.v("hello back class", act + ",");
		if (act.equals("UIActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, UIActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ListActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, ListActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("SearchActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this,
					SearchActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("FavActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, FavActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("MoreActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, MoreActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("InfoActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, InfoActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("UpgradeSearch")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, searchUgrade.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		}
		// else if(act.equals("UpgradeActivity")){
		// ActivityContext.myList.remove(ActivityContext.myList.size()-1);
		// finish();
		// Intent intent = new Intent(UpgradeActivity.this,
		// UpgradeActivity.class);
		// //intent.putExtra("token",act);
		// startActivity(intent);
		//
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_right);
		//
		// //Toast.makeText(getApplicationContext(), "fdf", 500).show();
		//
		// }
		else if (act.equals("RevealActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, RevealInfo.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ShareActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this,
					ShareActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ShareActivity1")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this,
					ShareActivity1.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		}

		else if (act.equals("HelpActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, HelpActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("randomquotes")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);
			finish();
			Intent intent = new Intent(UpgradeActivity.this, randomquote.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);

			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

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

	// public void onPurchaseStateChanged(String itemId, PurchaseState state) {
	// Log.i("billing", "onPurchaseStateChanged() itemId: " + itemId);
	// // updateOwnedItems();
	// }
	//
	// public void onRequestPurchaseResponse(String itemId, ResponseCode
	// response) {
	// Log.d("onRequestPurchaseResponse", response + " ");
	// }

	public boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

}
