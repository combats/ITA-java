package com.softserveinc.ita;


import com.google.gson.Gson;
import com.softserveinc.ita.utils.JsonUtil;

public class JsonUtilGsonImpl implements JsonUtil {
	private Gson gson = new Gson();
	@Override
	public <T> T fromJson(String s, Class<T> classOfT) {
		return gson.fromJson(s, classOfT);
	}

	@Override
	public String toJson(Object o) {
		return gson.toJson(o);
	}
}
