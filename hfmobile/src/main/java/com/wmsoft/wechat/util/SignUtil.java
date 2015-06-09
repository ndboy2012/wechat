package com.wmsoft.wechat.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 
 * 微信签证工具
 * @author Administrator
 *
 */
public class SignUtil {
    
	 private static String token = "Rav0PP";  
    /** 
     * 楠岃瘉绛惧悕 
     *  
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] {token, timestamp, nonce };  
        // 灏唗oken銆乼imestamp銆乶once涓変釜鍙傛暟杩涜瀛楀吀搴忔帓搴? 
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 灏嗕笁涓弬鏁板瓧绗︿覆鎷兼帴鎴愪竴涓瓧绗︿覆杩涜sha1鍔犲瘑  
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;  
        // 灏唖ha1鍔犲瘑鍚庣殑瀛楃涓插彲涓巗ignature瀵规瘮锛屾爣璇嗚璇锋眰鏉ユ簮浜庡井淇? 
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
  
    /** 
     * 灏嗗瓧鑺傛暟缁勮浆鎹负鍗佸叚杩涘埗瀛楃涓?
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 灏嗗瓧鑺傝浆鎹负鍗佸叚杩涘埗瀛楃涓?
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
 }
