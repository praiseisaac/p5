/*
 * File: Export.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

 public class Exporter {

     public void print(ArrayList<Activity> export) {
        for (Activity activity: export){
          System.out.println(activity.getId() + " - " + activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    public void print(ArrayList<Activity> export, int year) {
        System.out.println("Calendar for " + year);
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    public void Export(ArrayList<Activity> export,
        File filename) throws FileNotFoundException{
        PrintStream writer = new PrintStream(new FileOutputStream(filename));

        for (Activity activity: export){
            writer.println(activity.toString());
            System.out.println("written");
        }
        writer.close();
    }

    public void Export(ArrayList<Activity> export,
        File filename, int year) throws FileNotFoundException{
        PrintStream writer = new PrintStream(new FileOutputStream(filename));
        writer.println("Calendar for " + year);

        for (Activity activity: export){
            writer.println(activity.toString());
            System.out.println("written");
        }
        writer.close();
    }


 }
