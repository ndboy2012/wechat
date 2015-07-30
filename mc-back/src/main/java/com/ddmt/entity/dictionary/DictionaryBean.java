package com.ddmt.entity.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryBean {
	public static Map<String, List<DictionaryEntity>> allDicts = new HashMap<String, List<DictionaryEntity>>();
	public static Map<String, DictionaryEntity> keyDict = new HashMap<String, DictionaryEntity>();
}
