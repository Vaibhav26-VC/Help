package com.example.help;

public class loginDatabase {
    String loginDatabaseId;
    String loginDatabaseName;
    String loginDatabaseNumber;
    String loginDatabasecity;

    public loginDatabase(){

    }

    public loginDatabase(String loginDatabaseId, String loginDatabaseName, String loginDatabaseNumber, String loginDatabasecity) {
        this.loginDatabaseId = loginDatabaseId;
        this.loginDatabaseName = loginDatabaseName;
        this.loginDatabaseNumber = loginDatabaseNumber;
        this.loginDatabasecity = loginDatabasecity;
    }

    public String getLoginDatabaseId() {
        return loginDatabaseId;
    }

    public String getLoginDatabaseName() {
        return loginDatabaseName;
    }

    public String getLoginDatabaseNumber() {
        return loginDatabaseNumber;
    }

    public String getLoginDatabasecity() {
        return loginDatabasecity;
    }
}
