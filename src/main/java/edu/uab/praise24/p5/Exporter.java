/**
 * File: Export.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 1.1.1 11/16/2019 P.D - exporting to ics format implemented
 * Vers: 1.0.1 10/30/2019 P.D - debugging
 * Vers: 1.0.0 10/22/2019 P.D - initial coding
 *
 */
package edu.uab.praise24.p5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * This class can be used to export activities to a file or to the command line
 *
 * @author Praise Daramola
 */
public class Exporter {

    /**
     * prints the activities to the command line
     *
     * @param export
     */
    public void print(ArrayList<Activity> export) {
        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param year
     */
    public void print(ArrayList<Activity> export, int year) {
        System.out.println("Calendar for " + year);
        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param year
     * @param month
     */
    public void print(ArrayList<Activity> export, int year, String month) {
        System.out.println("Calendar for " + month + " " + year);

        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param month
     */
    public void print(ArrayList<Activity> export, String month) {
        System.out.println("Calendar for " + month);

        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param role
     */
    public void print(ArrayList<Activity> export, Role role) {
        System.out.println("Calendar for " + role);
        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param role
     * @param year
     */
    public void print(ArrayList<Activity> export, Role role, int year) {
        System.out.println("Calendar for " + role + " " + year);
        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param role
     * @param year
     * @param month
     */
    public void print(ArrayList<Activity> export, Role role, int year, String month) {
        System.out.println("Calendar for " + role + " " + month + " " + year);

        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * prints the activities to the command line
     *
     * @param export
     * @param role
     * @param month
     */
    public void print(ArrayList<Activity> export, Role role, String month) {
        System.out.println("Calendar for " + role + " " + month);

        for (Activity activity : export) {
            System.out.println(activity);
        } //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename) throws FileNotFoundException {
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            for (Activity activity : export) {
                writer.println(activity.toString());
            }
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param year
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, int year) throws FileNotFoundException {
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
           
            String date;
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME: multiple \r\n"
                    + "X-WR-CALDESC:" + "calendar for " + year);

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(activity.getDateValue()).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDateValue()).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param year
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, int year, String month) throws FileNotFoundException {
        String date;
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(activity.getDateValue()).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(month).maxLength()
                            + "10T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, String month) throws FileNotFoundException {
        String date;
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME: multiple\r\n"
                    + "X-WR-CALDESC:" + "calendar for " + month);

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(activity.getDateValue()).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(month).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param role
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, Role role) throws FileNotFoundException {
        String date;
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME:" + role.toString() + "\r\n"
                    + "X-WR-CALDESC:" + "calendar for " + role.toString());

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(activity.getDateValue()).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDateValue()).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param role
     * @param year
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, Role role, int year) throws FileNotFoundException {
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            String date;
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME:" + role.toString() + "\r\n"
                    + "X-WR-CALDESC:" + "calendar for " + role.toString());

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(activity.getDateValue()).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDateValue()).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDateValue()).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + year
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param role
     * @param year
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, Role role, int year, String month) throws FileNotFoundException {
        String date;
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME:" + role.toString() + "\r\n"
                    + "X-WR-CALDESC:" + "calendar for " + role.toString());

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(month).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(month).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(month).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + activity.getYear()
                            + date
                            + Month.valueOf(month).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + activity.getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + year
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + activity.getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                
            }
            writer.println("END:VCALENDAR");
            
        }
    }

    /**
     * Exports the activities to a file
     *
     * @param export
     * @param filename
     * @param role
     * @param month
     * @throws FileNotFoundException
     */
    public void Export(ArrayList<Activity> export,
            File filename, Role role, String month) throws FileNotFoundException {
        String date;
        try (PrintStream writer = new PrintStream(new FileOutputStream(filename))) {
            writer.println("BEGIN:VCALENDAR\r\n"
                    + "VERSION:2.0\r\n"
                    + "PRODID:icalendar-ruby\r\n"
                    + "CALSCALE:GREGORIAN\r\n"
                    + "METHOD:PUBLISH\r\n"
                    + "X-WR-CALNAME:" + role.toString() + "\r\n"
                    + "X-WR-CALDESC:" + "calendar for " + role.toString());

            for (Activity activity : export) {
                if (activity.getDate().getDate().length == 1) {
                    if (Month.valueOf(month).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(month).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(month).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(month).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                } else {
                    if (Month.valueOf(activity.getDate().getDate()[0]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[0]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[0]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                    if (Month.valueOf(activity.getDate().getDate()[1]).getValue() >= 10) {
                        date = String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    } else {
                        date = "0" + String.valueOf(Month.valueOf(activity.getDate().getDate()[1]).getValue());
                    }
                    writer.println("BEGIN:VEVENT\r\n"
                            + "DTSTAMP:20190606T161900Z\r\n"
                            + "UID:event-assignment-5992859\r\n"
                            + "DTSTART;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + "01T000000\r\n"
                            + "DTEND;VALUE=DATE:" + LocalDate.now().getYear()
                            + date
                            + Month.valueOf(activity.getDate().getDate()[1]).maxLength()
                            + "T000000\r\n"
                            + "CLASS:PUBLIC\r\n"
                            + "DESCRIPTION:\r\n"
                            + "SEQUENCE:0\r\n"
                            + "SUMMARY:"+ activity.getDescription() + "\r\n"
                            + "URL:\r\n"
                            + "END:VEVENT");
                }
                 
            }
            writer.println("END:VCALENDAR");
           
        }
    }
}
