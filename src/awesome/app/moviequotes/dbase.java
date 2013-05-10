													// Project Name: - Movie Quotes
// Purpose of file: - Stores favorite quotes to database
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class dbase {
	static SQLiteDatabase db;
	//public static String[] f_quotes = {};
	//public static String[] f_movies = {};
	//public static String[] f_year = {};
	public static ArrayList<String> f_quotes=new ArrayList<String>();
	public static ArrayList<String> f_movies=new ArrayList<String>();
	public static ArrayList<String> f_year=new ArrayList<String>();
	static int i = 0;
	

	public dbase(Context c, String q, String m, String y) {

		// SQLiteDatabase db = null;
		// CREATING DATABASE
		try {
			db = c.openOrCreateDatabase("EMPDATABASE.db",
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
			// Toast.makeText(c, "Database Created",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(c, "Database error" + e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
		// CREATING TABLE
		try {
			final String Create_CashBook = "CREATE TABLE IF NOT EXISTS fav1 ("

			+ "movie TEXT," + "year TEXT," + "quote Varchar2)";
			db.execSQL(Create_CashBook);
		} catch (Exception e) {
			// Toast.makeText(c, "Table Creation Error" + e.getMessage(),
			// Toast.LENGTH_SHORT).show();

		}

		try {

			String sql = "SELECT * FROM fav1 WHERE movie == '" + m
					+ "' and quote=='" + q.replaceAll("'", "`")
					+ "' and year=='" + y + "'";
			Cursor data = db.rawQuery(sql, null);
			if (data.moveToFirst()) {
				// Toast.makeText(c, "record exists", 500).show();

			} else {

				try {
					//String qq = q.replaceAll("~", "");

					String Insert_Data = "INSERT INTO fav1 VALUES('"
							+ m.toString() + "','" + y.toString() + "','"
							+ q.replaceAll("'", "`").toString() + "');";

					db.execSQL(Insert_Data);
					// Toast.makeText(c, "Insertion successful",
					// Toast.LENGTH_SHORT).show();
					db.close();
				} catch (Exception e) {
					Log.v("e", e.toString());
				}

				// Toast.makeText(getApplicationContext(), "not exists",
				// 500).show();
			}

			db.close();

		} catch (Exception e) {
			Log.v("indbase", e.toString());

		}

	}

	public static void getfromdata(Context con) {
		
		try {
			db = con.openOrCreateDatabase("EMPDATABASE.db",
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
			// Toast.makeText(c, "Database Created",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(con, "Database error" + e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
		String fav_data = "SELECT * from fav1";
		Cursor c = db.rawQuery(fav_data, null);

		while (c.moveToNext()) {
			f_quotes.add(c.getString(c.getColumnIndex("quote")));
			f_year.add(c.getString(c.getColumnIndex("year")));
			f_movies.add(c.getString(c.getColumnIndex("movie")));
			i++;
			// System.out.println(uname);
			// System.out.println(uname);
			// System.out.println(uname);
		}
	
		db.close();
	}

}