package com.smashdishes.game;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FigureView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

	Bitmap figureBitmap;
	Bitmap door;
	DishActivity activity;
	float x=Constant.Figure_X;
	float y=Constant.Figure_Y;
	float t=0;
	float timeSpan=1;
	float vx=-3;
	float vy=1;
	float g=0.1f;
	Thread th=new Thread(this);
	Canvas canvas;
	SurfaceHolder sfh;
	Paint paint;
	boolean flag=true;
	
	public FigureView(DishActivity activity) {
		super(activity);
		this.activity=activity;
	    sfh=this.getHolder();
		sfh.addCallback(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		paint=new Paint();
		paint.setAntiAlias(true);
		door=BitmapFactory.decodeResource(this.getResources(), R.drawable.door);
		figureBitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.figure);
		th.start();
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	public void draw(){
		canvas=sfh.lockCanvas();
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(door,300,100,paint);
		canvas.drawBitmap(figureBitmap,x,y,paint);
		sfh.unlockCanvasAndPost(canvas);
	}
	
	private void updateLocation() {
		// TODO Auto-generated method stub
		if(y<=380){
			x+=vx*t;
			y+=vy*t+1/2*g*t*t;
			t+=timeSpan;
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			draw();
			updateLocation();
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
			if(y>380){
				flag=false;
				activity.sendMessage(WhatMessage.GOTO_GAME_VIEW);
			}
			
		}
		
	}


}
