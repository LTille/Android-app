package com.smashdishes.game;

public class CountTimeThread extends Thread{
	
	GameView gameView;
	private boolean flag=true;
	private int sleepSpan=1000;
	boolean isGameOn;
	
	public CountTimeThread(GameView gameView){
		this.gameView=gameView;
		isGameOn=true;
	}

	@Override
	public void run(){
	
		while(flag){
			while(isGameOn){
		
			try{
				Thread.sleep(sleepSpan);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
			gameView.timer.subtractTime(1);
		
		}
		}
	}

	public void setFlag(boolean flag) {
		
		this.flag=flag;
	}
	

}
