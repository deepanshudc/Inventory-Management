package com.project.Inventory.Management.App.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getFormattedDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }


}
