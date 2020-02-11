package com.mubawab.tech.core;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

public class DataLoader {

	private static final Gson gson = new Gson();

	public static List loadDataList(String dataSource, Type collectionType) {
		return (List) gson.fromJson(new InputStreamReader(DataLoader.class.getResourceAsStream(dataSource)),
				collectionType);

	}

	public static Object loadDataObject(String dataSource, Class targetClass) {
		return gson.fromJson(new InputStreamReader(DataLoader.class.getResourceAsStream(dataSource)), targetClass);

	}

}
