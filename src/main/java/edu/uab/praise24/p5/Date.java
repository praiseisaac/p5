/**
 * File: Date.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.2.0 11/01/2019 P.D - debugging
 * Vers: 1.1.0 10/30/2019 P.D - modification for calendarApp
 * Vers: 1.0.0 10/28/2019 P.D - initial coding
 *
 * Credits:  (if any for sections of code)
 */
package edu.uab.praise24.p5;

import java.time.Month;


/**
 *  This is a date class used to create objects representing dates for 
 *  processes. It takes single month format [May] and double month format 
 *  [May/June].
 * 
 * @author Praise Daramola praise24@uab.edu
 */
public class Date {
    private String value;
    private String[] dates = new String[2];

    Date(String value){
        this.value = value.toUpperCase();
        dates = this.value.split("/",2);
    }

    /**
     *  This method is used to verify if a date object contains a particular 
     *  month.
     * 
     * @param month
     * @return boolean
     */
    public boolean contains(String month){
        return value.contains(month);
    }

    /**
     * prints the value of the date
     * 
     * @return String 
     */
    @Override
    public String toString(){
        return value;
    }
    
    /**
     * used to get the dates contained in the date object. returns an array 
     * consisting of all the dates in the object
     * 
     * @return String[]
     */
    public String[] getDate(){
        return dates;
    }

    /**
     * used to compare two date objects 
     * 
     * @param date
     * @return boolean
     */
    public boolean matches(Date date){
        return this.value.equals(date.toString());
    }

    /**
     * This is used to compare two dates to find out which date is greater or
     * lesser with respect to a calendar. [0 = equal],[1 = greater], [-1 = less]
     * 
     * @param date
     * @return int
     */
    public int compareTo(Date date){
      String dt = date.toString();
      String[] dtValue = date.getDate();
      if (Month.valueOf(dtValue[0]).ordinal() == Month.valueOf(dates[0]).ordinal()){
        return 0;
      } else if (Month.valueOf(dates[0]).ordinal() < Month.valueOf(dtValue[0]).ordinal()){
        return -1;
      } else {
        return 1;
      }
    }

    /**
     * this method is used to ensure a date is valid with respect to a regular
     * calendar.
     * 
     * @param value
     * @return boolean
     */
    public static boolean isValidDate(String value){
      String[] dates = value.split("/",2);
      int count = 0;
      for (String val : dates){
        if (isMonth(val)){
          count++;
        }
      }
        if (count == 2 && dates.length == 2){
          return true;
        }else if (count == 1){
          return true;
        }
        return false;
    }
    
    /**
     * Used to determine is a month name is valid with respect to the months in 
     * the month object.
     * 
     * @param value
     * @return boolean
     */
    private static boolean isMonth(String value){
        Month[] months = Month.values();
        for (Month month : months) {
            if (value.toLowerCase().equals(String.valueOf(month).toLowerCase()))
            {
                return true;
            }
        }
      return false;
    }
}
