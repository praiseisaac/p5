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
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author daram
 */
public class Exporter {

    /**
     *
     * @param export
     */
    public void print(ArrayList<Activity> export) {
        for (Activity activity: export){
          System.out.println(activity.getId() + " - " + activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param export
     * @param year
     */
    public void print(ArrayList<Activity> export, int year) {
        System.out.println("Calendar for " + year);
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     *
     * @param export
     * @param year
     * @param month
     */
    public void print(ArrayList<Activity> export, int year, String month) {
        System.out.println("Calendar for " + month + " " + year);
        
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
       /**
     *
     * @param export
     * @param month
     */
    public void print(ArrayList<Activity> export, String month) {
        System.out.println("Calendar for " + month);
        
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param export
     * @param role
     */
    public void print(ArrayList<Activity> export, Role role) {
        System.out.println("Calendar for " + role);
        for (Activity activity: export){
          System.out.println(activity.getId() + " - " + activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param export
     * @param role
     * @param year
     */
    public void print(ArrayList<Activity> export, Role role, int year) {
        System.out.println("Calendar for " + role + " " + year);
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     *
     * @param export
     * @param role
     * @param year
     * @param month
     */
    public void print(ArrayList<Activity> export, Role role, int year, String month) {
        System.out.println("Calendar for " + role + " " + month + " " + year);
        
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
       /**
     *
     * @param export
     * @param role
     * @param month
     */
    public void print(ArrayList<Activity> export, Role role, String month) {
        System.out.println("Calendar for " + role + " " + month);
        
        for (Activity activity: export){
          System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param export
     * @param filename
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            for (Activity activity: export){
                writer.println(activity.toString());
                System.out.println("written");
            }
        }
    }

    /**
     *
     * @param export
     * @param filename
     * @param year
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, int year) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + year);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }
    
    /**
     *
     * @param export
     * @param filename
     * @param year
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, int year, String month) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + month + " " + year);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }

     /**
     *
     * @param export
     * @param filename
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, String month) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + month);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }

    /**
     *
     * @param export
     * @param filename
     * @param role
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, Role role) throws FileNotFoundException{
        
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + role);
            for (Activity activity: export){
                writer.println(activity.toString());
                System.out.println("written");
            }
        }
    }

    /**
     *
     * @param export
     * @param filename
     * @param role
     * @param year
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, Role role, int year) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + role + " " + year);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }
    
    /**
     *
     * @param export
     * @param filename
     * @param role
     * @param year
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, Role role,int year, String month) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            
            writer.println("Calendar for " + role + " "+ month + " " + year);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }

     /**
     *
     * @param export
     * @param filename
     * @param role
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
        File filename, Role role,String month) throws FileNotFoundException{
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("Calendar for " + role + " " + month);
            
            for (Activity activity: export){
                writer.println(activity.toString());
            }
        }
    }
 }
