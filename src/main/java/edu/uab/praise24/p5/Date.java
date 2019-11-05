/*
 * File: Date.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 * Credits:  (if any for sections of code)
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uab.praise24.p5;

import java.time.Month;


/**
 *
 * @author Praise Daramola praise24@uab.edu
 */
public class Date {
    String value;

    Date(String value){
        this.value = value.toUpperCase();
    }

    public boolean contains(Month month){
        return value.contains(month.toString());
    }

    public String toString(){
        return value;
    }

    public boolean matches(Date date){
        return this.value.equals(date.toString());
    }

    public int compareTo(Date date){
      if (date.toString().toLowerCase().equals(this.toString().toLowerCase())){
        return 0;
      } else if (stringCompare(this.toString().toLowerCase(),
                      date.toString().toLowerCase()) < 0){
        return -1;
      } else {
        return 1;
      }
    }

    private int stringCompare(String value1, String value2){

      for (int i = 0; i < value1.length();i++){
        try{
          if (Character.compare(value1.charAt(i), value2.charAt(i)) == 0){
            // keep going
          } else if (Character.compare(value1.charAt(i), value2.charAt(i)) < 0){
            return -1;
          } else {
            return 1;
          }
        } catch (NullPointerException e){
          return 1;
        }
      }
      return 0;
    }

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
