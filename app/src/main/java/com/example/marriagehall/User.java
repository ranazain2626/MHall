package com.example.marriagehall;

public class User {
    private String email,password,username,name,address,number;
    private int usertype;

    public User(String email, String password,String username,String name,String address,String number,int usertype){
        this.email=email;
        this.password=password;
        this.username=username;
        this.usertype=usertype;
        this.name=name;
        this.address=address;
        this.number=number;


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address =address ;
    }
    public void setNumber(String number) {
        this.number = number;
    }


    public void  setUsertype(int usertype){
        this.usertype= usertype;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username =username ;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public  int getUsertype(){
        return usertype;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }



}
