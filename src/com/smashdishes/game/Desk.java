package com.smashdishes.game;

import java.util.Random;

import android.graphics.Bitmap;



public class Desk {
	
	GameView gameView;
    Bitmap[] dishBitmap;
    
    public Desk(GameView gameView,Bitmap[] dishBitmap){
    	
    	this.gameView=gameView;
    	this.dishBitmap=dishBitmap;
    	
    }


	public Dish produceDish() {
		Random rand=new Random();
		int random=rand.nextInt(4);
		Dish result=null;
		if(random==0||random==2){
		result=new Dish(gameView,dishBitmap[0],0);
		
		}
		if(random==1){
	    result=new Dish(gameView,dishBitmap[1],1);
		}
		if(random==3){
		result=new Dish(gameView,dishBitmap[2],1);
		}
		return result;
	}
}
