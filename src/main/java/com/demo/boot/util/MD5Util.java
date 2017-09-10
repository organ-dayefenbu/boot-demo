package com.demo.boot.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String TIANAN_SECRET="yo938djfalaqingsonguy57asqplcirm";
	
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * md5加密
	 * 
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}
		try {
			msgDigest.update(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");
		}
		byte[] bytes = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}
	
	
	public static String tianAnEncrypt(String st,String token){
		return MD5Util.md5(TIANAN_SECRET+st+token);
	}
	
	private static String KEY = "um";
	private static String sysToken_key="sysToken";

	/**
	 * 获取加密后的密码
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Password(String str) {
		return MD5Util.md5(str + KEY);
	}
	
	
	public static String getSysTokenMd5(String st){
		return MD5Util.md5(st + sysToken_key);
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5Password("80413"));
	}
}
