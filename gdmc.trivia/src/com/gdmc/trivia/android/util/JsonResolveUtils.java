package com.gdmc.trivia.android.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gdmc.trivia.android.entity.Card;

import android.content.Context;

public class JsonResolveUtils {

	public static Boolean ResolveMainCard(Context context,List<Card> cards){
		try {
			String jsonString = HttpUtils.post(ServiceUtils.urlALLSecret, null);
//			System.out.println("jsonString-allsecret== "+jsonString);
			if (jsonString.equals("") || jsonString ==null) {
				return false;
			}
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("posts");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.getString("content");
				content.replaceAll("<p>", "");
				content.replaceAll("</p>", "");
//				String likecount = object.getString("");
				String likecount = "199";
				String commentcount = object.getString("comment_count");
				cards.add(new Card(content,likecount,commentcount));
				System.out.println("一次次次次次次");
			}
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
