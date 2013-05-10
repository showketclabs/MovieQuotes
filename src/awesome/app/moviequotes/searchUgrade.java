//Project Name: - Movie Quotes
// Purpose of file: -search list activity controls
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import clabs.androidscreenlibrary.AndroidScreenSize;


public class searchUgrade extends Activity implements TextWatcher,
		OnItemClickListener {
	private ListView lv;
	private EditText et;
	Button shake, help, search, more, list, fav, back;
	private String listview_array[] = {};
	private ArrayList<String> array_sort = new ArrayList<String>();
	private ArrayList<String> newquote = new ArrayList<String>();
	private ArrayList<String> newmovie = new ArrayList<String>();
	private ArrayList<String> newyear = new ArrayList<String>();
	int textlength = 0;
	private ArrayList<Picture> listPic = new ArrayList<Picture>();

	private PicListAdapter adapter1;
	// Shaker shaker;

	private ShakeListener mShaker;
	public static String[] movie;
	public static String[] year;
	public static String[] quote;
	int temp = 0;

	private FrameLayout fl;
	public static String searchmode;
	private SearchView mSearchView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.search_upgrade);

		
		lv = (ListView) findViewById(R.id.list_twoligne_itineraire);


		fl = (FrameLayout) findViewById(R.id.rv);
		new AndroidScreenSize(searchUgrade.this, fl, 1184, 720);
		shake = (Button) findViewById(R.id.btnshake);
		help = (Button) findViewById(R.id.btnhlp);
		search = (Button) findViewById(R.id.btnsearch);
		more = (Button) findViewById(R.id.btnmore);
		list = (Button) findViewById(R.id.btnlist);
		fav = (Button) findViewById(R.id.btnfav);
		back = (Button) findViewById(R.id.btnback);
		temp = 0;
		AppRater.app_launched(searchUgrade.this);
	
		final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		try {
			movie = Global.movie.split("~, ");
			year = Global.year.split("~, ");
			quote = Global.quotes.split("~, ");
			Log.i("Error:", Global.movie);
			Log.i("Error:", Global.year);
			Log.i("Error:", Global.quotes);
		} catch (Exception e) {
			Log.v("no data,,,,,,,,", e.toString());
		}
		mShaker = new ShakeListener(this);

		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
			@Override
			public void onShake() {

				if (temp == 0)

				{
					ActivityContext.revealflag = true;
					temp = 1;
					String act = ActivityContext.myList
							.get(ActivityContext.myList.size() - 1);
					Log.v("hello back class", act + ",");
					if (!act.equals("UpgradeSearch")) {

						ActivityContext.myList.add("UpgradeSearch");
					}
					Intent intent = new Intent(searchUgrade.this, dance.class);

					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
					finish();
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
							searchUgrade.this);

					// Setting Dialog Title
					alertDialog.setTitle("Upgrade App");

					// Setting Dialog Message
					alertDialog
							.setMessage("Do you want to Upgrade your app to enable Search options ?");

					// Setting Icon to Dialog
					// alertDialog.setIcon(R.drawable.delete);

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ActivityContext.myList.add("UpgradeSearch");

									Intent intent = new Intent(
											searchUgrade.this,
											UpgradeActivity.class);

									startActivity(intent);
									overridePendingTransition(
											R.anim.slide_in_left,
											R.anim.slide_out_left);
									finish();
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
					ActivityContext.myList.add("UpgradeSearch");

					Intent intent = new Intent(searchUgrade.this,
							SearchActivity.class);

					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_left);
					finish();
				}
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");

				Intent intent = new Intent(searchUgrade.this,
						HelpActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
			}
		});
		shake.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.revealflag = true;
				String act = ActivityContext.myList.get(ActivityContext.myList
						.size() - 1);
				Log.v("hello back class", act + ",");
				if (!act.equals("UpgradeSearch")) {

					ActivityContext.myList.add("UpgradeSearch");
				}
				Intent intent = new Intent(searchUgrade.this, dance.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
			}
		});
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");

				Intent intent = new Intent(searchUgrade.this,
						MoreActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
			}
		});
		fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");

				Intent intent = new Intent(searchUgrade.this, FavActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
			}
		});

		list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(getApplicationContext(), "in", 500).show();
				ActivityContext.myList.add("UpgradeSearch");

				Intent intent = new Intent(searchUgrade.this,
						ListActivity.class);

				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				finish();
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

					Intent intent = new Intent(searchUgrade.this,
							UIActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ListActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							ListActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("SearchActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							SearchActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("FavActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							FavActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("MoreActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							MoreActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("InfoActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							InfoActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("InfoActivity2")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							Info_reveal.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
				// else if(act.equals("UpgradeSearch")){
				// ActivityContext.myList.remove(ActivityContext.myList.size()-1);
				// finish();
				// Intent intent = new Intent(searchUgrade.this,
				// searchUgrade.class);
				// //intent.putExtra("token",act);
				// startActivity(intent);
				//
				// overridePendingTransition(R.anim.slide_in_right,
				// R.anim.slide_out_right);
				//
				// //Toast.makeText(getApplicationContext(), "fdf", 500).show();
				//
				// }
				else if (act.equals("UpgradeActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							UpgradeActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("RevealActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							RevealInfo.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ShareActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							ShareActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("ShareActivity1")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							ShareActivity1.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("HelpActivity")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							HelpActivity.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				} else if (act.equals("randomquotes")) {
					ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

					Intent intent = new Intent(searchUgrade.this,
							randomquote.class);
					// intent.putExtra("token",act);
					startActivity(intent);

					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_right);
					finish();
					// Toast.makeText(getApplicationContext(), "fdf",
					// 500).show();

				}
			}
		});
		searchmode = getIntent().getExtras().getString("mode");
		// Toast.makeText(getApplicationContext(), searchmode, 500).show();
		// Log.v("movies",searchmode+"");

		// Log.v("movies",Global.year1+"");
		// Log.v("movies",Global.qu_arr+"");
		// try{
		// if (searchmode.equals("moviesearch")) {
		//
		// array_sort.clear();
		// for(int i=0;i<movie.length;i++){
		// array_sort.add(movie[i]);
		// // Toast.makeText(getApplicationContext(),
		// array_sort.size()+"jhmj",500).show();
		// // Log.v("arraysearch",movie[i]+"");
		// }
		// // 5000).show();
		// } else if (searchmode.equals("quoteseach")) {
		// array_sort.clear();
		//
		// for(int i=0;i<quote.length;i++){
		// array_sort.add(quote[i]);
		// // Log.v("arraysearch",quote[i]+"");
		// }
		//
		//
		// } else {
		//
		// array_sort.clear();
		// for(int i=0;i<year.length;i++){
		// array_sort.add(year[i]);
		// // Log.v("arraysearch",year[i]+"");
		// // Toast.makeText(getApplicationContext(), array_sort.size()+"",
		// // 5000).show();/
		// }
		//
		// }
		// }catch(Exception e){
		// Log.e("Error", e.toString());
		// }
		// lv = (ListView) findViewById(R.id.list_twoligne_itineraire);
		// et = (EditText) findViewById(R.id.EditText01);
		// loadlist();
		// lv.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, movie));
		// lv.setTextFilterEnabled(true);
		// setupSearchView();
		//
		// Log.v("size",array_sort.size()+"");
		// //
		// et.addTextChangedListener(new TextWatcher() {
		// @Override
		// public void afterTextChanged(Editable s) {
		// // Abstract Method of TextWatcher Interface.
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// // Abstract Method of TextWatcher Interface.
		// }
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// ListView listView = (ListView)
		// findViewById(R.id.list_twoligne_itineraire);
		// List<itemthreelinereveal> mListe = new
		// ArrayList<itemthreelinereveal>();
		//
		// textlength = et.getText().length();
		// newquote.clear();
		// newmovie.clear();
		// newyear.clear();
		// for (int i = 0; i < array_sort.size(); i++) {
		//
		//
		// if(textlength <= array_sort.get(i).length()) {
		// String temp=array_sort.get(i);
		// Log.i("temp", temp+"");
		//
		//
		// temp=temp.replace("     ", " ");
		// temp=temp.replace("    ", " ");
		// temp=temp.replace("   ", " ");
		// temp=temp.replace("  ", " ");
		//
		// String wordArray[] = temp.split(" ");
		// // Log.i("word", array_sort.get(i).split(" ")+"");
		// Log.i("size word array", wordArray.length+"");
		// for(int k=0;k<wordArray.length;k++)
		// {
		// Log.i("word in array,,,,", wordArray[k]+"");
		// }
		//
		// for (int j = 0; j < wordArray.length; j++)
		// {
		// Log.i("index = ", j+"");
		// try{
		// if(et.getText().toString().equalsIgnoreCase((String)
		// wordArray[j].subSequence(0, textlength))){
		//
		// Log.i("in the condistion", "hbgvjhd");
		// Log.i("data from search", quote[i]+"");
		// newquote.add(quote[i]);
		// newmovie.add(movie[i]);
		// newyear.add(year[i]);
		//
		// //mListe.add(new
		// itemthreelinereveal(quote[i],movie[i],year[i],false));
		// break;
		// }
		// }
		// catch(Exception e)
		// {
		//
		// }
		// }
		// // if (textlength <= array_sort.get(i).length()) {
		// // if (et.getText().toString().equalsIgnoreCase((String)
		// array_sort.get(i).subSequence(0, textlength))) {
		// //
		// // mListe.add(new itemthreelinereveal(quote[i],movie[i],year[i]
		// // , false));
		// // }
		// // }
		// }
		// }
		// for(int k=0;k<newquote.size();k++)
		// {
		// mListe.add(new
		// itemthreelinereveal(newquote.get(k),newmovie.get(k),newyear.get(k),false));
		// }
		// mylistadapter4 adapter = new mylistadapter4(searchUgrade.this,
		// mListe,searchUgrade.this);
		// listView.setAdapter(adapter);
		// listView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View v,
		// int position, long id) {
		//
		// }
		// });
		//
		//
		// }
		//
		// });
		for (int i = 0; i < quote.length; i++) {
			Picture pic = new Picture(quote[i], movie[i], year[i]);
			listPic.add(pic);
		}
		adapter1 = new PicListAdapter(searchUgrade.this, listPic);
		lv.setAdapter(adapter1);
		lv.setOnItemClickListener(this);

		et = (EditText) findViewById(R.id.EditText01);
		et.addTextChangedListener(this);
		// viewpic = (ImageView) findViewById(R.id.viewpic_img);
	}

	// }
	// private void setupSearchView() {
	// mSearchView.setIconifiedByDefault(false);
	// mSearchView.setOnQueryTextListener(searchUgrade.this);
	// mSearchView.setSubmitButtonEnabled(false);
	// mSearchView.setQueryHint("search");
	// }
	//
	// public boolean onQueryTextChange(String newText) {
	// if (TextUtils.isEmpty(newText)) {
	// lv.clearTextFilter();
	// } else {
	// lv.setFilterText(newText.toString());
	// }
	// return true;
	// }
	//
	// public boolean onQueryTextSubmit(String query) {
	// return false;
	// }
	// private void loadlist() {
	//
	//
	//
	// try {
	// List<itemthreelinereveal> mListe = new ArrayList<itemthreelinereveal>();
	//
	//
	//
	// for (int i = 0; i < quote.length; i++) {
	//
	//
	// mListe.add(new itemthreelinereveal(quote[i],movie[i],year[i]
	// , false));
	//
	// }
	// adapter = new mylistadapter4(searchUgrade.this,
	// mListe,searchUgrade.this);
	// lv.setAdapter(adapter);
	// } catch (Exception e) {
	// Log.v("load list in list act", e.toString());
	// }
	//
	//
	// }

	@Override
	public void onDestroy() {
		super.onDestroy();

		System.gc();
		Runtime rt = Runtime.getRuntime();
		long free = rt.freeMemory();
		long total = rt.totalMemory();
		long used = total - free;
		Log.v("free time", free / (1024 * 1024) + "");
		Log.v("total", total / (1024 * 1024) + "");
		Log.v("used", used / (1024 * 1024) + "");

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

			Intent intent = new Intent(searchUgrade.this, UIActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ListActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, ListActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("SearchActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, SearchActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("FavActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, FavActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("MoreActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, MoreActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("InfoActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, InfoActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("InfoActivity2")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, Info_reveal.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		}
		// else if(act.equals("UpgradeSearch")){
		// ActivityContext.myList.remove(ActivityContext.myList.size()-1);
		// finish();
		// Intent intent = new Intent(searchUgrade.this, searchUgrade.class);
		// //intent.putExtra("token",act);
		// startActivity(intent);
		//
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_right);
		//
		// //Toast.makeText(getApplicationContext(), "fdf", 500).show();
		//
		// }
		else if (act.equals("UpgradeActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, UpgradeActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("RevealActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, RevealInfo.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ShareActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, ShareActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("ShareActivity1")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, ShareActivity1.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("HelpActivity")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, HelpActivity.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		} else if (act.equals("randomquotes")) {
			ActivityContext.myList.remove(ActivityContext.myList.size() - 1);

			Intent intent = new Intent(searchUgrade.this, randomquote.class);
			// intent.putExtra("token",act);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_right);
			finish();
			// Toast.makeText(getApplicationContext(), "fdf", 500).show();

		}
	}

	/**
	 * @author 9Android.net
	 */
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String text = et.getText().toString().toLowerCase();
		adapter1.filter(text);
	}

	/**
	 * @author 9Android.net
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// Sliding.slideFromRightToLeft(VIEW_PIC_SCREEN, fliper);
		Toast.makeText(getApplicationContext(),
				position + "" + listPic.get(position).getPicName(), 500).show();
		// viewpic.setImageResource(listPic.get(position).getPicSource());

	}

	/************************ adapter for search ***************/
	public class PicListAdapter extends BaseAdapter {
		private Context mContext;
		private LayoutInflater mInflater;
		private List<Picture> picList = null;
		private ArrayList<Picture> listpicOrigin;

		public PicListAdapter(Context context, List<Picture> picList) {
			mContext = context;
			this.picList = picList;
			mInflater = LayoutInflater.from(mContext);
			this.listpicOrigin = new ArrayList<Picture>();
			this.listpicOrigin.addAll(picList);
		}

		public class ViewHolder {
			RelativeLayout item;
			public int p;
			TextView picName;
			TextView picType;
			TextView thirdline;
			Button cb1Recup;
			// ImageView picIcon;
		}

		public View getView(int position, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				holder = new ViewHolder();
				view = mInflater.inflate(R.layout.searchitem, null);
				holder.item = (RelativeLayout) view.findViewById(R.id.item);
				holder.picName = (TextView) view.findViewById(R.id.firstLine);
				holder.picType = (TextView) view.findViewById(R.id.secondLine);
				holder.thirdline = (TextView) view.findViewById(R.id.thirdLine);

				Button cb1Recup = (Button) view.findViewById(R.id.cb1);
				cb1Recup.setTag(holder);
				holder.item.setTag(holder);
				holder.item.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ViewHolder holder = (ViewHolder) v.getTag();
						int pos = holder.p;
//						Toast.makeText(mContext, "aagaya" + pos, 500).show();
//						Toast.makeText(mContext,
//								"aagaya" + picList.get(pos).getPicName(), 500)
//								.show();
						String randomdata = picList.get(pos).getPicName() + "\n"
								+ "~" + "\n" + picList.get(pos).getPicType() + "\n"
								+ "~" + "\n" + picList.get(pos).getThirdLine();
						// Toast.makeText(c, randomdata, 500).show();
						// InfoActivity.data=randomdata;
		                Log.v("", randomdata);
						Global global = new Global(1, randomdata);
						randomdata=null;
						System.gc();
						ActivityContext.fromupgrade=true;
						ActivityContext.forswipecheck=true;
						ActivityContext.myList.add("UpgradeSearch");
					
						Intent ii = new Intent(searchUgrade.this, RevealInfo.class);
					
					    startActivity(ii);
						overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					   finish();
					}
				});
				cb1Recup.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						ViewHolder holder = (ViewHolder) v.getTag();
						int pos = holder.p;
//						Toast.makeText(mContext, "aagaya" + pos, 500).show();
//						Toast.makeText(mContext,
//								"aagaya" + picList.get(pos).getPicName(), 500)
//								.show();
						String randomdata = picList.get(pos).getPicName() + "\n"
								+ "~" + "\n" + picList.get(pos).getPicType() + "\n"
								+ "~" + "\n" + picList.get(pos).getThirdLine();
						// Toast.makeText(c, randomdata, 500).show();
						// InfoActivity.data=randomdata;
		               Log.v("", randomdata);
						Global global = new Global(1, randomdata);
						randomdata=null;
						System.gc();
					
						ActivityContext.fromupgrade=true;
						ActivityContext.forswipecheck=true;
						ActivityContext.myList.add("UpgradeSearch");
						
						Intent ii = new Intent(searchUgrade.this, RevealInfo.class);
		               
						startActivity(ii);
					   overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
						finish();

					}
				});
				// holder.thirdline = (ImageView) view.findViewById(R.id);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.p = position;
			holder.picName.setText(picList.get(position).getPicName());
			holder.picType.setText(picList.get(position).getPicType());
			holder.thirdline.setText(picList.get(position).getThirdLine());
			// holder.picIcon.setImageResource(picList.get(position).getPicSource());

			return view;
		}

		public int getCount() {
			return picList.size();
		}

		public Picture getItem(int position) {
			return picList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		/**
		 * Filter
		 * 
		 * @author 9Android.net
		 * 
		 */
		public void filter(String charText) {
			charText = charText.toLowerCase();
			picList.clear();
			if (charText.length() == 0) {
				picList.addAll(listpicOrigin);
			} else {
				for (Picture pic : listpicOrigin) {
					 if (searchmode.equals("quoteseach")) {
					if (pic.getPicName().toLowerCase().contains(charText)) {
						picList.add(pic);
					}
					 }
					 else  if (searchmode.equals("moviesearch")) {
						 if (pic.getPicType().toLowerCase().contains(charText)) {
								picList.add(pic);
						 
					 }
					 }
					 else
					 {
						 if (pic.getThirdLine().toLowerCase().contains(charText)) {
								picList.add(pic);
						 
					 }
					 }
				}
			}
			notifyDataSetChanged();
		}

	}
}
