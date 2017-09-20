package com.myErp.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	public static boolean isIP(String str) {
		String num = "(25[0-5]|2[0-4]//d|[0-1]//d{2}|[1-9]?//d)";
		String regex = "^" + num + "//." + num + "//." + num + "//." + num + "$";
		return match(regex, str);
	}

	public static boolean IsUrl(String str) {
		String regex = "http(s)?://([//w-]+//.)+[//w-]+(/[//w- ./?%&=]*)?";
		return match(regex, str);
	}

	public static boolean IsTelephone(String str) {
		String regex = "^(http://www.ccc.com/yaojian/admin/file://d%7b3,4%7d-)/?//d{6,8}$";
		return match(regex, str);
	}

	public static boolean IsPassword(String str) {
		String regex = "[A-Za-z]+[0-9]";
		return match(regex, str);
	}

	public static boolean IsPasswLength(String str) {
		String regex = "^//d{6,18}$";
		return match(regex, str);
	}

	public static boolean IsPostalcode(String str) {
		String regex = "^//d{6}$";
		return match(regex, str);
	}

	public static boolean IsHandset(String str) {
		String regex = "^[1]+[3,5]+//d{9}$";
		return match(regex, str);
	}

	public static boolean IsIDcard(String str) {
		String regex = "(^//d{18}$)|(^//d{15}$)";
		return match(regex, str);
	}

	public static boolean IsDecimal(String str) {
		String regex = "^[0-9]+(.[0-9]{2})?$";
		return match(regex, str);
	}

	public static boolean IsMonth(String str) {
		String regex = "^(0?[[1-9]|1[0-2])$";
		return match(regex, str);
	}

	public static boolean IsDay(String str) {
		String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
		return match(regex, str);
	}

	public static boolean isDate(String str) {
		String regex = "^((((1[6-9]|[2-9]//d)//d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]//d|3[01]))|(((1[6-9]|[2-9]//d)//d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]//d|30))|(((1[6-9]|[2-9]//d)//d{2})-0?2-(0?[1-9]|1//d|2[0-8]))|(((1[6-9]|[2-9]//d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?//d):[0-5]?//d:[0-5]?//d$";
		return match(regex, str);
	}

	public static boolean IsNumber(String str) {
		String regex = "^[0-9]*$";
		return match(regex, str);
	}

	public static boolean IsIntNumber(String str) {
		String regex = "^//+?[1-9][0-9]*$";
		return match(regex, str);
	}

	public static boolean IsUpChar(String str) {
		String regex = "^[A-Z]+$";
		return match(regex, str);
	}

	public static boolean IsLowChar(String str) {
		String regex = "^[a-z]+$";
		return match(regex, str);
	}

	public static boolean IsLetter(String str) {
		String regex = "^[A-Za-z]+$";
		return match(regex, str);
	}

	public static boolean IsChinese(String str) {
		String regex = "^[/u4e00-/u9fa5],{0,}$";
		return match(regex, str);
	}

	public static boolean IsLength(String str) {
		String regex = "^.{8,}$";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static boolean checkPasswordStrong(String password) {
		if ((password == null) || (password.trim().isEmpty())) {
			return false;
		}
		try {
			String pwdRegex = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})";
			Pattern pattern = Pattern.compile(pwdRegex);
			Matcher matcher = pattern.matcher(password);

			return matcher.matches();
		} catch (Exception localException) {
		}
		return true;
	}

	/**
	 * 获取随机码
	 * 
	 * @return
	 */
	public static String GetRandom() {
		int max = 1000;
		int min = 2000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return String.valueOf(s);
	}
}
