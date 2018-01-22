package com.hmei.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {
	public static LocalDate stringToLocalDate(String strDate)
	{
		LocalDate date = null;
		if(strDate.contains("/"))
		{
			String[] dateArray = strDate.split("/");
			date = LocalDate.of(Integer.valueOf(dateArray[2]), Integer.valueOf(dateArray[0]),
					Integer.valueOf(dateArray[1]));
		}
		else {
			String[] dateArray = strDate.split("-");
			date = LocalDate.of(Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[1]),
					Integer.valueOf(dateArray[2]));
		}
		
		return date;
	}
}
