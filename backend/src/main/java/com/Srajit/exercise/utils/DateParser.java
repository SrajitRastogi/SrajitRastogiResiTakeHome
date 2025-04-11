package com.Srajit.exercise.utils;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.expression.ParseException;


public class DateParser {
    private static final List<String> datesFormat = Arrays.asList(
        "MM/dd/yy",
        "MMMM d, yyyy",
        "MMM-dd-yyyy"
    );

    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String parseDate(String dateString){
        for(String df : datesFormat){
            try{
                SimpleDateFormat desiredDateFormat = new SimpleDateFormat(df, Locale.ENGLISH);
                desiredDateFormat.setLenient(false);
                Date date = desiredDateFormat.parse(dateString);

                System.out.println("Parsed date '" + dateString + "' using pattern '" + df + "'");

                return outputFormat.format(date);
            }catch(ParseException | java.text.ParseException ignored){

            }
        }
        return null;
    }

}
