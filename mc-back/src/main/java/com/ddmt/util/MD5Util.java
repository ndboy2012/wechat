package com.ddmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName()
					+ "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	public static String getFileMD5String(File file) throws IOException {
		String hex;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
					0, file.length());
			messagedigest.update(byteBuffer);
			hex = bufferToHex(messagedigest.digest());
		} finally{
			if (in!=null){
				in.close();
			}
		}
		return hex;
	}

	public static String getMD5String(String s) {
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(s.getBytes("UTF-8"));
			byte[] digest = md5.digest();

			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				// byteVar &
				// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
				// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
				// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
				strTemp = Integer.toHexString(
						(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
				return hexString.toString();
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		// 2EA3E66AC37DF7610F5BD322EC4FFE48 670M 11s kuri双核1.66G 2G内存
		File big = new File("d:\\upload\\09.jpg");
		String md5 = getFileMD5String(big);
		long end = System.currentTimeMillis();
		System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000)
				+ "s");
	}
}
