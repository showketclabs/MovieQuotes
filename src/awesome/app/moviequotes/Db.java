
package awesome.app.moviequotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db {

	boolean hasTables;
	
	
	
	public static final String KEY_ROWID2 = "_id";
	public static final String KEY_INAPP = "in_app";
	
	public static final String DATABASE_NAME = "database_0";
	public static final String DATABASE_TABLE_INAPP = "tbl_inapp";
	
	public static final int DATABASE_VERSION = 2;

	private DbHelper ourhelper1;
	private final Context ourcontext;

	SQLiteDatabase db;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		// in this we execute query to create table

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
		
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_INAPP + "("
					+ KEY_ROWID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ KEY_INAPP + " TEXT NOT NULL );");

		
		}
		

		// this method calls when the table name already exists
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_INAPP);
			onCreate(db);
		}

	}

	// Constructor
	public Db(Context c) {
		ourcontext = c;
	}

	// open database to perform operation
	public Db open() {
		ourhelper1 = new DbHelper(ourcontext);
		db = ourhelper1.getWritableDatabase();
		return Db.this;
	}

	// close the data base after performing any database operation
	public void close() {
		// TODO Auto-generated method stub

		ourhelper1.close();
	}

	// this method insert the entries into the table1
	public long CreateEntry_INAPP(int more_draw) {
		// TODO Auto-generated method stub

		return db.insert(DATABASE_TABLE_INAPP, null,
				createContentValues(more_draw));
	}

	private ContentValues createContentValues(int more_draw ) {

		ContentValues cv = new ContentValues();
		cv.put(KEY_INAPP, more_draw);
		

		return cv;
}

	// update tbl2 row with new img
	public long updateMOREDRAW(int val) {
		// TODO Auto-generated method stub
		ContentValues cv2 = new ContentValues();

		cv2.put(KEY_INAPP, val);
		return db.update(DATABASE_TABLE_INAPP, cv2, null, null);
	}

	
	// get in app data
	public String inapp_data() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID2, KEY_INAPP };
		Cursor c = db.query(DATABASE_TABLE_INAPP, columns, null, null, null,
				null, null);

		String result1 = "";
		

		int in1 = c.getColumnIndex(KEY_INAPP);
		

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			result1 = result1 + c.getString(in1);
			

		}
		c.close();
		c.requery();
		String ary = result1;

		return ary;

	}

	// GET NO OF ROWS IN TABLE 1
	public int get_inapp_row() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM tbl_inapp";
		Cursor c = db.rawQuery(query, null);
		int lastId = c.getCount();
		c.close();
		return lastId;
	}




	// GET NO OF ROWS IN TABLE 2
	public int getrows2() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM tbl2";
		Cursor c = db.rawQuery(query, null);
		int lastId = c.getCount();
		c.close();
		return lastId;
	}



	

}
