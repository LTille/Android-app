package com.smashdishes.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;



public class Loading extends BaseActivity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.loading);	

		
	
	ImageView imageLogo=(ImageView) this.findViewById(R.id.image);
			
    AlphaAnimation animation =new AlphaAnimation(0.0f,1.0f);
	animation.setDuration(4000);
	        
	animation.setAnimationListener(new AnimationListener()
	 {
	       	
	         public void onAnimationEnd(Animation animation)
	       {
	        Intent intent= new Intent(Loading.this,LogActivity.class);
			startActivity(intent);
			
			finish();
			 
	}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}

	        });
imageLogo.setAnimation(animation);
}}