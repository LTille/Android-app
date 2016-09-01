package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class WinView extends SurfaceView implements SurfaceHolder.Callback{

	DishActivity activity;
	Bitmap gameover;
	Paint paint;
	Bitmap word;
	Bitmap restart;
	Bitmap exit;
	Bitmap rankinglist;
	Bitmap overback;
	

	
	int x=150;
	int y=50;
	int status;
	boolean flag=true;
	
	GestureDetector gesture;
	
	public WinView(DishActivity activity) {
		super(activity);
		this.activity=activity;
		this.requestFocus();
		this.setLongClickable(true); 
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);
		 this.setLongClickable(true); 
		 initBitmap();
	
	}
	
	
	 public void initBitmap(){
			
		restart = BitmapFactory.decodeResource(getResources(), R.drawable.restart);
		exit = BitmapFactory.decodeResource(getResources(), R.drawable.exit2);
		rankinglist = BitmapFactory.decodeResource(getResources(), R.drawable.rankinglist);
		word = BitmapFactory.decodeResource(getResources(), R.drawable.dialog);
		overback=BitmapFactory.decodeResource(getResources(), R.drawable.overback);
	 }
		public void Draw(Canvas canvas){
	
		canvas.drawBitmap(overback, 0, 0, paint);		
		canvas.drawBitmap(word,x,y,paint);
		canvas.drawBitmap(restart,150,420,paint);
		canvas.drawBitmap(rankinglist,300,420,paint);
		canvas.drawBitmap(exit,450,420,paint);
		
	}
	
       
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		paint=new Paint();
		paint.setAntiAlias(true);
		new Thread()
		{
			//int sleepSpan=100;
			public void run()
			{
				Canvas c;
				c=null;
				SurfaceHolder myHolder = WinView.this.getHolder();
				try
				{
					c=myHolder.lockCanvas(null);
					synchronized(myHolder)
					{
						WinView.this.Draw(c);
					}
				}finally
				{
						if(c!=null){
							myHolder.unlockCanvasAndPost(c);
						}
					
				}
			}
		 
		}.start();
		
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

       		
		if(event.getX()>105 && event.getX()<220
		    &&event.getY()>370 && event.getY()<490){
			activity.sendMessage(WhatMessage.GOTO_GAME_VIEW);
			}
		else if(event.getX()>275 && event.getX()<350
				&&event.getY()>370 && event.getY()<490)
				{
			activity.sendMessage(WhatMessage.GOTO_GAME_VIEW);
			}
		else if(event.getX()>400 && event.getX()<500
				&&event.getY()>370 && event.getY()<490)
				{
			      System.exit(0);
				}
			
	return super.onTouchEvent(event);
		}
}
	

