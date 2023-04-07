package com.spring.core.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class Event {

    private static long eventCounter=0;
    private long id;
    private String message;
    private Date date;

    private DateFormat df;

    public Event(String message){
        this.id=eventCounter++;
        this.message=message;
        //this.date= Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        //df= new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    }

    public Event(String message,Date date){
        this.id=eventCounter++;
        this.message=message;
        this.date=date;
    }

    public Event(Date date){
        this.date=date;
        this.id=eventCounter++;
    }

    public Event(Date date,DateFormat format){
        this.date=date;
        this.id=eventCounter++;
        this.df=format;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){this.message=message; }

    @Override
    public String toString(){
        return String.format("Event\nid=%d\nmessage=%s\ndate=%s\n", id, message, df.format(date));
    }

}
