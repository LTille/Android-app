package com.smashdishes.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Dish {
	GameView gameView;
	private Bitmap dishBitmap;
	private Bitmap[] explodeBitmaps;
    float x=Constant.Dish_X;
	float y=Constant.Dish_Y;
	float vx=20;
    float vy=10;
    float vy1=-15;
	private float t=0;
	private float timeSpan=1;
	private final float g=1f;
	public int status=3;
	private int number;
	

	public Dish(GameView gameView, Bitmap dishBitmap,int number)
	{
		this.gameView=gameView;
     	this.dishBitmap=dishBitmap;
     	explodeBitmaps=gameView.explodeBitmapArray;
		this.number=number;
	}
	
	public void drawSelf(Canvas canvas, Paint paint) {
		go();
		canvas.drawBitmap(dishBitmap, x, y,paint);
	
	}
	
	public void go()
	{

		switch(gameView.status()){
		case 0:
			
			x=x;
			y=y;
		break;
		case 1:
			x+=vx*t;
			y+=vy1*t-0.5f*g*t*t;
			t+=timeSpan;
			
			if(y<100)
			{
				this.disapear();
			}
			break;
	    
		case 2:
		    x+=vx*t;
		    y+=vy*t+0.5f*g*t*t;
	        t+=timeSpan;	
	        gameView.playSound(2,0);
	        if(touchFloor())		
			{
				explode();
			}
	}
		
	}

	public boolean touchFloor(){
		
		if(y>500&&y<550){
			return true;
		}
		return false;
	}
	public void explode(){
		
		gameView.playSound(1,0);
		gameView.arrDish.remove(this);
		gameView.alExplosion.add(new Explosion(gameView,explodeBitmaps,x,y));
		if(number==0){
		gameView.score.addScore(1);
		
		}
		if(number==1){
		gameView.score.addScore(0);
		gameView.overGame();
		}
	}
	public void disapear()
	{
		gameView.arrDish.remove(this);
	}

	

}
