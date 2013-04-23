// Project Name:Movie Quotes
// Purpose of file: - Twitter controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;
import com.flurry.android.FlurryAgent;

public class SplashScreen extends Activity {
//in app purchase
	String[] arr1;

	// Does the user have the premium upgrade?
	boolean mIsPremium = false;

	// inapp_product id
	static final String SKU_PREMIUM = "remove_ads";

	// (arbitrary) request code for the purchase flow
	static final int RC_REQUEST = 10001;

	// The helper object
	IabHelper mHelper;
	
	public String s=null,serverres=null;
	TextView t, m, y, q;
	String movie="";
	String quotes="";
	String year="";
	public int flag=0;
	Runtime rt;
	 long free ;
     long total;
     long used ;
     ProgressDialog mDialog = null;
	ArrayList<String> mov1 = new ArrayList<String>();
	ArrayList<String> year1 = new ArrayList<String>();
	ArrayList<String> quo1 = new ArrayList<String>();
	private JSONArray Movies, Quotes, Year;
	private JSONObject jObj;
	
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	ImageView gazil,togo;
	Animation a;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(SplashScreen.this, MY_KEY);//MY_KEY is key given by them.
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
		setContentView(R.layout.splash);
		fl=(FrameLayout)findViewById(R.id.rv);
		ActivityContext.myList.clear();
		System.gc();
		new AndroidScreenSize(SplashScreen.this,fl,1184,720);
		     mov1.clear();
			 year1.clear();
			 quo1.clear();
			 mDialog = new ProgressDialog(SplashScreen.this);
             mDialog.setMessage("Loading...");
			 rt=Runtime.getRuntime();
			 free = rt.freeMemory();
		        total = rt.totalMemory();
		        used = total - free;
		        Log.v("free time",free/(1024*1024)+"");
		        Log.v("total",total/(1024*1024)+"");
		        Log.v("used",used/(1024*1024)+"");
		
		        gazil = (ImageView)findViewById(R.id.imageView1);
		        togo = (ImageView)findViewById(R.id.imageView2);
		    	gazil.setVisibility(0);
			     a = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.slideup);
			    gazil.startAnimation(a);
			    Handler mHandler1 = new Handler();

				mHandler1.postDelayed(new Runnable() {
					@Override
					public void run() {
						// frameAnimation.stop();
					//	mDialog.hide();
						 Handler mHandler1 = new Handler();

							mHandler1.postDelayed(new Runnable() {
								@Override
								public void run() {
									// frameAnimation.stop();
								//	mDialog.hide();
									    
									   
									try {
										if(haveNetworkConnection())
										{  
										try {
											new myAsyncTask().execute();
										} catch (Exception e) {
											Toast.makeText(getApplicationContext(), "Check Your Internet Connection..!", 500).show();
											e.printStackTrace();
										}
									//	parse();
										}
										else{
											
											Toast.makeText(getApplicationContext(), "Check Your Internet Connection..!", 500).show();
											//finish();
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
										Log.v("responce", e.toString());
									}


								}
							}, 1000);
					
						   
						togo.setVisibility(0);
					     a = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.slideup2);
					    togo.startAnimation(a);

					}
				}, 1000);
		
		
		
				try {
					String payload = "";

					mHelper.launchPurchaseFlow(SplashScreen.this,
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

			


			
			
		
		
		
	
		
	
	
	private boolean haveNetworkConnection() {
		 boolean NetConnected = false;

	     try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm == null)
			{
			    NetConnected = false;
			}
			else
			{
			    NetworkInfo[] info = cm.getAllNetworkInfo();
			    if (info != null)
			    {
			        for (int i = 0; i < info.length; i++)
			        {
			            if (info[i].getState() == NetworkInfo.State.CONNECTED)
			            {
			                NetConnected = true;
			            }
			        }
			    }
			}
		} catch (Exception e) {
			NetConnected = false;
		}
	    return NetConnected;
   
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
	       public void updateUi() {
	     	   //	Toast.makeText(getApplicationContext(), "updateUi called", Toast.LENGTH_SHORT).show();
	     	   	if(mIsPremium==true){

	     	     SavePreferences("MEM2", "pro");
	     		
	    		
	     	     
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
	        private void SavePreferences(String key, String value) {
	    		SharedPreferences sharedPreferences = getSharedPreferences("MY",
	    				MODE_PRIVATE);
	    		SharedPreferences.Editor editor = sharedPreferences.edit();
	    		editor.putString(key, value);
	    		editor.commit();
	    	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Movies=null;
		Quotes=null;
		Year=null;
	//	jObj=null;
		unbindDrawables(findViewById(R.id.mainlayout));
		System.gc();
		
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

	// Get Url Response
	public  String getUrlResponse(String url) {
		
			try {
				HttpGet get = new HttpGet(url);
				HttpClient client = new DefaultHttpClient();
				HttpResponse response = client.execute(get);
				HttpEntity entity = response.getEntity();
				return convertStreamToString(entity.getContent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//flag=1;
				e.printStackTrace();
			} 
		
		return null;
	}

	// Convert the Response to String
	private  String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is),
				4 * 1024);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		return sb.toString();
	}

	// Loading Quotes on looading
	class myAsyncTask extends AsyncTask<Void, Void, String> {

		

		@Override
		protected void onPreExecute() {
			 mDialog.show();
			 mDialog.setCanceledOnTouchOutside(false);
//			bar.setVisibility(View.VISIBLE);
//			loading.setVisibility(loading.VISIBLE);
		}

		@Override
		protected String doInBackground(Void... arg) {
			
			s = getUrlResponse("http://54.235.203.216/movies-quotes/movies.php");
		
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			 mDialog.hide();
			 mDialog.dismiss();

			 try{
				 Log.v("s", s+"");
				 if(s!=null)
				 {
					 ActivityContext.myList.add("UIActivity");
					    parse();
						finish();
						startActivity(new Intent(SplashScreen.this, UIActivity.class));
						overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					
			}else{
				 
				 Toast.makeText(getApplicationContext(), "Server Error...", 500).show();	
			}
			
			
			 rt=Runtime.getRuntime();
			 free = rt.freeMemory();
		        total = rt.totalMemory();
		        used = total - free;
		        Log.v("free time",free/(1024*1024)+"");
		        Log.v("total",total/(1024*1024)+"");
		        Log.v("used",used/(1024*1024)+"");
			 }
			 catch(Exception e)
			 {
			 }

		}
	}


	public void parse() {
		
		
	

		try {
			jObj = new JSONObject(s.toString());
			// Log.v("inside",jObj.toString());
		} catch (JSONException e1) {
			flag=1;
		//	Log.v("inside", "error...");

		}

	

			
				try {
					Movies = jObj.getJSONArray("movies");
					Quotes = jObj.getJSONArray("quotes");
					Year = jObj.getJSONArray("year");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//Log.v("json", e.toString());
				}
				try {
				
				if (Movies != null) { 
				   int len = Movies.length();
				   for (int i=0;i<len;i++){ 
				    mov1.add(Movies.get(i).toString()+"~");
				 //   Log.v("mov name", Movies.get(i).toString());
				   } 
				} 
				if (Quotes != null) { 
					   int len = Quotes.length();
					   for (int i=0;i<len;i++){ 
					    quo1.add(Quotes.get(i).toString()+"~");
					   // Log.v("quo name", Quotes.get(i).toString());
					   } 
					} 
				if (Year != null) { 
					   int len = Year.length();
					   for (int i=0;i<len;i++){ 
					    year1.add(Year.get(i).toString()+"~");
					//   Log.v("year name", Year.get(i).toString());
					   } 
					} 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			movie=mov1+"";
			year=year1+"";
			quotes=quo1+"";
			//Log.v("year",year+"");
	
			
			
		Global global = new Global(quotes.substring(1, quotes.length()-2),movie.substring(1, movie.length()-2), year.substring(1, year.length()-2));
		
		
		Log.v("quotes = ",quotes);
		Log.v("movie = ",movie);
		Log.v("year = ",year);
		
		//Runtime.getRuntime().maxMemory();
		movie=null;
		year=null;
		quotes=null;
		 Movies=null;
		 Quotes=null;
		 Year=null;
		 jObj=null;
		 mov1.clear();
		 year1.clear();
		 quo1.clear();
		 		 
		 s=null;
		 System.gc();
		 
		}
		
	       
		
	



}
