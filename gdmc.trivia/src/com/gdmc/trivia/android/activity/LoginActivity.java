package com.gdmc.trivia.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.gdmc.trivia.android.BaseActivity;
import com.gdmc.trivia.android.R;
import com.gdmc.trivia.android.util.HttpUtils;
import com.gdmc.trivia.android.util.ServiceUtils;

public class LoginActivity extends BaseActivity implements OnClickListener{

	private EditText mEtAccount;
	private EditText mEtPassword;
	private Button   mBtLogin;
	private Button   mBtRegister;
	private String   mAccount ;
	private String   mPassword ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
		initEvents();
		initData();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mEtAccount = (EditText)findViewById(R.id.login_et_account);
		mEtPassword = (EditText)findViewById(R.id.login_et_pwd);
		mBtLogin = (Button)findViewById(R.id.login_bt_login);
		mBtRegister = (Button)findViewById(R.id.login_bt_register);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mBtLogin.setOnClickListener(this);
		mBtRegister.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		getAccountPwd();
	}
	
	private void getAccountPwd(){
		String account = getAccount();
		String password = getPassword();
		if (account!=null && password!=null) {
			mEtAccount.setText(account);
			mEtPassword.setText(password);
		}
	}
	
	private void setAccountPwd(String account,String pwd){
		setAccount(account);
		setPassword(pwd);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		//µÇÂ¼
		case R.id.login_bt_login:
			showLoadingDialog("ÕýÔÚµÇÂ¼");
			mAccount = mEtAccount.getText().toString().trim();
			mPassword = mEtPassword.getText().toString().trim();
			if (!mAccount.equals("") && !mPassword.equals("")) {
				//user_login=ÓÃ»§Ãû&user_password=ÃÜÂë
				setAccountPwd(mAccount,mPassword);
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... arg0) {
						// TODO Auto-generated method stub
						String url = ServiceUtils.urlLogin +"&user_login=" + mAccount + 
								"&user_password=" + mPassword;
						String loginString = HttpUtils.post(url, null);
						if (loginString.equals("")) {
							return "ÍøÂç³ö´í";
						}
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(loginString);
							String status = jsonObject.getString("status");
							if (status.equals("ok")) {
								//intent
								Intent intent = new Intent(LoginActivity.this,MainActivity.class);
								startActivity(intent);
								System.out.println("ok");
							}else {
								return "ÕËºÅ»òÕßÃÜÂë´íÎó";
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "ÍøÂç³ö´í";
						}
						return "";
					}

					@Override
					protected void onPostExecute(String result) {
						// TODO Auto-generated method stub
						super.onPostExecute(result);
						if (!result.equals("")) {
							showCustomToast(result);
						}
						dismissLoadingDialog();
					}
					
				}.execute();
			}else {
				showCustomToast("ÕËºÅ»òÕßÃÜÂë²»ÄÜÎª¿Õ");
			}
			
			break;
			
		//×¢²á
		case R.id.login_bt_register:
			Intent intent  = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		
		default:
			break;
		}
	}

}
