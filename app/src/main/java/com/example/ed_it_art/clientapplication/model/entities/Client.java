package com.example.ed_it_art.clientapplication.model.entities;

/**
 * Created by USER on 03/12/2017.
 */

public class Client {
    private String name;
    private String last_name;
    private long _id;
    private String mail;
    private long credit_number;

    private String user_name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getCredit_number() {
        return credit_number;
    }

    public void setCredit_number(long credit_number) {
        this.credit_number = credit_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client() {
    }

    public Client(String name, String family,
                  long ID, String mail,
                  long creditNumber,
                  String userName, String password) {
        this.name = name;
        last_name = family;
        this._id = ID;
        this.mail = mail;
        credit_number = creditNumber;
        user_name = userName;
        this.password = password;
    }
}
