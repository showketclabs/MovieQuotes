// Project Name: - Movie Quotes
// Purpose of file: - Adapter for quote list  to display
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyListAdapterTwoLine extends BaseAdapter {

	
	private LayoutInflater inflater;
	Context c;

	SQLiteDatabase db = null;
	 public static String[] movie;
	 public static  String[] year;
	 public static String[] quote;
	 
	Activity activity;
	 public static int b1[];
	public static ViewHolder holder;

	public MyListAdapterTwoLine(Activity a, Context context) {
		activity = a;
		this.c = context;
		
		
		movie=Global.movie.split("~, ");
		year=Global.year.split("~, ");
		quote=Global.quotes.split("~, ");
		b1=new int[quote.length];
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		for (int i = 0; i < quote.length; i++)
			b1[i] = 0;

	}

	@Override
	public int getCount() {
		return quote.length;
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
		CheckBox cb1Recup;
		public int p;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		int p = position;
		

		if (convertView == null) {

			vi = inflater.inflate(R.layout.itemlist, null);

			holder = new ViewHolder();
			holder.item = (RelativeLayout) vi.findViewById(R.id.item);
			holder.texte1Recup = (TextView) vi.findViewById(R.id.firstLine);
			holder.texte2Recup = (TextView) vi.findViewById(R.id.secondLine);
			holder.texte3Recup = (TextView) vi.findViewById(R.id.thirdLine);
			holder.cb1Recup = (CheckBox) vi.findViewById(R.id.cb1);
			
			holder.cb1Recup.setButtonDrawable(R.drawable.checkbox);
			
			holder.cb1Recup.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					CheckBox cBox = (CheckBox) v;
					holder = (ViewHolder) v.getTag();
					int pos = holder.p;
					if (cBox.isChecked() == true) {

						String quote = holder.texte1Recup.getText().toString()+"\n";
						String movie = "\n"+holder.texte2Recup.getText().toString()+"\n";
						String year = "\n"+holder.texte3Recup.getText().toString();
						new dbase(c, quote, movie, year);
						mylistadapter2.cnt++;

					}

					if (b1[pos] == 0) {

						b1[pos] = 1;

					} else
						b1[pos] = 0;

					
				}
			});
			holder.item.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					holder = (ViewHolder) v.getTag();
					int pos = holder.p;
					String randomdata = holder.texte1Recup.getText().toString()
							+ "\n" + "~" + "\n"
							+ holder.texte2Recup.getText().toString() + "\n"
							+ "~" + "\n"
							+ holder.texte3Recup.getText().toString();

					Global global = new Global(1, randomdata);
					randomdata=null;
					System.gc();
					ActivityContext.myList.add("ListActivity");
					
					Intent ii = new Intent(c, InfoActivity.class);
					
					ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					c.startActivity(ii);
					activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

				}
			});

			holder.p = position;
			holder.texte1Recup.setTag(holder);
			holder.texte2Recup.setTag(holder);
			holder.texte3Recup.setTag(holder);
			holder.item.setTag(holder);
			holder.cb1Recup.setTag(holder);

			vi.setTag(holder);
		} else {
			try {

				holder = (ViewHolder) vi.getTag();

			} catch (Exception e) {

			}

		}

		if (b1[position] == 1) {

			holder.cb1Recup.setChecked(true);
		} else {

			holder.cb1Recup.setChecked(false);
		}
		holder.p = position;
		
		holder.texte1Recup.setText(quote[position]);

		holder.texte2Recup.setText(movie[position]);

		holder.texte3Recup.setText(year[position]);

		return vi;

	}

}
