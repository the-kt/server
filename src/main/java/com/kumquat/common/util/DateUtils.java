package com.kumquat.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    public static List<String> getYearByStartAndEnd(Date startDate,Date endDate) {
        List<String> yearList = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int startYear = c.get(Calendar.YEAR);
        c.setTime(endDate);
        int endYear = c.get(Calendar.YEAR);
        while (startYear <= endYear) {
            yearList.add(String.valueOf(startYear));
            startYear++;
        }
        return yearList;
    }

    public static String DateToString(Date data){

        if(data==null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(data);

        return  dateString;
    }

    public static String GmtStringToDate(String str){

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        Date dd = null; //将字符串改为date的格式
        String result ="";
        try {
            str =str.substring(0,str.indexOf("("));
            dd = sdf.parse(str);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            result = formatter.format(dd);
        } catch (ParseException e) {

        }
        return result;
    }

    public static int getDateYear(Date d){

        if(d!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            return year;
        }

        return 0;
    }
}
