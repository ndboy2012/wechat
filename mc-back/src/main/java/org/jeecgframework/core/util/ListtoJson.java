package org.jeecgframework.core.util;

import java.util.List;



public class ListtoJson {

	public static String getJsonData(@SuppressWarnings("rawtypes") List list) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");


		buffer.append("]");

		// 将,\n]替换成\n]

		String tmp = buffer.toString();

		tmp = tmp.replaceAll(",\n]", "\n]");

		return tmp;

	}

	static int count = 0;

	

}
