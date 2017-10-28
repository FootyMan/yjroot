package com.api.utils;

public class EncryUtil {

	 /** 
     * 使用默认密钥进行DES加密 
     */  
    public static String encrypt(String plainText) {  
        try {  
            return new Hex().encrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
      
    /** 
     * 使用指定密钥进行DES解密 
     */  
    public static String encrypt(String plainText, String key) {  
        try {  
            return new Hex(key).encrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
      
  
    /** 
     * 使用默认密钥进行DES解密 
     */  
    public static String decrypt(String plainText) {  
        try {  
            return new Hex().decrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
      
    /** 
     * 使用指定密钥进行DES解密 
     */  
    public static String decrypt(String plainText, String key) {  
        try {  
            return new Hex(key).decrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
}
