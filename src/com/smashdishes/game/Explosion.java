package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Explosion {
	GameView gameView;
	private Bitmap[] bitmaps;//位图	
	float x;//x方向位移
	float y;//y方向位移	
	private int aniIndex=0;//爆炸动画帧索引
	public Explosion(GameView gameView,Bitmap[] bitmaps,float x,float y)
	{
		this.gameView=gameView;
		this.bitmaps=bitmaps;
		this.x=x;
		this.y=y;
	}

	public void drawSelf(Canvas canvas, Paint paint) {
	
		canvas.drawBitmap(bitmaps[aniIndex], x, y,paint);
		if(aniIndex<bitmaps.length-1)//如果没有播放完
		{
			aniIndex++;
		}
		else//如果爆炸动画完毕
		{
			gameView.alExplosion.remove(this);//从列表中删除此爆炸
		}
		
	}
}