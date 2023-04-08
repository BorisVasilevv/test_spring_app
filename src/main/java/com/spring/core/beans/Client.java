package com.spring.core.beans;

public class Client {

    private long id;
    private String fullName;
    private String city;
    private String greeting;

    public long getId(){return id;}

    public String getFullName(){ return fullName;}


    public String getGreeting(){return greeting;}

    public void setGreeting(String greeting) {this.greeting=greeting;}

    public Client(long id,String name, String city){
        this.id=id;
        this.fullName=name;
        this.city=city;
    }



}
