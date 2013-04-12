package awesome.app.moviequotes;



import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class dance extends Activity {
	   AnimationDrawable animation;
	 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.shake);
         startAnimation();
	
	 }
	 class Starter implements Runnable {
         public void run() {
              animation.start();
          }
      
	 }
      private void startAnimation(){
          animation = new AnimationDrawable();
          animation.addFrame(getResources().getDrawable(R.drawable.a1), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a2), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a3), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a4), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a5), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a6), 120);
          animation.addFrame(getResources().getDrawable(R.drawable.a7), 120);
          animation.setOneShot(false);
          
          ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        
         
          imageView.setImageDrawable(animation);
          Handler handler = new Handler();
  		handler.postDelayed(new Runnable() {
  			
			private int temp=0;

			@Override
  			public void run() {
  				// anim.stop();
				
  				
  				
  				
  				finish();
  Intent i=new Intent(dance.this,randomquote.class);

  startActivity(i);
  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
  				/*
  				 * Intent i=new Intent(getApplicationContext(),rematch.class);
  				 * startActivity(i); overridePendingTransition(R.anim.flip,
  				 * R.anim.flip_rev);
  				 */
  			}
  		}, 2000);
          imageView.post(new Starter());
      }
      @Override
  	public void onDestroy() {
  		super.onDestroy();
  		
  		unbindDrawables(findViewById(R.id.mainlayout));
  		System.gc();
  	}
      public void onBackPressed() {
		 

	        
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
