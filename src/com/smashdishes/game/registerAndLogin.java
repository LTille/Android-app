package com.smashdishes.game;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class registerAndLogin {
	
	public static String regisiter(String username,String password,String email){
		try {
			String uri="http://192.168.1.100:8080/Action/userAction?actionType=register&username="
					+username+"&password="+password+"&email="+email;
			URL url=new URL(uri);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int code=con.getResponseCode();
			if(code==200){
				 InputStream in=con.getInputStream();
				 String info=StreamTool.readInputStream(in);
				 if(info.equals("success")){
					return "success";
				 }else{
					 return "error";
				 }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static String login(String username,String password){
		try {
			String uri="http://192.168.1.100:8080/Action/userAction?actionType=login&username="
					+username+"&password="+password;
			URL url=new URL(uri);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int code=con.getResponseCode();
			if(code==200){
				 InputStream in=con.getInputStream();
				 String info=StreamTool.readInputStream(in);
				 if(info.equals("success")){
					return "success";
				 }else{
					 return null;
				 }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
