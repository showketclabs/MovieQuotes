package awesome.app.moviequotes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AndroidScreenSize {
	FrameLayout rv;
	FrameLayout.LayoutParams layoutParams_relative;
	private Activity context;
	float scale;
	float xx;
	int height, width;
	float density = 0;
	int heightp, widthp;

	// CONSTRUCTER

	public AndroidScreenSize(Activity a, FrameLayout rv2, int height1,
			int width1) {
		context = a;
		rv = rv2;
		heightp = height1;
		widthp = width1;
		height = context.getResources().getDisplayMetrics().heightPixels;
		width = context.getResources().getDisplayMetrics().widthPixels;
		density = context.getResources().getDisplayMetrics().density;
		ViewGroup rootView = rv;
		doit();
		scale();
		setsize(rootView);
	}

	private void doit() {
		layoutParams_relative = new FrameLayout.LayoutParams(widthp, heightp);
		layoutParams_relative.setMargins(width / 2 - (widthp / 2), height / 2
				- (heightp / 2), 0, 0);
		rv.setLayoutParams(layoutParams_relative);
	}

	private void scale() {
		for (int i = 0; i <= 300; i++) {
			float ab = i;
			xx = (ab / 100);
			if (((int) (widthp * xx)) <= width
					&& ((int) (heightp * xx)) <= height)
				scale = xx;
		}
		rv.setScaleX(scale);
		rv.setScaleY(scale);
	}

	void setsize(ViewGroup parent) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			View child = parent.getChildAt(i);
			if (child instanceof ViewGroup) {
				setsize((ViewGroup) child);
			} else if (child != null) {
				if (child.getClass() == TextView.class) {
					TextView textView = (TextView) child;
					textView.setTextSize(textView.getTextSize() / (density));
				}
				if (child.getClass() == EditText.class) {
					EditText editText = (EditText) child;
					editText.setTextSize(editText.getTextSize() / (density));
				}
				if (child.getClass() == Button.class) {
					Button button = (Button) child;
					button.setTextSize(button.getTextSize() / density);
				}
			}
		}
	}
}
