// Project Name: - Movie Quotes
// Purpose of file: - Adapter for quotes to display
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class mylistadapter2 extends BaseAdapter {

	
	private LayoutInflater inflater;
	Context c;
	public static int cnt=-1;
	SQLiteDatabase db = null;
	ArrayList<String> m = new ArrayList<String>();
	ArrayList<String> q = new ArrayList<String>();
	ArrayList<String> y = new ArrayList<String>();
	
	Activity activity;
	ListView listView;
	int post=0;
	 int initialHeight;
	 int actualHeight;
	// Button but;
	int b1[] ;
	public ViewHolder holder;

	public mylistadapter2(Activity a, Context context, ArrayList<String> list1,
			ArrayList<String> list2, ArrayList<String> list3) {
		this.q = list1;
		this.m = list2;
		this.y = list3;
		activity = a;

		Log.v("fav quotes", q + "q");
		Log.v("fav quotes", m + "m");
		Log.v("fav quotes", y + "y");
		
		b1= new int[q.size()];
		this.c = context;
		listView = (ListView) activity.findViewById(R.id.listView1);
		//but=(Button)activity.findViewById(R.id.cb2);
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		for (int i = 0; i <q.size(); i++)
			b1[i] = 0;

	}

	@Override
	public int getCount() {
		cnt=q.size();
	
		return q.size();
		
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		RelativeLayout item;
		TextView texte1Recup;
		TextView texte2Recup;
		TextView texte3Recup;
		Button cb1Recup;
		Button cb2Recup;
		public int p;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		int p = position;

		if (convertView == null) {

			vi = inflater.inflate(R.layout.favlistitem, null);

			holder = new ViewHolder();
			holder.item = (RelativeLayout) vi.findViewById(R.id.item);
			holder.texte1Recup = (TextView) vi.findViewById(R.id.firstLine);
			holder.texte2Recup = (TextView) vi.findViewById(R.id.secondLine);
			holder.texte3Recup = (TextView) vi.findViewById(R.id.thirdLine);
			//holder.texte4Recup = (TextView) vi.findViewById(R.id.textView1);
			holder.cb1Recup = (Button) vi.findViewById(R.id.cb1);
			holder.cb2Recup = (Button) vi.findViewById(R.id.cb2);
			
			final SwipeDetector swipeDetecter = new SwipeDetector();
			holder.item.setOnTouchListener(swipeDetecter);
			holder.cb2Recup.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button cBox = (Button) v;
					holder = (ViewHolder) v.getTag();
					int pos = holder.p;
					String randomdata = holder.texte1Recup.getText().toString()
							+ "~"
							+ holder.texte2Recup.getText().toString()
							+ "~"
							+ holder.texte3Recup.getText().toString();
Log.v("rdata", randomdata+"");
					Global global = new Global(1, randomdata);
					randomdata=null;
					System.gc();
					ActivityContext.myList.add("FavActivity");
					
					Intent ii = new Intent(c, InfoActivity.class);
					
					ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					c.startActivity(ii);
					activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
					activity.finish();
//					Toast.makeText(c, "you clicked:"+pos, 500).show();
//					Log.v("ffd", pos+"");
			}
			});
			holder.item.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					holder.cb1Recup.setVisibility(4);
					holder = (ViewHolder) v.getTag();
					int pos = holder.p;
					
					if (swipeDetecter.swipeDetected()) {
						RelativeLayout cBox = (RelativeLayout) v;
						holder = (ViewHolder) v.getTag();
						
						
						if (b1[pos] == 0) {

							b1[pos] = 1;

						} else
							b1[pos] = 0;
						
						if(post==0)
						{
					    holder.cb1Recup.setEnabled(true);			
						holder.cb1Recup.setVisibility(0);
						
						
						post=1;
						}
						else
						{
							holder.cb1Recup.setEnabled(true);
							
						//	holder.cb1Recup.setBackgroundResource(R.drawable.morep);
							holder.cb1Recup.setVisibility(4);
							//holder.cb2Recup.setVisibility(0);
							post=0;
						}
						
						holder.cb1Recup.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								Button cBox = (Button) arg0;
								holder = (ViewHolder) arg0.getTag();
								int pos = holder.p;
								//Toast.makeText(c, "but---"+pos, 500).show();

								

								// Setting Icon to Dialog
								// alertDialog.setIcon(R.drawable.delete);

							
									// mListe.get(position).setCb1(true);
									AlertDialog.Builder alertDialog = new AlertDialog.Builder(
											activity);
									// Setting Dialog Title
									alertDialog.setTitle("Confirm Delete...");
									// Setting Dialog Message
									alertDialog
											.setMessage("Are you sure you want delete this?");
									// Setting Positive "Yes" Button
									alertDialog
											.setPositiveButton(
													"YES",
													new DialogInterface.OnClickListener() {
														private Global global;

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															holder.cb1Recup
																	.setVisibility(View.GONE);
															try {
																db = c.openOrCreateDatabase(
																		"EMPDATABASE.db",
																		SQLiteDatabase.CREATE_IF_NECESSARY,
																		null);
																// Toast.makeText(c, "Database Created",
																// Toast.LENGTH_SHORT).show();
															} catch (Exception e) {
																Toast.makeText(
																		c,
																		"Database error"
																				+ e.getMessage(),
																		Toast.LENGTH_SHORT)
																		.show();
															}
															//												// CREATING TABLE
															try {

																String delquery = "delete from fav1 where movie='"
																		+ holder.texte2Recup
																				.getText()
																				.toString()
																		+ "' and quote='"
																		+ holder.texte1Recup
																				.getText()
																				.toString()
																				.replaceAll(
																						"'",
																						"`")
																		+ "'and year='"
																		+ holder.texte3Recup
																				.getText()
																				.toString()
																		+ "'";
																db.execSQL(delquery);

															} catch (Exception e) {
																Toast.makeText(
																		c,
																		" Error"
																				+ e.getMessage(),
																		Toast.LENGTH_SHORT)
																		.show();

															}

															// loading list with remaining elements
															try {
																Cursor allrows = db
																		.rawQuery(
																				"SELECT * FROM fav1",
																				null);
																allrows.moveToFirst();
																m.clear();
																q.clear();
																y.clear();
																System.gc();
																System.gc();
																while (allrows
																		.isAfterLast() == false) {

																	m.add(allrows
																			.getString(0));
																	q.add(allrows
																			.getString(
																					2)
																			.replaceAll(
																					"~",
																					""));
																	y.add(allrows
																			.getString(1));

																	allrows.moveToNext();
																}
																db.close();

																Log.v("hello entry=",
																		m + ",");

																ArrayList<String> data = new ArrayList<String>();
																for (int i = 1; i < q
																		.size(); i++) {
																	Log.v(" entry=",
																			m.get(i)
																					+ ",");
																	data.add("Quote:  "
																			+ q.get(i)
																					.replaceAll(
																							"`",
																							"'")
																			+ "\n\n"
																			+ "Movie:  "
																			+ m.get(i)
																			+ "\n\n"
																			+ "Year:  "
																			+ y.get(i)
																			+ "\n\n");
																}

																global = new Global(
																		data,
																		data);

																data = null;
																System.gc();
																System.gc();

															} catch (Exception e) {
																Log.v("mov name",
																		e.toString());
															}

															holder.cb1Recup
																	.setVisibility(View.INVISIBLE);
															FavActivity.adapter
																	.notifyDataSetChanged();
cnt--;

														}
													});
									// Setting Negative "NO" Button
									alertDialog
											.setNegativeButton(
													"NO",
													new DialogInterface.OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															// Write your code here to invoke NO event
															//Toast.makeText(c, "You clicked on NO",
															//		Toast.LENGTH_SHORT).show();
															dialog.cancel();
														}
													});
									// Showing Alert Message
									alertDialog.show();
									// TODO Auto-generated method stub
									cnt--;
							
								
							}
						});

						//Toast.makeText(c, "eee", 500).show();
					}else{
						String randomdata = holder.texte1Recup.getText().toString()
							 + "~" 
								+ holder.texte2Recup.getText().toString() 
								+ "~" 
								+ holder.texte3Recup.getText().toString();
							Log.v("rdata", randomdata+"");
						Global global = new Global(1, randomdata);
						randomdata=null;
						System.gc();
						
						ActivityContext.myList.add("FavActivity");
						
						Intent ii = new Intent(c, InfoActivity.class);
						
						ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						c.startActivity(ii);
						activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
						activity.finish();
					}
				}
			});
			// holder.item.setOnTouchListener(new
			// OnSwipeTouchListener(holder.cb1Recup, holder.texte4Recup,c));
			holder.p = position;
			holder.texte1Recup.setTag(holder);
			holder.texte2Recup.setTag(holder);
			holder.texte3Recup.setTag(holder);
			holder.cb1Recup.setTag(holder);
			holder.cb2Recup.setTag(holder);
			holder.item.setTag(holder);
			//holder.cb1Recup.setTag(holder);

			vi.setTag(holder);
		} else {

			holder = (ViewHolder) vi.getTag();

		}

		if (b1[position] == 1) {

			// holder.cb1Recup.setClickable(true);
			// holder.cb1Recup.setEnabled(true);
		//	holder.cb2Recup.setVisibility(4);
			holder.cb1Recup.setVisibility(4);
		//	holder.cb2Recup.setVisibility(0);
			//holder.texte4Recup.setVisibility(0);

		} else {

			// holder.cb1Recup.setClickable(false);
			// holder.cb1Recup.setEnabled(false);
			//holder.cb2Recup.setVisibility(4);
			holder.cb1Recup.setVisibility(4);
		//	holder.cb2Recup.setVisibility(4);
			//holder.texte4Recup.setVisibility(4);

		}

		holder.p = position;

		holder.texte1Recup.setText(q.get(position));

		holder.texte2Recup.setText(m.get(position));

		holder.texte3Recup.setText(y.get(position));

		return vi;

	}
	
}
