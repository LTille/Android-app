package com.smashdishes.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{
	private boolean flag = true;	
	private int sleepSpan = 100;
	GameView gameView;
	SurfaceHolder surfaceHolder;
	public boolean isViewOn;
	
	public DrawThread(GameView gameView) {
		this.gameView = gameView;
		this.surfaceHolder = gameView.getHolder();
	}

	public void run(){
		Canvas c;
        while (this.flag) {
        	while(isViewOn){
            c = null;
            try {
                c = this.surfaceHolder.lockCanvas(null);
                synchronized (this.surfaceHolder){ 
                	gameView.Draw(c);}
                
            }finally {
                if (c != null) {
                    this.surfaceHolder.unlockCanvasAndPost(c);
                }
            }
            try{
            	Thread.sleep(sleepSpan);
            }
            catch(Exception e){
            	e.printStackTrace();
            }
        }
        }
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
