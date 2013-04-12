// Project Name: - s
// Purpose of file: - Share activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import awesome.app.moviequotes.TwitterApp.TwDialogListener;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.flurry.android.FlurryAgent;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class ShareActivity extends Activity {
	Button fb, btnemail, txt, tweet;
	Button shake, help, search, more, list, fav, back;
	public ButtonClicked clicked;
	public Intent i;
	AlertDialog.Builder alertDialog;
	int temp = 0;
	// twitter dec
	private TwitterApp mTwitter;
	public String path;
	public static File f;
	private static final String CONSUMER_KEY = "uTQueLRec6kt7iQnPUdvEQ";
	private static final String CONSUMER_SECRET = "YhVaM8wCkvsgf252BWYPL9WMzUXwkJI33CD5TnIx4M";

	private enum FROM {
		TWITTER_POST, TWITTER_LOGIN
	};

	private enum MESSAGE {
		SUCCESS, DUPLICATE, FAILED, CANCELLED
	};

	// facebook dec
	private static final String APP_ID = "444315482300075";
	private static final String[] PERMISSIONS = new String[] { "publish_stream" };

	private static final String TOKEN = "access_token";
	private static final String EXPIRES = "expires_in";
	private static final String KEY = "facebook-credentials";

	private Facebook facebook;
	private String messageToPost;
	// String facebookMessage;
	String postdata = null;
	Bundle parameters = new Bundle();
	ArrayList<String> facebookMessage = new ArrayList<String>();
	ArrayList<String> textmessage = new ArrayList<String>();
	//Shaker shaker;
	private ShakeListener mShaker;
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	String caller=null;
	private AdView adview;
	
    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }

	public boolean saveCredentials(Facebook facebook) {
		Editor editor = getApplicationContext().getSharedPreferences(KEY,
				Context.MODE_PRIVATE).edit();
		editor.putString(TOKEN, facebook.getAccessToken());
		editor.putLong(EXPIRES, facebook.getAccessExpires());
		return editor.commit();
	}

	public boolean restoreCredentials(Facebook facebook) {
		SharedPreferences sharedPreferences = getApplicationContext()
				.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		facebook.setAccessToken(sharedPreferences.getString(TOKEN, null));
		facebook.setAccessExpires(sharedPreferences.getLong(EXPIRES, 0));
		return facebook.isSessionValid();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		facebook = new Facebook(APP_ID);
		restoreCredentials(facebook);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.share_activity);
		fl=(FrameLayout)findViewById(R.id.rv);
		new AndroidScreenSize(ShareActivity.this,fl,1184,720);
		//shaker = new Shaker(this, 2.25d, 500, this);
		alertDialog = new AlertDialog.Builder(ShareActivity.this);
		// Get screenshot
		temp = 0;
		adview = new AdView(this, AdSize.BANNER, "a1513d779a8f2c4");  
		SharedPreferences sharedPreferences = getSharedPreferences("MY",
				MODE_PRIVATE);
		String strSavedMem1 = sharedPreferences.getString("MEM2", "");
		// Toast.makeText(UIActivity.this, strSavedMem1, Toast.LENGTH_LONG)
		// .show();
		  AppRater.app_launched(ShareActivity.this);
		if (strSavedMem1 == "") {
			try{
				      
			    RelativeLayout layout = (RelativeLayout)findViewById(R.id.add);        
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
		}
		//Bundle extras = getIntent().getExtras();
	//	byte[] byteArray = extras.getByteArray("picture");
//		Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0,
//				byteArray.length);
		saveBitmap(ActivityContext.bmp);
		f = new File(path);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		mTwitter = new TwitterApp(this, CONSUMER_KEY, CONSUMER_SECRET);
		// Log.v("Lenght", byteArray.toString());
		// Toast.makeText(getApplicationContext(), byteArray+"", 500).show();
		facebookMessage.clear();
		facebookMessage = Global.fbdata;
		textmessage.clear();
		textmessage = Global.textdata;

		Log.v("fb data", facebookMessage + "");
		Log.v("text data", textmessage + "");
		if (facebookMessage == null) {
			// facebookMessage = "Test wall post";
		}
		messageToPost = "facebookMessage";
		clicked = new ButtonClicked();
		fb = (Button) findViewById(R.id.btnfb);
		btnemail = (Button) findViewById(R.id.btnemail);
		txt = (Button) findViewById(R.id.btntext);
		tweet = (Button) findViewById(R.id.btntw);

		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		back = (Button) findViewById(R.id.btnback);
		
		//caller=getIntent().getExtras().getString("token");
		fav = (Button) findViewById(R.id.btnfav);
		
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
		    		  			
		    		  			startActivity(new Intent(ShareActivity.this, dance.class));
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
							ShareActivity.this);

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
										ActivityContext.myList.add("ShareActivity");
										finish();
										Intent intent = new Intent(ShareActivity.this, UpgradeActivity.class);
										
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
						ActivityContext.myList.add("ShareActivity");
						finish();
						Intent intent = new Intent(ShareActivity.this, SearchActivity.class);
					
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
						
						
					}
				}
			});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ShareActivity");
				finish();
				startActivity(new Intent(ShareActivity.this, HelpActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				
				finish();
				startActivity(new Intent(ShareActivity.this,
						dance.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ShareActivity");
				finish();
				startActivity(new Intent(ShareActivity.this, MoreActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ShareActivity");
				finish();
				startActivity(new Intent(ShareActivity.this, ListActivity.class));
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("ShareActivity");
				finish();
				startActivity(new Intent(ShareActivity.this, FavActivity.class));
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
					    Intent intent = new Intent(ShareActivity.this, UIActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("ListActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, ListActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("SearchActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, SearchActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("FavActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, FavActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("MoreActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, MoreActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("InfoActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, InfoActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeSearch")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, searchUgrade.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("UpgradeActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, UpgradeActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("RevealActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, RevealInfo.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
//				else if(act.equals("ShareActivity")){
//					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//					finish();
//					    Intent intent = new Intent(ShareActivity.this, ShareActivity.class);
//						//intent.putExtra("token",act);
//						startActivity(intent);
//						
//						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//					
//					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//					
//				}
				else if(act.equals("ShareActivity1")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, ShareActivity1.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				else if(act.equals("HelpActivity")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, HelpActivity.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				} 
				else if(act.equals("randomquotes")){
					ActivityContext.myList.remove(ActivityContext.myList.size()-1);
					finish();
					    Intent intent = new Intent(ShareActivity.this, randomquote.class);
						//intent.putExtra("token",act);
						startActivity(intent);
						
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
					
					//Toast.makeText(getApplicationContext(), "fdf", 500).show();
					
				}
				
			}
		});

	}

	@Override
	protected void onStart() {
		// Auto-generated method stub
		super.onStart();
		fb.setOnClickListener(clicked);
		btnemail.setOnClickListener(clicked);
		txt.setOnClickListener(clicked);
		tweet.setOnClickListener(clicked);
		FlurryAgent.onStartSession(ShareActivity.this, MY_KEY);//MY_KEY is key given by them.
        FlurryAgent.onEvent("Application started");
	}

	class ButtonClicked implements OnClickListener {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.btnemail:
				alertDialog.setTitle("Send As Email");
				alertDialog.setMessage("Click Yes to share..");
				alertDialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								for (int i = 0, j = 1; i < facebookMessage
										.size(); i++, j++) {
									if (postdata == null) {
										postdata = j
												+ "  "
												+ facebookMessage.get(i)
														.toString() + ",<p>";
									} else{
										postdata += j
												+ " "
												+ facebookMessage.get(i)
														.toString() + "<p>";}
								}
								postdata=postdata+"<p>"+"https://play.google.com/store/apps/details?id=awesome.app.moviequotes";

								if (haveNetworkConnection()) {
								try {
									new emai_s().execute();
								} catch (Exception e) {
									Toast.makeText(getApplicationContext(), "No Email Support on Your device.",500).show();
								}}
								else{
									
									Toast.makeText(getApplicationContext(), "Check your internet connection.",500).show();
								}
					
							}
						});

				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				alertDialog.show();

				break;
			case R.id.btnfb:

				postdata = null;
				alertDialog.setTitle("Share To Facebook");
				alertDialog.setMessage("Click Yes to share..");
				alertDialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								int j = 1;
								for (int i = 0; i < facebookMessage.size(); i++, j++) {

									// data[i] = facebookMessage.get(i) + ",";
									if (postdata == null) {
										postdata = j
												+ "  "
												+ facebookMessage.get(i)
														.toString() + "\n\n";
									} else
										postdata += j
												+ " "
												+ facebookMessage.get(i)
														.toString() + "\n\n";

								}
								if (!facebook.isSessionValid()) {
									loginAndPostToWall();
								} else {
									post(postdata);

								}
								fb.setEnabled(false);
							}

						});

				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				alertDialog.show();
				postdata = null;

				break;
			case R.id.btntext:
				try {
					postdata = null;
					alertDialog.setTitle("Send As Text");
					alertDialog.setMessage("Click Yes to share..");
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									int k = 1;
									for (int i = 0; i < textmessage.size(); i++, k++) {

										if (postdata == null) {
											postdata = k + " Ê"
													+ textmessage.get(i).toString()
													+ "\n\n";
										} else
											postdata += k + " "
													+ textmessage.get(i).toString()
													+ "\n\n";
									}
									String msgg = postdata+"https://play.google.com/store/apps/details?id=awesome.app.moviequotes";
									try {
										postdata = null;
										Intent intentsms = new Intent(
												Intent.ACTION_VIEW, Uri.parse("sms:"
														+ ""));
										intentsms.putExtra("sms_body", msgg);
										startActivity(intentsms);
									} catch (Exception e) {
										Toast.makeText(getApplicationContext(), "No Text Support on Your device.",500).show();
									}
									txt.setEnabled(true);
								}

							});

					alertDialog.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});

					alertDialog.show();
					postdata = null;
				} catch (Exception e1) {
					txt.setEnabled(true);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case R.id.btntw:
				postdata = null;
				alertDialog.setTitle("Share To Twitter");
				alertDialog.setMessage("Click Yes to share..");
				alertDialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mTwitter.setListener(mTwLoginDialogListener);
								mTwitter.resetAccessToken();
								if (mTwitter.hasAccessToken() == true) {
									try {
										mTwitter.uploadPic(
												f,
												String.valueOf(Html
														.fromHtml(TwitterApp.MESSAGE)));

										postAsToast(FROM.TWITTER_POST,
												MESSAGE.SUCCESS);
									} catch (Exception e) {
										if (e.getMessage().toString()
												.contains("duplicate")) {
											postAsToast(FROM.TWITTER_POST,
													MESSAGE.DUPLICATE);
										}
										e.printStackTrace();
									}
									mTwitter.resetAccessToken();
								} else {
									mTwitter.authorize();
								}
								tweet.setEnabled(false);
							}

						});

				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				alertDialog.show();
				postdata = null;
				break;
			default:
				break;
			}
		}

	}

	public void loginAndPostToWall() {
		facebook.authorize(this, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH,
				new LoginDialogListener());
	}

	class LoginDialogListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {
			saveCredentials(facebook);
			if (messageToPost != null) {
				post(postdata);
				// postToWall(messageToPost);
			}
		}

		@Override
		public void onFacebookError(FacebookError error) {
			showToast("Authentication with Facebook failed!");
			fb.setEnabled(true);
			//finish();
		}

		@Override
		public void onError(DialogError error) {
			showToast("Authentication with Facebook failed!");
			fb.setEnabled(true);
			//finish();
		}

		@Override
		public void onCancel() {
			showToast("Authentication with Facebook cancelled!");
			fb.setEnabled(true);
			//finish();
		}
	}

	private void showToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	public void post(String d) {

		parameters.putString("message", d+"Check out this great app.'Gazillion Movie Quote'  https://play.google.com/store/apps/details?id=awesome.app.moviequotes");
		// parameters.putStringArrayList("message", data);
		parameters.putString("picture", "http://i45.tinypic.com/36b6s.png");
		parameters.putString("link", "https://play.google.com/store/apps/details?id=awesome.app.moviequotes");
		parameters.putString("name", "Gazillion Movie Quotes");
		
		parameters.putString("caption", "play.google.com");
		
		parameters.putString("description", "Get Gazillion Movie Quotes on the Play Store.See screen shots,rates and customer reviews");
		new myAsyncTask().execute();
	}

	class myAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg) {
			try {
				facebook.request("me");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String response = facebook.request("me/feed", parameters,
						"POST");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			AlertDialog.Builder alertDialog4 = new AlertDialog.Builder(
					ShareActivity.this);
			alertDialog4.setTitle("Message");
			// Setting Dialog Message
			alertDialog4
					.setMessage("Message posted to your facebook wall !");
			// Setting Positive "Yes" Button
			alertDialog4
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
		
			alertDialog4.show();
			

		}
	}

	private void postAsToast(FROM twitterPost, MESSAGE success) {
		switch (twitterPost) {
		case TWITTER_LOGIN:
			switch (success) {
			case SUCCESS:
				Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG)
						.show();
				break;
			case FAILED:
				Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
				tweet.setEnabled(true);
			default:
				break;
			}
			break;
		case TWITTER_POST:
			switch (success) {
			case SUCCESS:
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						ShareActivity.this);
				// Setting Dialog Title
				alertDialog2.setTitle("Message");
				// Setting Dialog Message
				alertDialog2
						.setMessage("Posted Succesfully !");
				// Setting Positive "Yes" Button
				alertDialog2
						.setPositiveButton(
								"Ok",
								new DialogInterface.OnClickListener() {
									

									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										
										
									}
								});
			
				alertDialog2.show();
				
				break;
			case FAILED:
				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
						ShareActivity.this);
				// Setting Dialog Title
				alertDialog1.setTitle("Message");
				// Setting Dialog Message
				alertDialog1
						.setMessage("Posting Failed !");
				// Setting Positive "Yes" Button
				alertDialog1
						.setPositiveButton(
								"Ok",
								new DialogInterface.OnClickListener() {
									

									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										
										
									}
								});
			
				alertDialog1.show();
				tweet.setEnabled(true);
				break;
			case DUPLICATE:
				AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(
						ShareActivity.this);
				alertDialog3.setTitle("Message");
				// Setting Dialog Message
				alertDialog3
						.setMessage("Posting Failed because of duplicate message !");
				// Setting Positive "Yes" Button
				alertDialog3
						.setPositiveButton(
								"Ok",
								new DialogInterface.OnClickListener() {
									

									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										
										
									}
								});
			
				alertDialog3.show();
				tweet.setEnabled(true);
			default:
				break;
			}
			break;
		}
	}

	public void saveBitmap(Bitmap bm) {
		try {
			String mBaseFolderPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/";
			path = mBaseFolderPath + "abcd.jpg";

			FileOutputStream stream = new FileOutputStream(path);
			bm.compress(CompressFormat.JPEG, 100, stream);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			Log.e("Could not save", e.toString());
		}
	}

	private TwDialogListener mTwLoginDialogListener = new TwDialogListener() {

		@Override
		public void onError(String value) {
			postAsToast(FROM.TWITTER_LOGIN, MESSAGE.FAILED);
			Log.e("TWITTER", value);
			mTwitter.resetAccessToken();
		}

		@Override
		public void onComplete(String value) {
			try {
				mTwitter.updateStatus(TwitterApp.MESSAGE);
				// File f = new File("mnt/sdcard/DCIM/Camera/z.jpg");
				mTwitter.uploadPic(f,
						String.valueOf(Html.fromHtml(TwitterApp.MESSAGE)));
				postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
				// boolean deleted = f.delete();
			} catch (Exception e) {
				if (e.getMessage().toString().contains("duplicate")) {
					postAsToast(FROM.TWITTER_POST, MESSAGE.DUPLICATE);
				}
				e.printStackTrace();
			}
			mTwitter.resetAccessToken();
		}
	};

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
				    Intent intent = new Intent(ShareActivity.this, UIActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("ListActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, ListActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("SearchActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, SearchActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("FavActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, FavActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("MoreActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, MoreActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("InfoActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, InfoActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeSearch")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, searchUgrade.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("UpgradeActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, UpgradeActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("RevealActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, RevealInfo.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
//			else if(act.equals("ShareActivity")){
//				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
//				finish();
//				    Intent intent = new Intent(ShareActivity.this, ShareActivity.class);
//					//intent.putExtra("token",act);
//					startActivity(intent);
//					
//					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//				
//				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
//				
//			}
			else if(act.equals("ShareActivity1")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, ShareActivity1.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			}
			else if(act.equals("HelpActivity")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, HelpActivity.class);
					//intent.putExtra("token",act);
					startActivity(intent);
					
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				
				//Toast.makeText(getApplicationContext(), "fdf", 500).show();
				
			} 
			else if(act.equals("randomquotes")){
				ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				finish();
				    Intent intent = new Intent(ShareActivity.this, randomquote.class);
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

	 public class emai_s extends AsyncTask<Void, Void, String> {

		  private Drawable drawable;
		  private Uri screenshotUri;
		  ProgressDialog mDialog = null;
		private Activity activity=ShareActivity.this;
		String path;
		@Override
		protected void onPreExecute() {
			
							//bar.setVisibility(View.VISIBLE);
							 mDialog = new ProgressDialog(activity);
				             mDialog.setMessage("Please wait...");
				             mDialog.show();
						}
			//
		  @Override
		  protected String doInBackground(Void... params) {
		   try {
			   
		    InputStream is;
		    try {
		     is = (InputStream) new URL(
		       "http://i45.tinypic.com/36b6s.png")
		       .getContent();

		     // storing image from stream
		     drawable = Drawable.createFromStream(is, "srcName");
		     is.close();
		      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		     // Store image in Devise database to send image to mail
		     path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "title",null);
		     screenshotUri = Uri.parse(path);

		    } catch (MalformedURLException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }

		   

		   } catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } 
		   return null;
		  }

		  @Override
		  protected void onPostExecute(String result) {
		 
			  setProgressBarIndeterminateVisibility(false);
			  mDialog.hide();
		   Handler handler = new Handler();
		   handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {

		     try {
				final Intent emailIntent1 = new Intent(
				 android.content.Intent.ACTION_SEND);
				 emailIntent1.putExtra(Intent.EXTRA_EMAIL, new String[] { "" });
				 
				 emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 emailIntent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				
				 emailIntent1.putExtra(Intent.EXTRA_STREAM, screenshotUri);
				 emailIntent1.putExtra(Intent.EXTRA_SUBJECT,
				   "Gazillion Movie Quote Request!");
				

				 emailIntent1.putExtra(Intent.EXTRA_TEXT,
				   Html.fromHtml(postdata));
				 emailIntent1.setType("image/rfc822");

				 // emailIntent1.setType("image/png");
				 activity.startActivity(Intent.createChooser(emailIntent1,
				   "Send email using"));
				 
				 overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			} catch (Exception e) {
				Log.v("email error::", e.toString());
			}

		    }
		   }, 300);

		  }

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
