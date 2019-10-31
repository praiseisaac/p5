/*
 * File: Activity.java
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

/**
 *
 * @author Praise Daramola praise24@uab.edu
 */
public class Activity {
    Date date;
    Year year;
    Role role;
    String description;
    Activity(Year year, Date date, Role role, String description){
        this.year = year;
        this.date = date;
        this.role = role;
        this.description = description;
    }

    Activity(){
      // used to initialize tempty activity
    }

    public boolean matches(Activity activity){
      if (activity.role.matches(this.role) && activity.year == this.year
       && this.date.matches(activity.getDate())){
          return true;
      }else {
          return false;
      }
    }

    // Queries for activity
    public String getRoleValue(){
      return role.toString();
    }

    public String getDateValue(){
      return date.toString();
    }

    public Role getRole(){
      return role;
    }

    public Year getYear(){
      return year;
    }

    public Date getDate(){
      return date;
    }

    public String getDescription(){
      return description;
    }

    // Commands for activity
    public void setRole(Role role){
      this.role = role;
    }

    public void setYear(Year year){
      this.year = year;
    }

    public void setDate(Date date){
      this.date = date;
    }

    public void copy(Activity activity){
      this.year = activity.getYear();
      this.date = new Date(activity.getDateValue());
      this.role = new Role(activity.getRoleValue());
      this.description = activity.getDescription();
    }
}
