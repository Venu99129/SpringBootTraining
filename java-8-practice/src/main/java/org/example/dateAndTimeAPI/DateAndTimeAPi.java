package org.example.dateAndTimeAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateAndTimeAPi {

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        int year = currentDate.getYear();
        int day = currentDate.getDayOfMonth();
        Month month = currentDate.getMonth();

        System.out.println("year of current date :"+year);
        System.out.println("day of current date :"+day);
        System.out.println("month of current date :"+month.getValue());

        //creating date using parse method  yyyy-mm-dd
        String strDate = "1985-09-12";
        LocalDate parsedDate = LocalDate.parse(strDate);
        System.out.println("parsed Date :"+parsedDate);

        //minus days and months and year

        LocalDate yesterday = currentDate.minusDays(1);
        System.out.println("yesterday :"+yesterday);

        LocalDate previousMonth = currentDate.minusMonths(1);
        System.out.println("last month :"+previousMonth.getMonthValue());

        LocalDate previousYear = currentDate.minusYears(1);
        System.out.println("last year :"+previousYear.getYear());

        //date comparing
        if(currentDate.isAfter(yesterday)){
            System.out.println("current date after the yesterday");
        }else{
            System.out.println("current date before the yesterday");
        }

        if(yesterday.isBefore(currentDate)){
            System.out.println("yesterday date before the current day");
        }else{
            System.out.println("yesterday date after the current day");
        }

        LocalDate cusDate = LocalDate.of(2026,8,26);
        if(cusDate.isAfter(currentDate)){
            System.out.println("custom date is after the current date");
        }

        //format date like indian

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

        String formattedDate = currentDate.format(formatter);
        System.out.println("indian format date :"+formattedDate);

        //zoned date and time
        ZonedDateTime indianZoneDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("india date and time :"+indianZoneDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss.SSS")));

        System.out.println();
        //System.out.println(ZoneId.getAvailableZoneIds());


        // calculating period using Period class
        Period diff = Period.between(currentDate,parsedDate);
        System.out.println("different period b/w two dates :"+diff);
        int months = Math.abs(diff.getMonths());
        System.out.println("period of months between two dates current date and parsedDate :"+months);

        int years = Math.abs(Period.between(currentDate,parsedDate).getYears());
        System.out.println("period of years between two dates current date and parsedDate :"+years);

    }
}
