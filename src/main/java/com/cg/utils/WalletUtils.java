package com.cg.utils;

import java.util.regex.Pattern;

public class WalletUtils {
	private WalletUtils() {
		
	}
	public static boolean validateMobile(String mobile) {
		if(mobile == null)
			return false;
		mobile = mobile.trim();
		if(mobile.length() != 8)
			return false;
		else if(mobile.matches("[0-9]{8}"))
			return true;
		return false;
	}
	public static boolean validateName(String name) {
		name = name.trim();
		if(name.length() == 0)
			return false;
		return Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", name);
	}
	public static boolean validateBalance(float balance, float amt) {
		return (balance + amt) >= 0 && (balance + amt) <= 100000;
	}
	public static boolean isNegative(float amt) {
		return amt < 0;
	}
	public static boolean validateNull(Object o) {
		return o == null;
	}
	public static boolean validateEmptyString(String s) {
		return s.trim().length() != 0;
	}
}
