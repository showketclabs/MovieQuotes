// Project Name: - Movie Quotes
// Purpose of file: - Swiper class to swipe the quotes on touch on info activity
// Developed by Showket Ahmad,Clicklabs pvt. ltd.
package awesome.app.moviequotes;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class onswipeforinfo implements OnTouchListener {

	View view;
	MotionEvent motionEvent;

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
				float diffY = e2.getY() - e1.getY();
				float diffX = e2.getX() - e1.getX();
				if (Math.abs(diffX) > Math.abs(diffY)) {
					if (Math.abs(diffX) > SWIPE_THRESHOLD
							&& Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
						if (diffX > 0) {
							onSwipeRight();
						} else {
							onSwipeLeft();
						}
					} else {
						return true;
					}
				} else {

					if (Math.abs(diffY) > SWIPE_THRESHOLD
							&& Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
						if (diffY > 0) {
							onSwipeBottom();
						} else {
							onSwipeTop();
						}
					}
					return true;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return result;
		}
	}

	public void onSwipeRight() {

	}

	public void onSwipeLeft() {
	}

	public void onSwipeTop() {

		// Log.v("hello top", "top");
	}

	public void onSwipeBottom() {
		// Log.v("hello bottom", "bottom");
	}
}