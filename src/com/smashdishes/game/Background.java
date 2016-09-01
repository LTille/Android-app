package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {
	
	GameView gameView;
	Bitmap bitmap;
	

	public Background(Bitmap bitmap)
	{
		this.bitmap=bitmap;
	}
	
	public void drawSelf(Canvas canvas,Paint paint)
	{
		canvas.drawBitmap(bitmap,0,0,paint);
	}

}
