package awesome.app.moviequotes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flurry.android.FlurryAgent;



public class dance extends Activity {
	RelativeLayout ll;
	AnimationDrawable frameAnimation;
	ImageView a;

	// FramesSequenceAnimation anim;

	int[] mTapScreenTextAnimRes = { R.drawable.a1,  R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7};
	private final int mTapScreenTextAnimDuration = 130;
	private final int mTapScreenTextAnimBreak = 160;
	
	String MY_KEY="MWZY29QZHHSWXYQS8DYN";
	private FrameLayout fl;
	@Override
	protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(dance.this, MY_KEY);//MY_KEY is key given by them.
        FlurryAgent.onEvent("Application started");
    }
    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		

		setContentView(R.layout.shake);
	//	fl=(FrameLayout)findViewById(R.id.rv);
	//	new AndroidScreenSize(dance.this,fl,800,480);
		a= (ImageView) findViewById(R.id.img);

		

		new SceneAnimation(a, mTapScreenTextAnimRes,
				mTapScreenTextAnimDuration, mTapScreenTextAnimBreak);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// anim.stop();
				finish();
Intent i=new Intent(dance.this,randomquote.class);
startActivity(i);
				/*
				 * Intent i=new Intent(getApplicationContext(),rematch.class);
				 * startActivity(i); overridePendingTransition(R.anim.flip,
				 * R.anim.flip_rev);
				 */
			}
		}, 3000);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			mTapScreenTextAnimRes = null;
		} catch (Exception e) {

		}
		unbindDrawables(findViewById(R.id.mainlayout));
		System.gc();
	}
	 public void onBackPressed() {
		  finish();
		  startActivity(new Intent(dance.this,
					UIActivity.class));


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
					// if can't remove all view (e.g. adapter view) - no problem
				}
			}
		} catch (Exception e) {

		}
	}
}
