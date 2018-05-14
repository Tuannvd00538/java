/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author biidz
 */
public class Student {

    private String EnrolID;
    private String FirstName;
    private String LastName;
    private int Age;

    public Student() {
    }

    public Student(String EnrolID, String FirstName, String LastName, int Age) {
        this.EnrolID = EnrolID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
    }

    public String getEnrolID() {
        return EnrolID;
    }

    public void setEnrolID(String EnrolID) {
        this.EnrolID = EnrolID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

}
