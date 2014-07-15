package com.gdmc.trivia.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	
	protected BaseApplication mApplication;
	protected ProgressDialog dialog;
	protected static SharedPreferences mSharedPreferences;
	protected static Editor mEditor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mApplication = (BaseApplication)getApplication();
		mSharedPreferences= this.getSharedPreferences("Users", MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	protected abstract void initViews();
	
	protected abstract void initEvents();
	
	protected abstract void initData();
	
	protected String getAccount(){
		String mAccount = mSharedPreferences.getString("Account", null);
		return mAccount;
	}
	
	protected String getPassword(){
		String mPassword = mSharedPreferences.getString("Password", null);
		return mPassword;
	}
	
	public static void setAccount(String mAccount){
		mEditor.putString("Account", mAccount);
		mEditor.commit();
	}
	
	public static void setPassword(String mPassword){
		mEditor.putString("Password", mPassword);
		mEditor.commit();
	}
	
	/** 显示自定义Toast提示(来自String) **/
	protected void showCustomToast(String text) {
		Toast.makeText(BaseActivity.this, text, Toast.LENGTH_LONG).show();
	}
	
	protected void showLoadingDialog(String text) {
		dialog = ProgressDialog.show(BaseActivity.this, "",
				text, true);
	}

	protected void dismissLoadingDialog() {
		dialog.dismiss();
	}
	
}
