/*
 * File: Activity.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 * Credits:  (if any for sections of code)
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
    String ID;
    static long idCount = 0;


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
    Activity(){
      // used to initialize tempty activity
    }

    /**
     *
     * @param activity
     * @return
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
     *
     * @return
     */
    public String getRoleValue(){
      return role.toString();
    }

    /**
     *
     * @return
     */
    public String getDateValue(){
      return date.toString();
    }

    /**
     *
     * @return
     */
    public Role getRole(){
      return role;
    }

    /**
     *
     * @return
     */
    public Year getYear(){
      return year;
    }

    /**
     *
     * @return
     */
    public Date getDate(){
      return date;
    }

    /**
     *
     * @return
     */
    public String getId(){
      return ID;
    }

    /**
     *
     * @return
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
     *
     * @param role
     */
    public void setRole(Role role){
      this.role = role;
    }

    /**
     *
     * @param year
     */
    public void setYear(Year year){
      this.year = year;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date){
      this.date = date;
    }

    /**
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
