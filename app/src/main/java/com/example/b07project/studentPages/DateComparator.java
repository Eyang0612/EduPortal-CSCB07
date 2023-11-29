package com.example.b07project.studentPages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator<T extends Notifications> implements Comparator<T> {

    private final String dateFormatPattern;

    public DateComparator(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    @Override
    public int compare(T object1, T object2) {
        // Compare the postTimes and handles exceptions

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        try {
            Date date1 = getDate(object1, dateFormat);
            Date date2 = getDate(object2, dateFormat);


            return date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Date getDate(T object, SimpleDateFormat dateFormat) throws ParseException {
        // Gets PostTime from objects and converts it into a Date object

        String postTimeString = object.getNotificationDate();
        return dateFormat.parse(postTimeString);
    }
}
