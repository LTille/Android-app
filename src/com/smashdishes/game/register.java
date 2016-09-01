package com.smashdishes.game;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends BaseActivity{

    Button buttonregister=null;
    Button buttoncancel=null;
    EditText  register_username=null; 
    EditText  register_password=null; 
    EditText  register_email=null; 
    
    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.register);
		buttonregister=(Button)findViewById(R.id.buttonregister);
		buttoncancel=(Button)findViewById(R.id.buttoncancel);
		register_username=(EditText)findViewById(R.id.register_username);
		register_password=(EditText)findViewById(R.id.register_password);
		register_email=(EditText)findViewById(R.id.register_email);
		
		
		
		buttonregister.setOnClickListener(new OnClickListener()
		{
			
			
			
			public void onClick(View v)
			{
				Toast.makeText(getApplicationContext(), "sasa", 1).show();
				final String username=register_username.getText().toString().trim();
				final String password=register_password.getText().toString().trim();
				final String email=register_email.getText().toString().trim();
				
				new Thread(){
					@Override
					public void run() {
						String result=registerAndLogin.regisiter(username, password, email);
						if(result!=null){
							runOnUiThread(new Runnable() {
								public void run() {
									Toast.makeText(getApplicationContext(), "×¢²á³É¹¦", Toast.LENGTH_LONG).show();
									Intent intent=new Intent(register.this, LogActivity.class);
									startActivity(intent);
								}
							});
						}else{
							runOnUiThread(new Runnable() {
								public void run() {
									Toast.makeText(getApplicationContext(), "×¢²áÊ§°Ü", Toast.LENGTH_LONG).show();
								}
							});
						}
					}
				}.start();
			}
		});
		
	
		
		
		buttoncancel.setOnClickListener(new View.OnClickListener()
		    {
		    	
		    
			    @Override
			    public void onClick(View v)
			    {
			       new AlertDialog.Builder(register.this) 
				   .setTitle("System Prompt")
				   .setMessage("Are you sure to give up register?")
				   .setPositiveButton("Yes", new DialogInterface.OnClickListener()
				     {
					    @Override
					    public void onClick(DialogInterface dialog, int which)
					    {
							register.this.finish(); 
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

	
	



