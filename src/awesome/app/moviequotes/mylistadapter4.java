// Project Name: - Movie Quotes
// Purpose of file: - Adapter for search to display
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class mylistadapter4 extends BaseAdapter {

	private List<itemthreelinereveal> mListe;
	private LayoutInflater inflater;
	Context c;

	SQLiteDatabase db = null;
	

	public mylistadapter4(Context context, List<itemthreelinereveal> mListe2) {
		inflater = LayoutInflater.from(context);
		this.mListe = mListe2;
		this.c = context;
	}

	@Override
	public int getCount() {
		return mListe.size();
	}

	@Override
	public Object getItem(int position) {
		return mListe.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		RelativeLayout item;
		public int p;
		TextView texte1;
		TextView texte2;
		TextView texte3;
		Button cb1Recup;
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder = new ViewHolder();
		convertView = inflater.inflate(R.layout.searchitem, null);

		final RelativeLayout item = (RelativeLayout) convertView
				.findViewById(R.id.item);

		TextView texte1Recup = (TextView) convertView
				.findViewById(R.id.firstLine);
		texte1Recup.setText(mListe.get(position).getTexte1());
		TextView texte2Recup = (TextView) convertView
				.findViewById(R.id.secondLine);
		texte2Recup.setText(mListe.get(position).getTexte2());
		TextView texte3Recup = (TextView) convertView
				.findViewById(R.id.thirdLine);
		texte3Recup.setText(mListe.get(position).getTexte3());
		holder.p = position;
		
		Button cb1Recup = (Button) convertView.findViewById(R.id.cb1);
		cb1Recup.setTag(holder);
		cb1Recup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ViewHolder holder = (ViewHolder) v.getTag();
				int pos = holder.p;
				String randomdata = mListe.get(pos).getTexte1() + "\n"
						+ "~" + "\n" + mListe.get(pos).getTexte2() + "\n"
						+ "~" + "\n" + mListe.get(pos).getTexte3();
				// Toast.makeText(c, randomdata, 500).show();
				// InfoActivity.data=randomdata;
Log.v("", randomdata);
				Global global = new Global(1, randomdata);
				randomdata=null;
				System.gc();
				
				ActivityContext.myList.add("UpgradeSearch");
			
				Intent ii = new Intent(c, RevealInfo.class);
               
				c.startActivity(ii);
				((Activity) c).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
	//	cb1Recup.setcl(mListe.get(position).getCb1());
		//cb1Recup.setEnabled(false);

		item.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// item.setBackgroundColor(Color.parseColor("#000000"));
				String randomdata = mListe.get(position).getTexte1() + "\n"
						+ "~" + "\n" + mListe.get(position).getTexte2() + "\n"
						+ "~" + "\n" + mListe.get(position).getTexte3();
				// Toast.makeText(c, randomdata, 500).show();
				// InfoActivity.data=randomdata;
Log.v("", randomdata);
				Global global = new Global(1, randomdata);
				randomdata=null;
				System.gc();
				
				ActivityContext.myList.add("UpgradeSearch");
			
				Intent ii = new Intent(c, RevealInfo.class);
			
				c.startActivity(ii);
				((Activity) c).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
			}
		});
		if (mListe.get(position).getTexte2().compareTo("") == 0) {
			texte2Recup.setHeight(0);
			texte2Recup.setWidth(0);
		}
		return convertView;

	}

}
