package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class CountScore {
	GameView gameView;
	final int space=5;//���ֺ��������ľ���
	private Bitmap defenBitmap;//�÷�λͼ
	private Bitmap[] numberBitmaps;	//����λͼ
	private int score=0;
	int x=Constant.Score_X;//���ֵ����ϵ�����
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

	//���Ƶ÷ֵķ���
	public void drawSelf(Canvas canvas,Paint paint)
	{		
		//��������
		drawNumberBitmap(this.score,numberBitmaps,x,y,canvas, paint);
		//���Ƶ÷�ͼƬ
		String numberStr=this.score+"";
		int textX=x-numberWidth*numberStr.length()-textWidth-space;
		int textY=y;
		canvas.drawBitmap(defenBitmap, textX, textY,paint);
	}
	//���ӵ÷ֵķ���
	public void addScore(int score)
	{
		this.score+=score;
	}
	public int getScore()
	{
		return score;
	}
	//������ͼƬ�ķ���
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


