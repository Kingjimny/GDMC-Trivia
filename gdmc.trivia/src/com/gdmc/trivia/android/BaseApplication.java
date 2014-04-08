package com.gdmc.trivia.android;

import org.apache.http.client.CookieStore;

import android.app.Application;

public class BaseApplication extends Application {

	public static CookieStore cookies; //����
	
	public  static CookieStore getCookie(){   
		return cookies;
	}
	public static  void setCookie(CookieStore cks){
		cookies = cks;
	}
}
