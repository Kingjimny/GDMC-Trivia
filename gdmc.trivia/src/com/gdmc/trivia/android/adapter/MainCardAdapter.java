package com.gdmc.trivia.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gdmc.trivia.android.R;
import com.gdmc.trivia.android.entity.Card;

public class MainCardAdapter extends BaseAdapter {
	
	protected LayoutInflater mInflater;
	private Context mContext;
	private List<Card> mCards;
	
	
	public MainCardAdapter(Context context,List<Card> mCards){
		this.mContext = context;
		this.mCards = mCards;
		mInflater = LayoutInflater.from(context);
		System.out.println("into adapter");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_main_secret, null);
			holder = new ViewHolder();
			holder.content = (TextView)convertView
					.findViewById(R.id.item_main_content);
			holder.likeCount = (TextView)convertView
					.findViewById(R.id.item_main_like);
			holder.commentCount = (TextView)convertView
					.findViewById(R.id.item_main_comment);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Card card = mCards.get(position);
		holder.content.setText(card.getContent());
		System.out.println("content="+card.getContent());
		holder.likeCount.setText(card.getLikeCount());
		System.out.println("like="+card.getLikeCount());
		holder.commentCount.setText(card.getCommentCount());
		System.out.println("comment="+card.getCommentCount());
		return convertView;
	}

	class ViewHolder{
		TextView content;
		TextView likeCount;
		TextView commentCount;
	}
	
	@Override
	public int getCount() {
		return mCards.size();
	}

	@Override
	public Object getItem(int position) {
		return mCards.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
