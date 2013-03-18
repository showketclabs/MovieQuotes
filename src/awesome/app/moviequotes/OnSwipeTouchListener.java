// Project Name: - Movie Quotes
// Purpose of file: - Swipe to delete favorite entry
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OnSwipeTouchListener implements OnTouchListener {

	View view;
	MotionEvent motionEvent;
	int position = 0;
	Button b;
	TextView t;
Context con;
	public OnSwipeTouchListener(Button b1, TextView t1,Context c) {
		this.b = b1;
		this.t = t1;
		this.con=c;

	}

	public OnSwipeTouchListener() {

	}

	private final GestureDetector gestureDetector = new GestureDetector(
			new GestureListener());

	@Override
	public boolean onTouch(final View v, final MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	private final class GestureListener extends SimpleOnGestureListener {

		private static final int SWIPE_THRESHOLD = 50;
		private static final int SWIPE_VELOCITY_THRESHOLD = 100;

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			
			boolean result = false;
			try {
				
				switch (position) {
				case 0:

					ScaleAnimation a = new ScaleAnimation(0, -2, 1, 1);// Animation.RELATIVE_TO_SELF,
																				// (float)0.4,
																				// Animation.RELATIVE_TO_SELF,
																				// (float)0.4);
					a.setDuration(200);
					a.setFillAfter(true);

					b.startAnimation(a);
					t.setText("Delete");
					// b.setBackgroundDrawable(null);
					// b

					Handler handler33 = new Handler();
					handler33.postDelayed(new Runnable() {
						@Override
						public void run() {

							// t.setText(" ");
							// Log.e("", "");

						}
					}, 2000);

					b.setEnabled(true);
					t.setEnabled(true);
					position = 1;
					break;

				case 1:
					ScaleAnimation a1 = new ScaleAnimation(-2, 0, 1, 1);// Animation.RELATIVE_TO_SELF,
																				// (float)0.1,
																				// Animation.RELATIVE_TO_SELF,
																				// (float)0.1);
					a1.setDuration(300);
					a1.setFillAfter(true);

					b.startAnimation(a1);

					Handler handler3 = new Handler();
					handler3.postDelayed(new Runnable() {
						@Override
						public void run() {

							t.setText(" ");
						}
					}, 50);
					position = 0;
					b.setEnabled(false);
					t.setEnabled(false);
					break;

				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return result;
		}
	}

	public void onSwipeRight() {
		Toast.makeText(con, "rght",500).show();
	}

	public void onSwipeLeft() {
	}

	public void onSwipeTop() {
	}

	public void onSwipeBottom() {
	}
}