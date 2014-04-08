package com.gdmc.trivia.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.gdmc.trivia.android.BaseActivity;
import com.gdmc.trivia.android.R;
import com.gdmc.trivia.android.adapter.MainCardAdapter;
import com.gdmc.trivia.android.entity.Card;
import com.gdmc.trivia.android.util.JsonResolveUtils;
import com.gdmc.trivia.android.view.RefreshListView;
import com.gdmc.trivia.android.view.RefreshListView.OnCancelListener;
import com.gdmc.trivia.android.view.RefreshListView.OnRefreshListener;

public class MainActivity extends BaseActivity implements OnClickListener,
                OnRefreshListener, OnCancelListener{

	private LinearLayout mLayoutBack;
	private LinearLayout mLayoutCreate;
	private LinearLayout mLayoutMore;
	private RefreshListView mRlvList;
	private MainCardAdapter mAdapter;
	private List<Card> mCards;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		initData();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mLayoutBack = (LinearLayout)findViewById(R.id.main_back);
		mLayoutCreate = (LinearLayout)findViewById(R.id.main_create);
		mLayoutMore = (LinearLayout)findViewById(R.id.main_more);
		mRlvList = (RefreshListView)findViewById(R.id.main_rlv_list);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mLayoutBack.setOnClickListener(this);
		mRlvList.setOnCancelListener(this);
		mRlvList.setOnRefreshListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		getCard();
	}
	
	private void getCard(){
		new AsyncTask<Void, Void, Boolean>() {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				showLoadingDialog("正在加载");
			}
			
			@Override
			protected Boolean doInBackground(Void... params) {
				// TODO Auto-generated method stub
				mCards = new ArrayList<Card>();
				return JsonResolveUtils.ResolveMainCard(MainActivity.this, mCards);
			}

			@Override
			protected void onPostExecute(Boolean result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				dismissLoadingDialog();
				System.out.println("result="+result);
				if (result) {
					mAdapter = new MainCardAdapter(MainActivity.this, mCards);
					mRlvList.setAdapter(mAdapter);
					mRlvList.onRefreshComplete();
				}else {
					showCustomToast("加载失败");
				}
			}

		}.execute();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

}
