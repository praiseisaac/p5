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
    static ArrayList<Activity> output;
    ArrayList<Activity> temp;
    ArrayList<Role> roles = new ArrayList<>();

    public void add(Activity activity) {
        activities.add(activity);
        System.out.println("adding");
    }

    public ArrayList<Activity> getActivities(Role role) {
        ArrayList<Activity> out = new ArrayList<>();
        System.out.println("getting");
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
        System.out.println("printing sub roles");
        for (Role rl : Role.getRoles().get(role.getIndex()).subroles) {
            System.out.println(rl);
        }
        return Sorter.removeDuplicatesActivity(out);
    }

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

    public ArrayList<Activity> getActivities() {
        return activities;
    }

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

    public void updateRoles() {
        roles.clear();
        for (int i = 0; i < Role.getTotal(); i++) {
            roles.add(Role.getRoles().get(i));
        }
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public ArrayList<Activity> getActivities(int year, String month, Role role) {
        ArrayList<Activity> solu = new ArrayList<>();
        Year yr;
        if (year % 2 == 0) {
            yr = Year.EVEN;
        } else {
            yr = Year.ODD;
        }
        Activity activity = new Activity(yr, new Date(month), role, "temp");
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).matches(activity)) {
                solu.add(activity);
            }
        }
        ArrayList<Activity> out = Sorter.removeDuplicatesActivity(solu);
        return out;
    }

}
