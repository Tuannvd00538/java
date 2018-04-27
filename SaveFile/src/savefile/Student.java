/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savefile;

/**
 *
 * @author biidz
 */
public class Student {
    private String rollNumber;
    private String name;
    private int balance;
    private String createAt;

    public Student() {
    }

    public Student(String rollNumber, String name, int balance, String createAt) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.balance = balance;
        this.createAt = createAt;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    
    @Override
    public int hashCode() {
        int hash = 17;
        int hashMultiplikator = 79;
        hash = hashMultiplikator * hash + this.rollNumber.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student st = (Student) obj;
            if (this.rollNumber.equals(st.rollNumber)) {
                return true;
            }
        }
        return false;
    }
}
