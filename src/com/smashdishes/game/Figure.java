package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Figure{

	
	GameView gameView;
    Bitmap bitmap;
	float x=100;
	float y=380;
	
	public Figure(GameView gameView,Bitmap bitmap){
		
		this.gameView=gameView;
		this.bitmap=bitmap;
	}
	
	public void drawSelf(Canvas canvas, Paint paint) {
		
		canvas.drawBitmap(bitmap,x,y,paint);
	}


}
