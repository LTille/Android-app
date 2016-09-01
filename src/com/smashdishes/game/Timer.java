package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * 
 *计时器类
 *
 */
public class Timer {
	GameView gameView;
	private final int totalSecond=90;
	private Bitmap colonBitmap;
	private Bitmap[] numberBitmaps;	
	private int leftSecond=totalSecond;
	int x=Constant.Timer_X;//数字的右上点坐标
	int y=Constant.Timer_Y;
	int numberWidth;
	int numberHeight;
	int colonWidth;
	int colonHeight;
	public Timer(GameView gameView,Bitmap colonBitmap,Bitmap[] numberBitmaps)
	{
		this.gameView=gameView;
		this.colonBitmap=colonBitmap;
		this.numberBitmaps=numberBitmaps;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		colonWidth=colonBitmap.getWidth();
		colonHeight=colonBitmap.getHeight();
	}
	
	//绘制时间的方法
	public void drawSelf(Canvas canvas,Paint paint)
	{
		int second=this.leftSecond%60;
		int minute=(int)Math.floor(this.leftSecond/60);		
		
		drawNumberBitmap(second,numberBitmaps,x,y,canvas, paint);
		//绘制分隔符
		int secondLength=(second+"").length()<=1 ? (second+"").length()+1 : (second+"").length();
		int colonX=x-secondLength*numberWidth-colonWidth;
		int colonY=y;
		canvas.drawBitmap(colonBitmap, colonX, colonY,paint);//绘制时间分隔符图片
		//绘制分钟
		int miniteEndX=colonX;
		int miniteEndY=y;
		drawNumberBitmap(minute,numberBitmaps,miniteEndX,miniteEndY,canvas, paint);
	}
	//减少时间的方法
	public void subtractTime(int second)
	{
		if(this.leftSecond>0)
		{
			this.leftSecond-=second;
		}
		else//如果时间为0，结束游戏
		{
			gameView.overGame();
		}
	}
	//画数字图片的方法
	public void drawNumberBitmap(int number,Bitmap[] numberBitmaps,float endX,float endY,Canvas canvas,Paint paint)
	{
		String numberStr=number+"";
		if(number<10){//保证至少有两位
			numberStr="0"+numberStr;
		}
		for(int i=0;i<numberStr.length();i++)
		{
			char c=numberStr.charAt(i);
			canvas.drawBitmap
			(
					numberBitmaps[c-'0'], 
					endX-numberWidth*(numberStr.length()-i), 
					endY, 
					paint
			);
		}
	}
}

