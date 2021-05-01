package com.capgemini.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component /*it will detect our custom beans*/
@Scope(scopeName = "prototype") /* keeping this as prototype*/
@Entity /* Creating table*/
@Table(name = "Admin") /* Specifying table name*/
public class Admin {

 

    @Id /* defining primary key*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* generating value automatically*/
    @Column(name = "Admin_Id") // specifying column name
    private int adminId;

 

    /* specifying column name, giving length and giving
    constraint as not null*/
    @Column(name = "Admin_Name", length = 25, nullable = false) 
    private String adminName;

 

    /* specifying column name, giving length and giving
    constraint as not null*/
    @Column(name = "Admin_Username", length = 25, nullable = false) 
    private String adminUsername;

 

    /* specifying column name, giving length and giving
    constraint as not null*/
    @Column(name = "Admin_Password", length = 10, nullable = false)
    private String adminPassword;
    
 

    /* creating parameterized constructor*/
    public Admin(String adminName, String adminUsername, String adminPassword) {
        super();
        
        this.adminName = adminName;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    /* creating default constructor*/
    public Admin() {

    }

    /* creating getters and setters*/
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /* creating tostring method*/
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminUsername=" + adminUsername
                + ", adminPassword=" + adminPassword + "]";
    }

}