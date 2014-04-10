package com.gdmc.trivia.android.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
	
	private String content ;
	private String likeCount;
	private String commentCount;
	
	public Card(){
		super();
	}
	
	public Card(String content,String likeCount,String commentCount){
		this.content = content;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(content);
		dest.writeString(likeCount);
		dest.writeString(commentCount);

	}

	public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {

		@Override
		public Card createFromParcel(Parcel source) {
			Card card = new Card();
			card.setContent(source.readString());
			card.setCommentCount(source.readString());
			card.setLikeCount(source.readString());
			return card;
		}

		@Override
		public Card[] newArray(int size) {
			return new Card[size];
		}
	};
	
	
}
