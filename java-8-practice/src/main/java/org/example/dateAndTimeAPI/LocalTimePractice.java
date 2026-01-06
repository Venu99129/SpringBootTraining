package org.example.dateAndTimeAPI;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimePractice {
    public static void main(String[] args) {

        //now method return system clock time in device
        LocalTime currentTime = LocalTime.now();
        System.out.println("current time :"+currentTime);

        //customTime with of method
        LocalTime customTime = LocalTime.of(22,10,1);
        System.out.println("custom timme :"+customTime);

        //create time from string using parse method
        String strTime = "10:33:53.536";
        LocalTime convertedTime = LocalTime.parse(strTime);
        System.out.println("String to LocalTime :"+convertedTime);


        //plus and minus operations of LocalTime
        LocalTime hourBefore = currentTime.minusHours(1);
        System.out.println("one hour before time :"+hourBefore);

        LocalTime after30Min = currentTime.plusMinutes(30);
        System.out.println("30 min after time :"+after30Min);

        LocalTime before90Sec = customTime.minusSeconds(90);
        System.out.println("before 90 sec time :"+before90Sec);


        //comparing LocalTime
        if(convertedTime.isBefore(currentTime)){
            System.out.println("converted time before than current time");
        }else{
            System.out.println("converted time after than current time");
        }

        if(after30Min.isAfter(currentTime)){
            System.out.println("after30min time is after than current time");
        }

        //finding duration hours between two times using Duration class
        long differenceHour = Duration.between(currentTime,convertedTime).abs().toHours();
        System.out.println("hours between currentTime and convertedTime  :"+differenceHour);

    }
}
