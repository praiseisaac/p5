/**
 * File: Role.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.0.1 11/05/2019 P.D - debugging
 * Vers: 1.0.2 11/02/2019 P.D - modification for calendar app
 * Vers: 1.0.1 10/30/2019 P.D - debugging
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 */
package edu.uab.praise24.p5;

import java.util.ArrayList;

/**
 * This class is used to create a role object 
 * 
 * @author Praise Daramola praise24@uab.edu
 */
public class Role {

    private String title;
    private ArrayList<Role> subroles = new ArrayList<>();
    private ArrayList<Role> parentroles = new ArrayList<>();
    private ArrayList<Activity> activities;
    private static ArrayList<Role> allRoles = new ArrayList<>();
    private boolean exists;
    private static int index = 0;
    private int indexId;

    Role(String title) {
        this.title = title;
        for (Role role : Role.getRoles()) {
            if (role.toString().toLowerCase().equals(title.toLowerCase())) {
                exists = true;
                this.indexId = role.getIndex();
                break;
            }
        }
        if (!exists) {
            addRole(this);
            this.indexId = index;
            index++;

        }
        activities = new ArrayList<>();
    }

    @Override
    public String toString() {
        return title;
    }

    /**
     * used to get the index of a role
     * 
     * @return int
     */
    public int getIndex() {
        return indexId;
    }

    /**
     * Used to get the index of a role
     * 
     * @param role
     * @return int
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
     * Used to add a sub role to a role 
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
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * Used to add a sub role to a role 
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
     * Used to add an activity to a role
     * 
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            activities.add(activity);
        } catch (NullPointerException e) {

        }
    }

    /**
     * Used to verify if a role has an activity
     * 
     * @param activity
     * @return boolean
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
     * Used to get the activities for a role
     * 
     * @return ArrayList
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
        return activity;
    }

    private void addParentRole(Role role) {
        if (!(isParentRole(role) && role.matches(title))) {
            parentroles.add(Role.getRoles().get(role.getIndex()));
        }
    }

    /**
     * Used to get the parent roles for a role
     * 
     * @return ArrayList
     */
    public ArrayList<Role> getParentRoles() {
        return parentroles;
    }

    /**
     * Used to verify is a role is a sub role of this role
     * 
     * @param role
     * @return boolean
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
     * used to verify if a role is a parent role of this role
     * 
     * @param role
     * @return boolean
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
     * used to get the sub roles for a role
     * 
     * @return ArrayList
     */
    public ArrayList<Role> getSubRoles() {
        return subroles;
    }

    /**
     * Used to check if a role has that title
     * 
     * @param role
     * @return boolean
     */
    public boolean matches(String role) {
        return this.title.equals(role);
    }

    /**
     * Used to compare a two roles
     * 
     * @param role
     * @return boolean
     */
    public boolean matches(Role role) {
        return this.title.equals(role.toString());
    }

    /**
     * Used to add a role to the array list of all existing roles
     * 
     * @param role
     */
    private static void addRole(Role role) {
        allRoles.add(role);
    }

    /**
     * Used to get all the existing roles
     * 
     * @return ArrayList
     */
    public static ArrayList<Role> getRoles() {
        return allRoles;
    }

    /**
     * Used to compare two role's titles alphabetically
     * 
     * @param role
     * @return int 
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

    /**
     * Used to compare two strings
     * 
     * @param role
     * @return int 
     */
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
     * used to get the total number of existing roles
     * 
     * @return int 
     */
    public static int getTotal(){
        return index;
    }

    /**
     * used to copy one role's information into another one
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
