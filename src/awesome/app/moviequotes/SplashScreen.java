// Project Name: - Movie Quotes
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
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

public class SplashScreen extends Activity {


	ProgressBar bar;
	public String s=null,serverres=null;
	TextView t, m, y, q, loading;
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
		new AndroidScreenSize(SplashScreen.this,fl,800,480);
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
		bar = (ProgressBar) findViewById(R.id.progressBar1);
        bar.setVisibility(View.INVISIBLE);
		
		loading = (TextView) findViewById(R.id.textView1);
		loading.setVisibility(View.INVISIBLE);
		
		/////////
		
		
		
		
	    //SavePreferences("MEM1", "");
		if(haveNetworkConnection())
		{   
			LoadPreferences();
			if (serverres!="") {
				Handler mHandler = new Handler();

				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						// frameAnimation.stop();
					//	mDialog.hide();
						finish();
						startActivity(new Intent(SplashScreen.this,
								UIActivity.class));

					}
				}, 2000);
				try {
					
		            // mDialog.show();
					new myAsyncTask1().execute();
					parse();
				} catch (Exception e) {
					Log.v("responce", e.toString());
				}
				

			} else {
				try {
					new myAsyncTask().execute();
				//	parse();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.v("responce", e.toString());
				}

			

			}
			


			
			
			}
		else{
			
			Toast.makeText(getApplicationContext(), "Check Your Internet Connection..!", 500).show();
			//finish();
		}
		
	
		
		
	}
	// savving preference
		private void SavePreferences(String key, String value) {
			Global.sharedPreferences = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = Global.sharedPreferences.edit();
			editor.putString(key, value);
			editor.commit();
		}

		// loading preference
		private void LoadPreferences() {
			Global.sharedPreferences = getPreferences(MODE_PRIVATE);
			serverres = Global.sharedPreferences.getString("MEM1", "");
			//s=serverres;
		
		}
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
	public void onDestroy() {
		super.onDestroy();
		Movies=null;
		Quotes=null;
		Year=null;
	//	jObj=null;
		unbindDrawables(findViewById(R.id.mainlayout));
		System.gc();
		
	}
//	 public void onBackPressed() {
//		 finish();
//		  startActivity(new Intent(SplashScreen.this,
//					SplashScreen.class));
//
//
//         return;
//     }   
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
			SavePreferences("MEM1", s);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			 mDialog.hide();
//			bar.setVisibility(View.INVISIBLE);
//			loading.setVisibility(loading.INVISIBLE);
//	
			 try{
				 Log.v("s", s+"");
				 if(s!=null)
				 {
					    parse();
						finish();
						startActivity(new Intent(SplashScreen.this, UIActivity.class));
					
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
//background update of data from server
	class myAsyncTask1 extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg) {
			s = getUrlResponse("http://54.235.203.216/movies-quotes/movies.php");
			
			
			SavePreferences("MEM1", s);
			

			return null;
		}

	}


	public void parse() {
		LoadPreferences();
		
	

		try {
			jObj = new JSONObject(serverres.toString());
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
		 System.gc();

		}
		
	       
		
	



}
