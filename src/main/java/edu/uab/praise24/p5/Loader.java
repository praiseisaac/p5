/**
 * File: Loader.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.0.1 11/05/2019 P.D - debugging
 * Vers: 1.0.1 11/01/2019 P.D - debugging
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 */
package edu.uab.praise24.p5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This class is used to load and save history
 * 
 * @author Praise Daramola praise24@uab.edu
 */
public class Loader {

    /**
     *  This method is used to save history.
     * 
     * @param calendar
     * @throws FileNotFoundException
     */
    public static void save(Calendar calendar) throws FileNotFoundException {
        // saves the files to a folder called history
        File filename = new File(System.getProperty("user.dir") + "/History/activity.txt");

        PrintStream writer = new PrintStream(new FileOutputStream(filename));
        ArrayList<String> temp = new ArrayList<>();;

        for (Activity activity : Sorter.removeDuplicatesActivity(calendar.getActivities())) {
            writer.println(activity.getYear() + "," + activity.getDateValue()
                    + "," + activity.getRoleValue() + "," + activity.getDescription());
        }
        writer.close();
        filename = new File(System.getProperty("user.dir") + "/History/roles.txt");
        filename.getParentFile().mkdirs();
        writer = new PrintStream(new FileOutputStream(filename));
        for (int i = 0; i < Role.getTotal();i++){
            temp.clear(); 
            writer.println("{" + Role.getRoles().get(i) + "}");
            for (Role rl : Role.getRoles().get(i).getSubRoles()) {
                if (!containedIn(temp, rl.toString())) {
                    writer.println(rl.toString());
                    temp.add(rl.toString());
                }
            }
        }
        writer.close();
    }

    private static boolean containedIn(ArrayList<String> roles, String role) {
        for (String val : roles) {
            if (val.equals(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to load saved history
     * 
     * @return Calendar
     * @throws FileNotFoundException
     */
    public static Calendar load() throws FileNotFoundException {
        Calendar calendar = new Calendar();
        boolean start;
        StringBuilder role;
        String fname;
        Scanner file;
        String[] actName;
        Activity activity;
        ArrayList<Role> subRoleH = new ArrayList<>();
        Role roleVal;
        // pass the path to the file as a parameter
        File filename;
        try {
            filename = new File(System.getProperty("user.dir") + "\\History\\roles.txt");
            file = new Scanner(filename,"utf-8");
            roleVal = null;
            while (file.hasNextLine()) {
                fname = file.nextLine();
                start = false;
                if (fname.contains("{")) {
                    subRoleH.clear();

                    role = new StringBuilder(fname.length() - 2);
                    for (int i = 1; i < fname.length() - 1; i++) {
                        role.append(fname.charAt(i));
                    }
                    roleVal = new Role(role.toString());
                    start = true;
                }
                if (!start) {
                    Role rl = new Role(fname);
                    Role.getRoles().get(roleVal.getIndex()).addSubRole(Role.getRoles().get(rl.getIndex()));
                }
            }
        } catch (FileNotFoundException e) {
            filename = new File(System.getProperty("user.dir") + "\\History\\roles.txt");
            filename.getParentFile().mkdirs();
            PrintStream writer = new PrintStream(new FileOutputStream(filename));
        }

        try {
            filename = new File(System.getProperty("user.dir") + "\\History\\activity.txt");
            filename.getParentFile().mkdirs();
            file = new Scanner(filename,"utf-8");
            roleVal = null;
            while (file.hasNextLine()) {
                fname = file.nextLine();

                actName = fname.split(",", 4);

                activity = new Activity(Year.valueOf(actName[0]),
                        new Date(actName[1]), new Role(actName[2]), actName[3]);
                
                calendar.add(activity);
            }
        } catch (FileNotFoundException e) {
            filename = new File(System.getProperty("user.dir") + "\\History\\activity.txt");
            filename.getParentFile().mkdirs();
            PrintStream writer = new PrintStream(new FileOutputStream(filename));
        }
        return calendar;
    }
}
