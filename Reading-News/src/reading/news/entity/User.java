/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.entity;

/**
 *
 * @author biidz
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private long createdTime;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id, String username, String password, String email, long createdTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

}
