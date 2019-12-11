package com.library.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class LibraryUtills {

	public static String getArrayToString(String[] val) {
		String str = "";
		for (String string : val) {
			str = str + "," + string;
		}
		return str.substring(1, str.length());
	}

	public static HashMap<String, String> getRoles() {
		HashMap<String, String> roles = new HashMap<String, String>();

		roles.put("admin", "Admin");
		roles.put("member", "Member");
		roles.put("staff", "Staff");
		return roles;
	}

	public static HashMap<String, String> getBookSubjectType() {
		HashMap<String, String> types = new HashMap<String, String>();

		types.put("computerScience", "Computer Science");
		types.put("medical", "Medical");
		types.put("fiction", "Fiction");
		types.put("biography", "Biography");
		types.put("cooking", "Cooking");
		types.put("children", "Children Books");
		types.put("novels", "Novels");
		return types;
	}

	public static String getDateInFormate(Timestamp time) {
		if (time == null)
			return null;
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		return f.format(time);
	}

}
