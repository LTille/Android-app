package com.smashdishes.game;
 

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

class WhatMessage{
	public static final int GOTO_FIGURE_VIEW=0;
	public static final int GOTO_GAME_VIEW=1;
	public static final int GOTO_WIN_VIEW=2;
	public static final int GOTO_HIGH_SCORE_VIEW=3;
	public static final int OVER_GAME=4;
	public static final int GOTO_GAME_VIEW2=5;
	
} 
public class DishActivity extends Activity implements OnClickListener {
	
	    int currentView;
        GameView gameView;
        WinView winView;
        FigureView figureView;
        int currScore;
        
        private Button button1;
       
        Handler myHandler = new Handler(){
        public void handleMessage(Message msg){
        	switch(msg.what)
        	{
        	case WhatMessage.GOTO_FIGURE_VIEW:
        	    gotoFigureView();
        	    break;
        	
        	case WhatMessage.GOTO_GAME_VIEW:
        	    gotoGameView();
        	    break;
        	
        	case WhatMessage.GOTO_WIN_VIEW:
        	    gotoWinView();
        	   break;
            
        	case WhatMessage.OVER_GAME:
        	    gotoOverView();
        	    break;
        	}    
        }

	
        };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
       
        DisplayMetrics dm = new DisplayMetrics();   
        getWindowManager().getDefaultDisplay().getMetrics(dm);    
        int ScreenX = dm.widthPixels;   
        //ScreenX=Constant.ScreenX;
        int ScreenY = dm.heightPixels;   
        //gameView = new GameView(this);
        //setContentView(gameView);
        //gotoGameView();
        gotoFigureView();

       
    }
    
    public void sendMessage(int what){
    	
    	Message msg1 = myHandler.obtainMessage(what);
    	myHandler.sendMessage(msg1);
    }
 
    private void gotoFigureView()
    {
    	if(figureView==null)
    	{
    		figureView = new FigureView(this);
    	}
    	this.setContentView(figureView);
    	currentView=WhatMessage.GOTO_FIGURE_VIEW;
    }
    
    public void onClick(View v){
    	
    	
    }
    private void gotoGameView()
    {
    	if(gameView==null)
    	{
    		gameView = new GameView(this);
    	}
    	this.setContentView(gameView);
    	currentView=WhatMessage.GOTO_GAME_VIEW;
    }
    
  
    
    private void gotoWinView()
    {
    	if(winView==null)
    	{
    		winView=new WinView(this);
    	}
    	this.setContentView(winView);
    	currentView=WhatMessage.GOTO_WIN_VIEW;
    }
    private void gotoOverView(){
     if(currScore>=0){
    	 this.gotoWinView();
     }
    	 
    }
}

