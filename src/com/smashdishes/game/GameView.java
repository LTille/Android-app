package com.smashdishes.game;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
public class GameView extends SurfaceView implements SurfaceHolder.Callback,OnGestureListener,OnTouchListener{
	
	DishActivity activity;
	Paint paint;
	GestureDetector gesture;
	
	DrawThread drawThread;
	CountTimeThread countTimeThread;

	
	Bitmap background;
	Bitmap colonBitmap;
	Bitmap figureBitmap;
    Bitmap scoreBitmap;
    Bitmap pauseBitmap;
    Bitmap unpauseBitmap;
	Bitmap[] numberBitmaps;
	Bitmap[] explodeBitmapArray;
	Bitmap[] dishBitmap;
	
	List<Dish> arrDish;
	List<Explosion> alExplosion;
	
	Dish dish;
	Background back;
	Desk desk;
	Timer timer;
	Figure figure;
	CountScore score;
	//PauseControl pauseControl;
	
	int status;
	float x=Constant.Dish_X;
	float y=Constant.Dish_Y;
	SurfaceHolder holder; 
	
	SoundPool soundPool;
	HashMap<Integer,Integer> soundPoolMap;
	MediaPlayer mMediaPlayer;

    boolean isPause=false;
    int rect=0;
    
	Rect rectMenuQuit = new Rect(100, 500, 150, 550);
	Rect rectMenuSound = new Rect(200,500,250,550);
	Rect rectMenuMusic = new Rect(300,500,350,550);
	Rect rectMenuPause = new Rect(400,500,450,550);
	
	
	public GameView(DishActivity activity){
	   
		super(activity);
		this.activity=activity;
		this.requestFocus();
        this.setFocusableInTouchMode(true);
        holder = this.getHolder();
        holder.addCallback(this);
        this.setLongClickable(true); 
        this.setOnTouchListener(this);
        gesture=new GestureDetector(this);
        gesture.setIsLongpressEnabled(true);
     
         }


	@Override
	public void surfaceCreated(SurfaceHolder holder){
		paint=new Paint();
		paint.setAntiAlias(true);	
		createAllThreads();
		initBitmap();
		
		initBitmap();//初始化位图资源
		initSounds();//初始化声音资源
		
		arrDish=new Vector<Dish>();
		alExplosion=new Vector<Explosion>();;
		back = new Background(background);
		desk = new Desk(this,dishBitmap);
		timer=new Timer(this,colonBitmap,numberBitmaps);
		figure=new Figure(this,figureBitmap);
		score=new CountScore(this,scoreBitmap,numberBitmaps);
		//pauseControl=new PauseControl(this,pauseBitmap,unpauseBitmap);
		mMediaPlayer=MediaPlayer.create(activity,R.raw.background);
		mMediaPlayer.setLooping(true);
		
		//mMediaPlayer.start();
		drawThread.isViewOn=true;
		countTimeThread.isGameOn=true;
		startAllThreads();
		
	}
	
	public void startGame(){
		
		//isPause=false;
		setPause(false);
		countTimeThread.isGameOn=true;
		drawThread.isViewOn=true;
		if(!drawThread.isAlive()){
			drawThread.start();
		}
		if(!countTimeThread.isAlive()){
			countTimeThread.start();
		}
	}
	
	public void pauseGame(){
		
		//isPause=true;
		setPause(true);
		drawThread.isViewOn=false;
		countTimeThread.isGameOn=false;
		
	}
	
	public void stopGame(){
	
		isPause=false;
		drawThread.isViewOn=false;
		countTimeThread.isGameOn=false;
	}
	
	
	protected void Draw(Canvas canvas) {    
		try
		{  
			back.drawSelf(canvas,paint);
			List<Dish> arrDish1=new Vector<Dish>(arrDish);
			for(Dish i:arrDish1)
			{
			  i.drawSelf(canvas,paint);
			}
			List<Explosion> alExplosion1=new Vector<Explosion>(alExplosion);
			for(Explosion e:alExplosion1)
			{
				e.drawSelf(canvas,paint);
			}
			timer.drawSelf(canvas, paint);
			figure.drawSelf(canvas,paint);
			score.drawSelf(canvas,paint);
            if(isPause()){
			//pauseControl.drawSelf(canvas,paint);
            	
            	canvas.drawBitmap(pauseBitmap,600,100,paint);
            }else {
            	canvas.drawBitmap(unpauseBitmap,600,100,paint);
            }
            canvas.drawBitmap(unpauseBitmap,200,100,paint);
            
            if(rect==1){
            canvas.drawRect( 100, 300,500, 500, paint);
            }
		}catch(Exception e)
		  {
			e.printStackTrace();
		  }
	    
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {		
	
	}
	

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
        stopAllThreads();
        while (retry) {
            try {
            	drawThread.join();
            	countTimeThread.join();
            
                retry = false;
            } 
            catch (InterruptedException e) {
            	e.printStackTrace();
            	}
            if(mMediaPlayer.isPlaying()){
            	mMediaPlayer.stop();
            }
        }

		drawThread.isViewOn=false;
	}
	
	public void initBitmap(){
		dishBitmap=new Bitmap[]{
				BitmapFactory.decodeResource(this.getResources(), R.drawable.dish),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.dish1),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.dish2),
		};
		background=BitmapFactory.decodeResource(this.getResources(), R.drawable.kitchen);
		numberBitmaps=new Bitmap[]{
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number0),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number1),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number2),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number3),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number4),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number5),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number6),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number7),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number8),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number9),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.number0),
		};
		explodeBitmapArray=new Bitmap[]{
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode0),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode1),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode2),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode3),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode4),
				BitmapFactory.decodeResource(this.getResources(), R.drawable.explode5),
		};
		colonBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.breakmark);
		figureBitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.figure);
		scoreBitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.score);
		pauseBitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.pause);
		unpauseBitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.unpause);
	}
	
	public void createAllThreads()
	{
		drawThread=new DrawThread(this);
		countTimeThread=new CountTimeThread(this);
		
	}

	public void startAllThreads()
	{
		drawThread.setFlag(true);
		countTimeThread.setFlag(true);
		drawThread.start();
		countTimeThread.start();
	
	}
	
	public void stopAllThreads()
	{
		drawThread.setFlag(false);
		countTimeThread.setFlag(false);
	}
	
	public void initSounds(){
		soundPool=new SoundPool(4,AudioManager.STREAM_MUSIC,100);
		soundPoolMap=new HashMap<Integer,Integer>();
		soundPoolMap.put(1,soundPool.load(getContext(),R.raw.explosion,1));
		soundPoolMap.put(2,soundPool.load(getContext(),R.raw.fly,1));
	}
	
	public void playSound(int sound, int loop) {
	    AudioManager mgr = (AudioManager)getContext().getSystemService(Context.AUDIO_SERVICE);   
	    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
	    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
	    float volume = streamVolumeCurrent / streamVolumeMax;   	    
	    soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}

	public void overGame() {
		
		stopGame();
		stopAllThreads();
		activity.currScore=score.getScore();//将总得分赋值给activity中的score
	    activity.sendMessage(WhatMessage.OVER_GAME);//获得积分榜中最高分	
		
	}

	@Override
	public boolean onTouch(View v,MotionEvent event){
		
		return gesture.onTouchEvent(event);
		
	}
	
	@Override
	public boolean onDown(MotionEvent event) {

		return true;
	}

	@Override
	public void onLongPress(MotionEvent e2) {
		// TODO Auto-generated method stub
		}
		

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY) {

	    if(e1.getX()-e2.getX()>Constant.FLING_MIN_DISTANCE&&Math.abs(velocityX)>Constant.MIN_VELOCITY){
		
			status=1;
		}
		else if(e2.getX()-e1.getX()>Constant.FLING_MIN_DISTANCE&&Math.abs(velocityX)>Constant.MIN_VELOCITY){
			
			status=2;
		}
	
		return false;
  }
	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onSingleTapUp(MotionEvent event) {

              int x= (int)event.getX();
              int y= (int)event.getY();


  			if(isTouchOn(x, y))
        		{
        		    
        			if(!isPause())
        			{
        				pauseGame();
        				
        			}
        			
        			else if(isPause())
        			{
        			   startGame();
        			}
        		}
  			else if(isTouchRestart(x,y))
  			{
  			    //surfaceCreated(holder);
  				/*Intent intent = new Intent(activity,menu.class);
  				activity.startActivity(intent);*/
  				rect=1;
  				
  			}
  			else{

               arrDish.add(desk.produceDish());
			   status=0;
			}
		return false;
	}
	
	 public boolean isTouchOn(int pressX, int pressY){

	       if(pressX>600&&pressX<unpauseBitmap.getWidth()+600&&pressY>100&&pressY<unpauseBitmap.getHeight()+100){

	          return true;
	          }
	        return false;
	   }
	
	 public boolean isTouchRestart(int pressX, int pressY){

	       if(pressX>200&&pressX<unpauseBitmap.getWidth()+200&&pressY>100&&pressY<unpauseBitmap.getHeight()+100){

	          return true;
	          }
	        return false;
	   }
	 
	
	public int status(){
		
		return status;
	}
	
	public boolean isPause(){
		
		return isPause;
	}
	public void setPause(boolean isPause){
		
		this.isPause=isPause;
	}
	/*//暂停播放声音
		public void pauseSound(int sound){
			soundPool.pause(soundPoolMap.get(sound));
			
		}
		//停止播放声音
		public void stopSound(int sound){
			soundPool.stop(soundPoolMap.get(sound));
		}*/
}
