package org.yelp.utils;

import java.util.Random;

public class GenerateRandCode {
	/*
     * 产生随机验证码
     */
	public GenerateRandCode() {
	}
	
	public static String  generateCode() {
	int[] array = {0,1,2,3,4,5,6,7,8,9};
	Random rand = new Random();
	for (int i = 10; i > 1; i--) {
	    int index = rand.nextInt(i);
	    int tmp = array[index];
	    array[index] = array[i - 1];
	    array[i - 1] = tmp;
	}
	int result = 0;
	for(int i = 0; i < 6; i++)
	    result = result * 10 + array[i];
	return  String.valueOf(result);
	}
	
	/*
	 * 产生随机数
	 */
	public static int generateNum(int maxNum) {
		 Random rand = new Random();
	     //System.out.println(rand.nextInt(maxNum)+1);
	     return rand.nextInt(maxNum)+1;
		
	}
	
	/*
	 * 随机字符串码的生成
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
}
