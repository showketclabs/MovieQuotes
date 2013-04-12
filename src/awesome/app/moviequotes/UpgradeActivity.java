// Project Name: - Movie Quotes
// Purpose of file: - upgrade activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;

import net.robotmedia.billing.BillingController;
import net.robotmedia.billing.BillingRequest.ResponseCode;
import net.robotmedia.billing.helper.AbstractBillingObserver;
import net.robotmedia.billing.model.Transaction.PurchaseState;

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
    
    
    private static final int DIALOG_BILLING_NOT_SUPPORTED_ID = 2;

	private String mSku;

	private AbstractBillingObserver mBillingObserver;
	private AdView adview;

	public void onBillingChecked(boolean supported) {
	 if (supported) {

	  // mBuyButton.setEnabled(true);
	 } else {
	  showDialog(DIALOG_BILLING_NOT_SUPPORTED_ID);
	 }
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upgrade_activity);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(UpgradeActivity.this,fl,1184,720);
	//	shaker = new Shaker(this, 2.25d, 500, this);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);

		purchase = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		upgrade = (Button) findViewById(R.id.button2);
		temp = 0;
		adview = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4");  
		  AppRater.app_launched(UpgradeActivity.this);
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		if (strSavedMem1 == "") {
			try{
				      
			    RelativeLayout layout = (RelativeLayout)findViewById(R.id.add);        
			    layout.addView(adview);
			    AdRequest request = new AdRequest();
			    request.setTesting(false);
			    adview.loadAd(request);
			}catch(Exception e){
				
			}
		} else {
		
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
								Intent intent = new Intent(UpgradeActivity.this, dance.class);
								
								startActivity(intent);
		    		  			overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
		    		  		}
		      }
		    });
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeActivity");
				finish();
				Intent intent = new Intent(UpgradeActivity.this, HelpActivity.class);
				
				startActivity(intent);

				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				finish();
				Intent intent = new Intent(UpgradeActivity.this, dance.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});

		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//startActivity(new Intent(UpgradeActivity.this,com.blundell.test.AppMainTest.class));
			//	finish();
				
				if (haveNetworkConnection()) {
					
					SharedPreferences sharedPreferences = getSharedPreferences("MY",
							MODE_PRIVATE);
					String strSavedMem1 = sharedPreferences.getString("MEM2", "");
					
					//testing purpose
					 // SavePreferences("MEM2", "pro");
					// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
					// .show();
					if (strSavedMem1 == "") {
					Log.v("billl", "billing");
					   try {
						BillingController.requestPurchase(getApplicationContext(),
						     "remove_ads");
					} catch (Exception e) {
						Log.v("billl", "billing"+e.toString());
					}
					}
					else{
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								UpgradeActivity.this);
						// Setting Dialog Title
						alertDialog.setTitle("Message");
						// Setting Dialog Message
						alertDialog
								.setMessage("You have already purchased app.");
						// Setting Positive "Yes" Button
						alertDialog
								.setPositiveButton(
										"Ok",
										new DialogInterface.OnClickListener() {
											private Global global;

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												
												
											}
										});
					
						alertDialog.show();
						
					}
					  }
				else{
					
					Toast.makeText(getApplicationContext(), "Check your internet connection.", 500).show();
				}
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
						    Intent intent = new Intent(UpgradeActivity.this, UIActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ListActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, ListActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("SearchActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, SearchActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("FavActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, FavActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("MoreActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, MoreActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("InfoActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, InfoActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("UpgradeSearch")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, searchUgrade.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
//					else if(act.equals("UpgradeActivity")){
//						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//						finish();
//						    Intent intent = new Intent(UpgradeActivity.this, UpgradeActivity.class);
//							//intent.putExtra("token",act);
//							startActivity(intent);
//							
//							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//						
//						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//						
//					}
					else if(act.equals("RevealActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, RevealInfo.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ShareActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, ShareActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("ShareActivity1")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, ShareActivity1.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					else if(act.equals("HelpActivity")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, HelpActivity.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					
					else if(act.equals("randomquotes")){
						ActivityContext.myList.remove(ActivityContext.myList.size()-1);
						finish();
						    Intent intent = new Intent(UpgradeActivity.this, randomquote.class);
							//intent.putExtra("token",act);
							startActivity(intent);
							
							overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
						
						//Toast.makeText(getApplicationContext(), "fdf", 500).show();
						
					}
					
			}
		});

		purchase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (haveNetworkConnection()) {
					
					SharedPreferences sharedPreferences = getSharedPreferences("MY",
							MODE_PRIVATE);
					String strSavedMem1 = sharedPreferences.getString("MEM2", "");
					// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
					// .show();
					
					if (strSavedMem1 == "") {
					Log.v("billl", "billing");
					   try {
						BillingController.requestPurchase(getApplicationContext(),
						     "remove_ads");
					} catch (Exception e) {
						Log.v("billl", "billing"+e.toString());
					}
					}
					else{
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								UpgradeActivity.this);
						// Setting Dialog Title
						alertDialog.setTitle("Message");
						// Setting Dialog Message
						alertDialog
								.setMessage("You have already purchased app.");
						// Setting Positive "Yes" Button
						alertDialog
								.setPositiveButton(
										"Ok",
										new DialogInterface.OnClickListener() {
											private Global global;

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												
												
											}
										});
					
						alertDialog.show();
						
					}
					  }
                 else{
					
					Toast.makeText(getApplicationContext(), "Check your internet connection.", 500).show();
				}

			}
		});

		// loadfavlist
		
		
		
		mBillingObserver = new AbstractBillingObserver(UpgradeActivity.this) {

			  public void onBillingChecked(boolean supported) {
				  UpgradeActivity.this.onBillingChecked(supported);

			  }

			  public void onPurchaseStateChanged(String itemId,
			    PurchaseState state) {

				  UpgradeActivity.this.onPurchaseStateChanged(itemId, state);
			   // Toast.makeText(coin1.this,state+"",
			   // Toast.LENGTH_LONG).show();
			  }

			  public void onRequestPurchaseResponse(String itemId,
			    ResponseCode response) {
				  UpgradeActivity.this.onRequestPurchaseResponse(itemId, response);
			   if (response == ResponseCode.RESULT_OK) {

				   SavePreferences("MEM2", "pro");
					LoadPreferences();
				
					ActivityContext.myList.add("UpgradeActivity");
					finish();
					startActivity(new Intent(UpgradeActivity.this,
							SearchActivity.class));
					
					overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

			  
			    // code to remove ads parmanent

			   } else {
			    Toast.makeText(UpgradeActivity.this, response + "",
			     Toast.LENGTH_LONG).show();
			   }
			  }

			  public void onSubscriptionChecked(boolean supported) {
			   // TODO Auto-generated method stub

			  }
			 };
			 
			 
			 BillingController.registerObserver(mBillingObserver);
			 BillingController.checkBillingSupported(UpgradeActivity.this);

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
		 BillingController.unregisterObserver(mBillingObserver);
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
				    Intent intent = new Intent(UpgradeActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("UpgradeActivity")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(UpgradeActivity.this, UpgradeActivity.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, ShareActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}	 
			
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			} 
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(UpgradeActivity.this, randomquote.class);
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
	

	public void onPurchaseStateChanged(String itemId, PurchaseState state) {
	 Log.i("billing", "onPurchaseStateChanged() itemId: " + itemId);
	 // updateOwnedItems();
	}

	public void onRequestPurchaseResponse(String itemId, ResponseCode response) {
	 Log.d("onRequestPurchaseResponse", response + " ");
	}

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
