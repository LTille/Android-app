package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * 
 *��ʱ����
 *
 */
public class Timer {
	GameView gameView;
	private final int totalSecond=90;
	private Bitmap colonBitmap;
	private Bitmap[] numberBitmaps;	
	private int leftSecond=totalSecond;
	int x=Constant.Timer_X;//���ֵ����ϵ�����
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
	
	//����ʱ��ķ���
	public void drawSelf(Canvas canvas,Paint paint)
	{
		int second=this.leftSecond%60;
		int minute=(int)Math.floor(this.leftSecond/60);		
		
		drawNumberBitmap(second,numberBitmaps,x,y,canvas, paint);
		//���Ʒָ���
		int secondLength=(second+"").length()<=1 ? (second+"").length()+1 : (second+"").length();
		int colonX=x-secondLength*numberWidth-colonWidth;
		int colonY=y;
		canvas.drawBitmap(colonBitmap, colonX, colonY,paint);//����ʱ��ָ���ͼƬ
		//���Ʒ���
		int miniteEndX=colonX;
		int miniteEndY=y;
		drawNumberBitmap(minute,numberBitmaps,miniteEndX,miniteEndY,canvas, paint);
	}
	//����ʱ��ķ���
	public void subtractTime(int second)
	{
		if(this.leftSecond>0)
		{
			this.leftSecond-=second;
		}
		else//���ʱ��Ϊ0��������Ϸ
		{
			gameView.overGame();
		}
	}
	//������ͼƬ�ķ���
	public void drawNumberBitmap(int number,Bitmap[] numberBitmaps,float endX,float endY,Canvas canvas,Paint paint)
	{
		String numberStr=number+"";
		if(number<10){//��֤��������λ
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

