package com.gdmc.trivia.android.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gdmc.trivia.android.BaseActivity;
import com.gdmc.trivia.android.R;
import com.gdmc.trivia.android.util.HttpUtils;
import com.gdmc.trivia.android.util.ServiceUtils;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private EditText mEtAccount;
	private EditText mEtPassword;
	private EditText mEtEmail;
	private Button   mBtRegister;
	private LinearLayout   mLayoutBack;
	private String mStrAccount;
	private String mStrPwd ;
	private String mStrEmail ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initViews();
		initEvents();
		initData();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mEtAccount = (EditText)findViewById(R.id.register_et_account);
		mEtPassword = (EditText)findViewById(R.id.register_et_pwd);
		mEtEmail = (EditText)findViewById(R.id.register_et_email);
		mBtRegister = (Button)findViewById(R.id.register_bt_register);
		mLayoutBack = (LinearLayout)findViewById(R.id.register_back);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mBtRegister.setOnClickListener(this);
		mLayoutBack.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_bt_register:
			showLoadingDialog("����ע����");
			mStrAccount = mEtAccount.getText().toString().trim();
			mStrPwd = mEtPassword.getText().toString().trim();
			mStrEmail = mEtEmail.getText().toString().trim();
			if (!mStrAccount.equals("") && !mStrPwd.equals("") && !mStrEmail.equals("")) {
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... params) {
						// TODO Auto-generated method stub
						try {
							String url = ServiceUtils.urlGetNonce;
							String jsonString = HttpUtils.get(url, null);
							if (jsonString.equals("")) {
								
								return "";
							}
							JSONObject jsonObject = new JSONObject(jsonString);
							if (jsonObject.getString("status").equals("ok")) {
								return Register(jsonObject.getString("nonce"));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "";
						}
						return "";
					}

					@Override
					protected void onPostExecute(String result) {
						// TODO Auto-generated method stub
						super.onPostExecute(result);
						dismissLoadingDialog();
						if (result.equals("")) {
							showCustomToast("�������");
						}else {
							showCustomToast(result);
						}
					}
				}.execute();
			}else {
				showCustomToast("�˺Ż������벻��Ϊ��");
			}
			break;

		case R.id.register_back:
			finish();
			break;
			
		default:
			break;
		}
	}
	
	private Handler RegisterHandler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			finish();
		}
		
	};
	
	private String Register(String nonce){
		try {
			Map<String, String> params = new HashMap<String, String>();
			//u=����Ա�û���&p=����Ա����&user_login=�µ��û���&user_password=����&user_email=���û�����&nonce=����
			params.put("user_login", mStrAccount);
			params.put("user_password", mStrPwd);
			params.put("user_email", mStrEmail);
			params.put("nonce", nonce);
			String jsonString = HttpUtils.post(ServiceUtils.urlRegister, params);
			if (jsonString.equals("")) {
				return "";
			}
			JSONObject jsonObject = new JSONObject(jsonString);
			String status = jsonObject.getString("status");
			if (status.equals("ok")) {
				RegisterHandler.sendEmptyMessage(0);
				return "ע��ɹ�";
			}else{
				return  jsonObject.getString("error");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
