package com.smashdishes.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.view. View.OnClickListener;



public class LogActivity extends BaseActivity{ 
	
			
		    Button buttonlog=null;
		    Button buttonExit=null;
		    Button buttonR=null;		   
		    EditText login_username=null;		    
		    EditText login_password=null;		    
			@Override
			public void onCreate(Bundle savedInstanceState) 
			{
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
		        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , 
		                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
				setContentView(R.layout.log);
				buttonlog=(Button)findViewById(R.id.buttonlog);
				buttonExit=(Button)findViewById(R.id.buttonExit);			    
				buttonR=(Button)findViewById(R.id.buttonR);
				login_username=(EditText)findViewById(R.id.login_username);
				login_password=(EditText)findViewById(R.id.login_password);
				buttonlog.setOnClickListener(new OnClickListener()
				{
					@Override
					
					public void onClick(View v)
					{
						
						final String username=login_username.getText().toString().trim();
						final String password=login_password.getText().toString().trim();
						
						new Thread(){
							@Override
							public void run() {
								String result=registerAndLogin.login(username, password);
								if(result.equals("success")){
									runOnUiThread(new Runnable() {
										public void run() {
											Toast.makeText(getApplicationContext(), "µÇÂ½³É¹¦", Toast.LENGTH_LONG).show();
											Intent intent=new Intent(LogActivity.this, menu.class);
											startActivity(intent);
										}
									});
								}else{
									runOnUiThread(new Runnable() {
										public void run() {
											Toast.makeText(getApplicationContext(), "µÇÂ½Ê§°Ü£¬ÓÃ»§Ãû»òÃÜÂë´íÎó", Toast.LENGTH_LONG).show();
										}
									});
								}
							}
						}.start();
						
						
						
						
//						
//						Intent intent= new Intent();
//						intent.setClass(LogActivity.this, menu.class );
//					    startActivity(intent);
					}
				});
				
				buttonR.setOnClickListener(new OnClickListener()
				{
					@Override
					
					public void onClick(View v)
					{
						Intent intent= new Intent();
						intent.setClass(LogActivity.this, register.class );
						startActivity(intent);
						
					}
				});
								
												
				buttonExit.setOnClickListener(new View.OnClickListener()
				    {
				    	
				    
					    @Override
					    public void onClick(View v)
					    {
					       new AlertDialog.Builder(LogActivity.this) 
						   .setTitle("System Prompt")
						   .setMessage("Are you sure to quit the game?")
						   .setPositiveButton("Yes", new DialogInterface.OnClickListener()
						     {
							    @Override
							   public void onClick(DialogInterface dialog, int which)
							 {
							
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

			
			