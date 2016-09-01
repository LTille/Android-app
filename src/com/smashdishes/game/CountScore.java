package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CountScore {
	GameView gameView;
	final int space=5;//文字和力度条的距离
	private Bitmap defenBitmap;//得分位图
	private Bitmap[] numberBitmaps;	//数字位图
	private int score=0;
	int x=Constant.Score_X;//数字的右上点坐标
	int y=Constant.Score_Y;
	int numberWidth;
	int numberHeight;
	int textWidth;
	int textHeight;	
	public CountScore(GameView gameView,Bitmap defenBitmap,Bitmap[] numberBitmaps)
	{
		this.gameView=gameView;
		this.defenBitmap=defenBitmap;
		this.numberBitmaps=numberBitmaps;
		numberHeight=numberBitmaps[0].getWidth();
		numberWidth=numberBitmaps[0].getHeight();		
		textWidth=defenBitmap.getWidth();
		textHeight=defenBitmap.getHeight();
	}

	//绘制得分的方法
	public void drawSelf(Canvas canvas,Paint paint)
	{		
		//绘制数字
		drawNumberBitmap(this.score,numberBitmaps,x,y,canvas, paint);
		//绘制得分图片
		String numberStr=this.score+"";
		int textX=x-numberWidth*numberStr.length()-textWidth-space;
		int textY=y;
		canvas.drawBitmap(defenBitmap, textX, textY,paint);
	}
	//增加得分的方法
	public void addScore(int score)
	{
		this.score+=score;
	}
	public int getScore()
	{
		return score;
	}
	//画数字图片的方法
	public void drawNumberBitmap(int number,Bitmap[] numberBitmaps,float x,float y,Canvas canvas,Paint paint)
	{
		String numberStr=number+"";
		for(int i=0;i<numberStr.length();i++)
		{
			char c=numberStr.charAt(i);
			canvas.drawBitmap
			(
					numberBitmaps[c-'0'], 
					x-numberWidth*(numberStr.length()-i), 
					y, 
					paint
			);
		}
	}
}


