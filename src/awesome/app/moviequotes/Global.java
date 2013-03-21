// Project Name: - Movie Quotes
// Purpose of file: - Global declarions and instializations
// Developed by Showket Ahmad,Clicklabs pvt. ltd.

package awesome.app.moviequotes;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;

public class Global {

	public static ArrayList<String> fbdata, textdata;
	public static int cnt = 0;

	private static LayoutInflater inflater;
	static View mylistview;
	static String datatodispaly, quotesharetofb;

	public static SharedPreferences sharedPreferences;
	public static String year, quotes, movie;

	public Global(String q, String m, String y) {

		year = y;
		quotes = q;
		movie = m;

		q = null;
		m = null;
		y = null;
		System.gc();

	}

	public Global(ArrayList<String> list1, ArrayList<String> list2) {
		fbdata = new ArrayList<String>(list1);
		textdata = new ArrayList<String>(list2);
		list1.clear();
		list2.clear();
		System.gc();

	}

	public Global(int cnt, String datatodispaly) {
		Global.cnt = cnt;
		Global.datatodispaly = datatodispaly;
		datatodispaly = null;
		System.gc();

	}

}
