/*
 * File: Role.java
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
public class Role {

    String title;
    ArrayList<Role> subroles = new ArrayList<>();
    ArrayList<Role> parentroles = new ArrayList<>();
    ArrayList<Activity> activities;
    static ArrayList<Role> allRoles = new ArrayList<>();
    boolean exists;
    private static int index = 0;
    int indexId;

    Role(String title) {
        this.title = title;
        for (Role role : Role.getRoles()) {
            if (role.toString().toLowerCase().equals(title.toLowerCase())) {
                exists = true;
                this.indexId = role.getIndex();
                System.out.println(role + "=" + title + " - " + indexId);
                break;
            }
        }
        if (!exists) {
            addRole(this);
            this.indexId = index;
            System.out.println(indexId + " = " + index);
            index++;

        }
        activities = new ArrayList<>();
    }

    @Override
    public String toString() {
        return title;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return indexId;
    }

    /**
     *
     * @param role
     * @return
     */
    public static int getIndex(String role) {
        for (Role role2 : allRoles) {
            if (role.equals(role2.toString())) {
                return role2.getIndex();
            }
        }
        return -1;
    }

    /**
     *
     * @param role
     */
    public void addSubRole(Role role) {
        try {
            if (!(this.matches(role.toString()) && isParentRole(role))) {
                subroles.add(role);
                for (Role srole : role.getSubRoles()) {
                    if (!this.matches(srole.toString())) {
                        subroles.add(srole);
                        System.out.println(srole + " added to " + Role.getRoles().get(indexId));
                    }
                }
            }
        } catch (NullPointerException e) {
        }
        //Sorter.removeDuplicatesRole(subroles);
        //Role.getRoles().get(role.getIndex()).addParentRole(this);
    }

    /**
     *
     * @param role
     */
    public void addSubRole(String role) {
        int ind = 0;
        ArrayList<Role> temp = subroles;
        if (!role.matches(title)) {
            for (Role role2 : allRoles) {
                if (role.matches(role2.toString())) {
                    ind = role2.getIndex();

                    for (int i = 0; i < Role.getRoles().get(indexId).getSubRoles().size(); i++){
                      for (Role role1: Role.getRoles().get(ind).getSubRoles()){
                        if (role1.matches(subroles.get(i).toString())){
                          temp.remove(i);
                          break;
                        }
                      }
                    }
                    break;
                }
            }
            addSubRole(Role.getRoles().get(ind));
            subroles = temp;
            Role.getRoles().get(ind).addParentRole(this);
        }

    }

    /**
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            activities.add(activity);
            System.out.println(activity + " added to " + Role.getRoles().get(indexId));
        } catch (NullPointerException e) {

        }
    }

    /**
     *
     * @param activity
     * @return
     */
    public boolean activityExists(Activity activity) {
        for (Activity act : getActivities()) {
            if (activity.matches(act)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param role1
     * @return
     */
    public boolean isHigher(Role role1) {
        boolean value = false;

        if (Role.getRoles().get(role1.getIndex()).getSubRoles() == null) {
            value = false;
        } else {
            for (Role rl : Role.getRoles().get(role1.getIndex()).getSubRoles()) {
                if (rl.getIndex() == this.getIndex()) {
                    value = false;
                } else if (isHigher(rl) == true && rl.getIndex() != this.getIndex()){
                    value = true;
                    return value;
                }
            }
        }
        System.out.println("Heirachy verification: " + title + " " + value);
        return value;
    }

    /**
     *
     * @return
     */
    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> activity = Sorter.removeDuplicatesActivity(activities);
        for (Role role : subroles) {
            if (!Role.getRoles().get(role.getIndex()).matches(title)){
                for (Activity act : allRoles.get(role.getIndex()).getActivities()) {
                    activity.add(act);
                }
            }
        }
        System.out.println("getting activities for " + this);
        return activity;
    }

    private void addParentRole(Role role) {
        if (!(isParentRole(role) && role.matches(title))) {
            System.out.println("new parent role " + Role.getRoles().get(role.getIndex()));
            parentroles.add(Role.getRoles().get(role.getIndex()));
        }
        //Sorter.removeDuplicatesRole(parentroles);
    }

    /**
     *
     * @return
     */
    public ArrayList<Role> getParentRoles() {
        return parentroles;
    }

    /**
     *
     * @param role
     * @return
     */
    public boolean isSubrole(Role role){
        for (Role rl : subroles){
          if (role.matches(rl)){
            return true;
          }
        }
        return false;
    }

    /**
     *
     * @param role
     * @return
     */
    public boolean isParentRole(Role role) {
        for (Role rl : parentroles) {
            if (rl.matches(role.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public ArrayList<Role> getSubRoles() {
        return subroles;
    }

    /**
     *
     * @param role
     * @return
     */
    public boolean matches(String role) {
        return this.title.equals(role);
    }

    /**
     *
     * @param role
     * @return
     */
    public boolean matches(Role role) {
        return this.title.equals(role.toString());
    }

    /**
     *
     * @param role
     */
    public static void addRole(Role role) {
        allRoles.add(role);
    }

    /**
     *
     * @return
     */
    public static ArrayList<Role> getRoles() {
        return allRoles;
    }

    /**
     *
     * @param role
     * @return
     */
    public int compareTo(Role role) {
        if (role.toString().toLowerCase().equals(title.toLowerCase())) {
            return 0;
        } else if (stringCompare(title,
                role.toString().toLowerCase()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    private int stringCompare(String value1, String value2) {

        for (int i = 0; i < value1.length(); i++) {
            try {
                if (Character.compare(value1.charAt(i), value2.charAt(i)) == 0) {
                    // keep going
                } else if (Character.compare(value1.charAt(i), value2.charAt(i)) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            } catch (NullPointerException e) {
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public static int getTotal(){
        return index;
    }

    /**
     *
     * @param role
     */
    public void copy(Role role) {
        this.title = role.toString();
        this.subroles = role.getSubRoles();
        this.parentroles = role.getParentRoles();
        this.indexId = role.getIndex();
    }
}
