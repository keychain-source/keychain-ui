package com.ericsson.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class KeychainUtils {
	public static void main(String[] args) {
		System.out.println(getCurrentTimestamp());
	}

	// function to generate a random string of length n
	public static String getAlphaNumericString() {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(12);

		for (int i = 0; i < 12; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public static String generateUserId(){
		return "IAM-" + UUID.randomUUID().toString();
	}
	
	public static String getCurrentTimestamp(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(Calendar.getInstance().getTime());
	}
}
