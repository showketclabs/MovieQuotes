package awesome.app.moviequotes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppRater {
    private final static String APP_TITLE = "Movie Quotes";
    private final static String APP_PNAME = "awesome.app.moviequotes";
    
    private final static int DAYS_UNTIL_PROMPT = 1;
    private final static int LAUNCHES_UNTIL_PROMPT = 3;
    
    public static void app_launched(Context mContext) {
    	SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) { return ; }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }
Log.v("launch_count:", launch_count+"\n");
Log.v("current mili::", System.currentTimeMillis()+"\n");
Log.v("date first launch:", date_firstLaunch+"\n");
Log.v("date first launch plus:", date_firstLaunch + 
        (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)+"\n");
        // Wait at least n days before opening
        if (launch_count%LAUNCHES_UNTIL_PROMPT==0) {
            
                showRateDialog(mContext, editor);
            
        }

        editor.commit();
    }   
    
    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setTitle("Rate " + APP_TITLE);

        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        TextView tv = new TextView(mContext);
        tv.setText("We need your help! Please take a moment to rate this app. It will help us continue to make great apps for you. If you love this app, please give us lots of stars.");
        tv.setWidth(300);
        tv.setPadding(4, 0, 4, 10);
        ll.addView(tv);
        
        Button b1 = new Button(mContext);
        b1.setText("Rate This App");
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=awesome.app.moviequotes")));
                dialog.dismiss();
            }
        });        
        ll.addView(b1);

        Button b2 = new Button(mContext);
        b2.setText("Remind Me Later");
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ll.addView(b2);

        Button b3 = new Button(mContext);
        b3.setText("No, Thanks");
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(b3);

        dialog.setContentView(ll);        
        dialog.show();        
    }
}