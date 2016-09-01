package com.smashdishes.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Explosion {
	GameView gameView;
	private Bitmap[] bitmaps;//λͼ	
	float x;//x����λ��
	float y;//y����λ��	
	private int aniIndex=0;//��ը����֡����
	public Explosion(GameView gameView,Bitmap[] bitmaps,float x,float y)
	{
		this.gameView=gameView;
		this.bitmaps=bitmaps;
		this.x=x;
		this.y=y;
	}

	public void drawSelf(Canvas canvas, Paint paint) {
	
		canvas.drawBitmap(bitmaps[aniIndex], x, y,paint);
		if(aniIndex<bitmaps.length-1)//���û�в�����
		{
			aniIndex++;
		}
		else//�����ը�������
		{
			gameView.alExplosion.remove(this);//���б���ɾ���˱�ը
		}
		
	}
}