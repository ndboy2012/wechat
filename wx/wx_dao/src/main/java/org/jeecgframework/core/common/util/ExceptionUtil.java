package org.jeecgframework.core.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	/**
	 * ���ش�����Ϣ�ַ�
	 * 
	 * @param ex
	 *            Exception
	 * @return ������Ϣ�ַ�
	 */
	public static String getExceptionMessage(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
}
