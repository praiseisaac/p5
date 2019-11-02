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
    static ArrayList<Role> allRoles = new ArrayList<>();
    boolean exists;

    Role(String title){
        this.title = title;
        for (Role role : allRoles){
          if (role.toString() == this.title){
            exists = true;
            break;
          }
        }
        if(!exists){
          addRole(this);
        } 

    }

    @Override
    public String toString(){
        return title;
    }

    public void addSubRole(Role role){
        subroles.add(role);
    }

    public ArrayList<Role> getSubRoles(){
        return subroles;
    }

    public boolean matches(String role){
        return this.title.equals(role);
    }

    public static void addRole(Role role){
        allRoles.add(role);
    }

    public static ArrayList<Role> getRoles(){
      return allRoles;
    }
}
