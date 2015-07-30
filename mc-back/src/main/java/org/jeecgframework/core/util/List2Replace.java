package org.jeecgframework.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * list转替换字符串，如：正常_1,失效_2
 * 作者 yelp
 * org.jeecgframework.core.util.List2Replace
 * 时间	2014-4-27
 */
public class List2Replace {

	/**
     * 方法描述:  值替换工具
     * 作    者： yelp
     * @param objList
     * @param perFieldName
     * @param sufFieldName
     * @return 格式：old_new,old2_new2
     * 返回类型： String
     */
	public static String listToReplaceStr(List<?> objList, String perFieldName, String sufFieldName){
		List<String> strList = new ArrayList<String>();
		for (Object object : objList) {
			String perStr = null;
			String sufStr = null;
			try {
				perStr = (String)PropertyUtils.getProperty(object, perFieldName);
				sufStr = (String)PropertyUtils.getProperty(object, sufFieldName);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			strList.add(perStr + "_" +sufStr);
		}
		return StringUtils.join(strList, ",");
	}
	public static String listToReplaceStr(List<Object> objList){
		List<Object> strList = new ArrayList<Object>();
		for(Object str:objList){
			strList.add(str);
		}
		return StringUtils.join(strList,",");
	}
	/**
     * 方法描述:  值替换工具
     * 作    者：yelp
     * @param objList
     * @param perFieldName
     * @param sufFieldName
     * @return 格式：old_new,old2_new2
     * 返回类型： String
     */
	public static String listToPercentReplaceStr(List<?> objList, String perFieldName, String sufFieldName){
		List<String> strList = new ArrayList<String>();
		for (Object object : objList) {
			String perStr = null;
			String sufStr = null;
			try {
				perStr = (String)PropertyUtils.getProperty(object, perFieldName);
				sufStr = (String)PropertyUtils.getProperty(object, sufFieldName);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			strList.add(perStr + "%" +sufStr);
		}
		return StringUtils.join(strList, ",");
	}
}
