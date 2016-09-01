package com.smashdishes.game;


import android.os.Bundle;
import android.app.AlertDialog;
import android.view. View;
import android.view.Window;
import android.view.WindowManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.view. View.OnClickListener;



public class menu extends BaseActivity 
{
    Button button1=null;
    Button button2=null;
    Button button3=null;
    Button button4=null;
   
    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.menu);
		button1=(Button)findViewById(R.id.bt1);
		button2=(Button)findViewById(R.id.bt2);
		button3=(Button)findViewById(R.id.bt3);
		button4=(Button)findViewById(R.id.bt4);
		
		button1.setOnClickListener(new OnClickListener()
		{
			@Override
			
			public void onClick(View v)
			{
				Intent intent= new Intent();
				intent.setClass(menu.this, DishActivity.class );
				startActivity(intent);
			}
		});
		
		button2.setOnClickListener(new OnClickListener()
		{
			@Override
			
			public void onClick(View v)
			{
				Intent connect= new Intent();
				connect.setClass(menu.this, introduction.class );
				startActivity(connect);
			}
		});
		
		button3.setOnClickListener(new OnClickListener()
		{
			@Override
			
			public void onClick(View v)
			{
				Intent connect= new Intent();
				connect.setClass(menu.this, rankinglist.class );
				startActivity(connect);
			}
		});
		  button4.setOnClickListener(new View.OnClickListener()
	    {
	    	
	    
		    @Override
		    public void onClick(View v)
		    {
		       new AlertDialog.Builder(menu.this) 
			   .setTitle("System Prompt")
			   .setMessage("Are you sure to exit game?")
			   .setPositiveButton("Yes", new DialogInterface.OnClickListener()
			     {
				    @Override
				    public void onClick(DialogInterface dialog, int which)
				    {
						//menu.this.finish(); 
				    	//android.os.Process.killProcess(android.os.Process.myPid());
				    	exit();

					 }
			     })
			
		       .setNegativeButton("No", new DialogInterface.OnClickListener()
			     {
				    @Override	
				    public void onClick (DialogInterface dialog, int which)
				    {
					
				    }
			}).create().show();
			
         }
		    
	  });
		
	}
		
	}

	
	