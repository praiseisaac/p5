/**
 * File: Calendar.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.2.0 11/16/2019 P.D - debugging
 * Vers: 1.1.0 11/08/2019 P.D - modification for calendar app
 * Vers: 1.0.1 10/30/2019 P.D - debugging
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 */
package edu.uab.praise24.p5;

import java.util.ArrayList;

/**
 * This Class allows for the creation of a calendar object
 * 
 * @author Praise Daramola praise24@uab.edu
 */
public class Calendar {

    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<Activity> temp;
    private ArrayList<Role> roles = new ArrayList<>();

    /**
     * Used to add an activity to the calendar
     * 
     * @param activity
     */
    public void add(Activity activity) {
        activities.add(activity);
    }

    /**
     * used to get a list of activities from the calendar
     * 
     * @param role
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(Role role) {
        ArrayList<Activity> out = new ArrayList<>();
        
        for (Activity activity : activities) {
            for (Role srole : Role.getRoles().get(role.getIndex()).getSubRoles()) {
                if (activity.getRole().matches(srole)) {
                    out.add(activity);
                }
            }
            if (activity.getRole().matches(role)) {
                out.add(activity);
            }
        }
        return Sorter.removeDuplicatesActivity(out);
    }

    /**
     * used to get a list of activities from the calendar
     * 
     * @param year
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(int year) {
        ArrayList<Activity> out = new ArrayList<>();
        Year yr;
        if (year % 2 == 0) {
            yr = Year.EVEN;
        } else {
            yr = Year.ODD;
        }
        for (Activity activity : activities) {
            if ((activity.getYear() == yr || activity.getYear() == Year.EACH)) {
                out.add(activity);
            }
        }
        out = Sorter.removeDuplicatesActivity(out);
        return out;
    }

    /**
     * Used to get all activities from the calendar
     * 
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }

    /**
     * used to get a list of activities from the calendar
     * 
     * @param month
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(String month) {
        ArrayList<Activity> out = new ArrayList<>();

        for (Activity activity : activities) {
            if (activity.getDateValue().contains(month)) {
                out.add(activity);
            }
        }
        out = Sorter.removeDuplicatesActivity(out);
        return out;
    }

    /**
     * Updates the roles in the calendar
     */
    public void updateRoles() {
        roles.clear();
        for (int i = 0; i < Role.getTotal(); i++) {
            roles.add(Role.getRoles().get(i));
        }
    }

    /**
     * Used to get the roles from the calendar
     * 
     * @return ArrayList
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     * used to get a list of activities from the calendar
     * 
     * @param year
     * @param month
     * @param role
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(int year, String month, Role role) {
        ArrayList<Activity> solu = new ArrayList<>();
        ArrayList<Activity> soluYear = new ArrayList<>();
        ArrayList<Activity> roleActivities = getActivities(role);
        Year yr;
        if (year % 2 == 0) {
            yr = Year.EVEN;
        } else {
            yr = Year.ODD;
        }
        for (Activity activity : roleActivities) {
            if (activity.getYear() == yr || activity.getYear() == Year.EACH) {
                soluYear.add(activity);
            }
        }
        for (Activity activity: soluYear){
            if (activity.getDate().contains(month)){
                solu.add(activity);
            }
        }
        
        ArrayList<Activity> out = Sorter.removeDuplicatesActivity(solu);
        return out;
    }
    
    /**
     * used to get a list of activities from the calendar
     * 
     * @param year
     * @param month
     * @param role
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(String month, Role role) {
        ArrayList<Activity> solu = new ArrayList<>();
        ArrayList<Activity> roleActivities = getActivities(role);
        for (Activity activity: roleActivities){
            if (activity.getDate().contains(month)){
                solu.add(activity);
            }
        }
        ArrayList<Activity> out = Sorter.removeDuplicatesActivity(solu);
        return out;
    }
    
    /**
     * used to get a list of activities from the calendar
     * 
     * @param year
     * @param role
     * @return ArrayList
     */
    public ArrayList<Activity> getActivities(int year, Role role) {
        ArrayList<Activity> solu = new ArrayList<>();
        ArrayList<Activity> roleActivities = getActivities(role);
        Year yr;
        if (year % 2 == 0) {
            yr = Year.EVEN;
        } else {
            yr = Year.ODD;
        }
        for (Activity activity : roleActivities) {
            if (activity.getYear() == yr || activity.getYear() == Year.EACH) {
                solu.add(activity);
            }
        }
        ArrayList<Activity> out = Sorter.removeDuplicatesActivity(solu);
        return out;
    }

}
