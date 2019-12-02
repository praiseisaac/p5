/**
 * File: Activity.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.1.0 10/30/2019 P.D - modification for the calendar app
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 */

package edu.uab.praise24.p5;

/**
 *  This class allows for the creation of an activity
 * 
 * @author Praise Daramola praise24@uab.edu
 */
public class Activity {
    private Date date;
    private Year year;
    private Role role;
    private String description;
    private String ID;
    private static long idCount = 0;


    Activity(Year year, Date date, Role role, String description){
        this.year = year;
        this.date = date;
        this.role = role;
        this.description = description;
        if (null == year){
            ID = "EA-" + idCount;

        } else switch (year) {
            case EVEN:
                ID = "EV-" + idCount;
                break;
            case ODD:
                ID = "OD-" + idCount;
                break;
            default:
                ID = "EA-" + idCount;
                break;
        }
        idCount++;

        updateRole();
    }

    private void updateRole(){
        Role.getRoles().get(role.getIndex()).addActivity(this);
    }

    /**
     * used to compare to another activity
     * 
     * @param activity
     * @return boolean
     */
    public boolean matches(Activity activity){
      if (activity.getRoleValue().equals(this.getRoleValue())
      && activity.getYear() == this.getYear()
       && this.getDate().matches(activity.getDate())){
          return true;
      }else {
          return false;
      }
    }

    // Queries for activity

    /**
     * used to get the string representation of the role
     * 
     * @return String
     */
    public String getRoleValue(){
      return role.toString();
    }

    /**
     * Used to get the string representation of the date
     * 
     * @return String
     */
    public String getDateValue(){
      return date.toString();
    }

    /**
     * Used to get the role
     * 
     * @return Role
     */
    public Role getRole(){
      return role;
    }

    /**
     * Used to get the year
     * 
     * @return Year
     */
    public Year getYear(){
      return year;
    }

    /**
     * Used to get the date
     * 
     * @return Date
     */
    public Date getDate(){
      return date;
    }

    /**
     * Used to get the activity ID
     * 
     * @return String
     */
    public String getId(){
      return ID;
    }

    /**
     * Used to get the description for an activity
     * 
     * @return String
     */
    public String getDescription(){
      return description;
    }

    @Override
    public String toString(){
      String message = "%4s | %21s | %12s | %s";
      String output = String.format(message,year,date,role,description);
      return output;
    }
    // Commands for activity

    /**
     * Used to explicitly set the role for an activity.
     * 
     * @param role
     */
    public void setRole(Role role){
      this.role = role;
    }

    /**
     * Used to explicitly set the year for an activity
     * 
     * @param year
     */
    public void setYear(Year year){
      this.year = year;
    }

    /**
     * Used to explicitly set the date for an activity
     *
     * @param date
     */
    public void setDate(Date date){
      this.date = date;
    }

    /**
     * Copy's another activity's information to this activity
     * 
     * @param activity
     */
    public void copy(Activity activity){
      this.year = activity.getYear();
      this.date = activity.getDate();
      this.role = activity.getRole();
      this.description = activity.getDescription();
      this.ID = activity.getId();
    }
}
