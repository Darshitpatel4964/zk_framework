package com.crud.zkcrud.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateConversion {
	 
	private DateConversion() {
		  }

	public static String date(Date dob) {
		DateFormat input = new SimpleDateFormat("yyyy-MM-dd");
		input.format(dob);
		input = new SimpleDateFormat("dd MMM yyyy");
		return input.format(dob);
	}

}
