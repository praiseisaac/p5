/*
 * File: Sorter.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  p5 - EE333 Fall 2019
 * Vers: 1.0.0 11/03/2019 PAD - initial coding
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
public class Sorter {

    /**
     *
     * @param tempList
     * @return
     */
    public static ArrayList<Activity> sortActivity(ArrayList<Activity> tempList) {
        boolean sorting = true;
        try {
            Activity tempAct1 = tempList.get(0);
            while (sorting == true) { // by date
                sorting = false;
                for (int i = 1; i < tempList.size(); i++) {
                   
                    if (tempList.get(i).getDate().compareTo(tempList.get(i - 1).getDate()) >= 0) {
                        //sorting = false;
                    } else if (tempList.get(i).getDate().compareTo(tempList.get(i - 1).getDate()) < 0) {
                        tempAct1.copy(tempList.get(i));
                        tempList.get(i).copy(tempList.get(i - 1));
                        tempList.get(i - 1).copy(tempAct1);
                    }
                }
                
                for (int j = 1; j < tempList.size();j++){
                  if (tempList.get(j).getDate().compareTo(tempList.get(j - 1).getDate()) < 0) {
                      sorting = true;
                      break;
                  }
                }
            }
            return tempList;
        } catch (Exception e) {
            return tempList;
        }
    }

    /**
     *
     * @param out
     * @return
     */
    public static ArrayList<Activity> removeDuplicatesActivity(ArrayList<Activity> out) {
        System.out.println("trying");
        try {
          
            ArrayList<Activity> tempList = new ArrayList<>();
            ArrayList<Activity> output = sortActivity(out);
            int count = 0;
            tempList.add(output.get(0));
            for (int i = 0; i < output.size(); i++) {
                if (!(tempList.get(count).matches(output.get(i)) && containedIn(tempList,output.get(i)))) {

                    tempList.add(output.get(i));
                    count++;
                } 
            }
            return tempList;
        } catch (Exception e) {
            return out;
        }
    }

    private static boolean containedIn(ArrayList<Activity> activities, Activity activity) {
        for (Activity act : activities) {
            if (act.matches(activity)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tempList
     * @return
     */
    public static ArrayList<Role> sortRole(ArrayList<Role> tempList) {
        boolean sorting = true;

        try {
            Role tempAct1 = new Role(tempList.get(0).toString());

            while (sorting == true) { // by date
                sorting = false;

                for (int i = 1; i < tempList.size(); i++) {
                    if (tempList.get(i).
                        compareTo(tempList.get(i - 1)) >= 0) {
                        //sorting = false;
                    } else if (tempList.get(i).
                        compareTo(tempList.get(i - 1)) < 0) {
                        tempAct1.copy(tempList.get(i));
                        tempList.get(i).copy(tempList.get(i - 1));
                        tempList.get(i - 1).copy(tempAct1);
                        sorting = true;
                    }
                }
            }
            return tempList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param out
     * @return
     */
    public static ArrayList<Role> removeDuplicatesRole(ArrayList<Role> out) {

        try {
            ArrayList<Role> tempList = new ArrayList<>();
            ArrayList<Role> output = sortRole(out);
            int count = 0;
            tempList.add(output.get(0));
            for (int i = 0; i < output.size(); i++) {
                if (!tempList.get(count).matches(output.get(i).toString())) {
                    tempList.add(output.get(i));
                    count++;
                }
            }
            return tempList;
        } catch (Exception e) {
            return out;
        }

    }


}
