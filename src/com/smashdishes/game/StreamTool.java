package com.smashdishes.game;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
	
	
	/**
	 * 
	 * @param is
	 * @return
	 */
	public static String readInputStream(InputStream is){
		try {
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer, 0, len);
			}
			is.close();
			bos.close();
			byte []result=bos.toByteArray();
			String temp=new String(result);
			if(temp.contains("utf-8")){
				return temp;
			}else if(temp.contains("gb2312")){
				return new String(result,"utf-8");
			}
			
			return temp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}
}
