/*
 * File: Calendar.java
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

import java.util.ArrayList;
/**
 *
 * @author Praise Daramola praise24@uab.edu
 */
public class Calendar {
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<Activity> output;

    public void add(Activity activity){
        activities.add(activity);
    }

    public ArrayList<Activity> getActivities(int year, Role role){
      output = new ArrayList<>();
      Year yr;
      if (year%2 == 0){
          yr = Year.EVEN;
      } else {
        yr = Year.ODD;
      }
      for (Activity activity : activities){
          if (activity.role.matches(role) && activity.year == yr){
              output.add(activity);
          }
      }
      sort();
      return output;
    }

    public ArrayList<Activity> getActivites(int year, String month, Role role){
      output = new ArrayList<>();
      Year yr;
      if (year%2 == 0){
          yr = Year.EVEN;
      } else {
        yr = Year.ODD;
      }
      Activity activity = new Activity(yr,new Date(month),role,"temp");
      for (int i = 0;i < activities.size();i++){
          if (activities.get(i).matches(activity)){
              output.add(activity);
          }
      }
      sort();
      return output;
    }

    private void sort(){
      boolean sorting = true;
      Activity tempAct1 = new Activity();

      while (sorting == true){ // by date
        for (int i = 1; i < output.size();i++){
          if (output.get(i).getDate().compareTo(output.get(i-1).getDate()) == 0){
            // skip
          }else if (output.get(i).getDate().compareTo(output.get(i-1).getDate()) < 0){
            tempAct1.copy(output.get(i));
            output.get(i).copy(output.get(i-1));
            output.get(i-1).copy(tempAct1);
          }
        }
      }
    }

}
