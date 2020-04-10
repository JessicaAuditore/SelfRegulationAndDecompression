package com.limitofsoul.utils;

/**
 * 判断字符串非空
 */
public class StringUtil {

	public static boolean isEmpty(Object str) {
		if (str == null || str.toString().length() == 0)
			return true;
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		return false;
	}

}