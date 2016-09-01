package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PauseControl{

	
	GameView gameView;
    Bitmap pauseBitmap;
    Bitmap unpauseBitmap;
	float x=600;
	float y=500;
	
	
	public PauseControl(GameView gameView,Bitmap pauseBitmap,Bitmap unpauseBitmap){
		
		this.gameView=gameView;
		this.pauseBitmap=pauseBitmap;
	}
	
	public void drawSelf(Canvas canvas, Paint paint) {
        
		canvas.drawBitmap(pauseBitmap,x,y,paint);
		
	}

	/*public void go(){
		
          if(gameView.isPause()){
			
			canvas.drawBitmap(pauseBitmap,x,y,paint);
			
			}else {
				
				canvas.drawBitmap(unpauseBitmap,x,y,paint);
				}
	}*/

    public boolean isTouchOn(int pressX, int pressY){

       if(pressX>600&&pressX<pauseBitmap.getWidth()+600&&pressY>500&&pressY<pauseBitmap.getHeight()+500){

          return true;
          }
        return false;
   }
/*    public boolean isTouchRestart(int pressX, int pressY){

        if(pressX>600&&pressX<unpauseBitmap.getWidth()+600&&pressY>500&&pressY<unpauseBitmap.getHeight()+500){

           return true;
           }
         return false;
    }*/

}
