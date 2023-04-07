package com.spring.core;

import com.spring.core.beans.Client;
import com.spring.core.beans.Event;
import com.spring.core.beans.Eventype;
import com.spring.core.loggers.CacheFileEventLogger;
import com.spring.core.loggers.ConsoleEventLogger;
import com.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;

    private Map<Eventype,EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<Eventype,EventLogger> map){
        this.client=client;
        this.defaultLogger=eventLogger;
        this.loggers=map;
    }


    public App(){}

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx= new ClassPathXmlApplicationContext("some.xml", "loggers.xml");
        App app=(App) ctx.getBean("app");
        Event e=(Event)ctx.getBean("event");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Event e1=(Event)ctx.getBean("event");
        e.setMessage("wfegr");
        e1.setMessage("message to 1");




        app.logEvent(Eventype.INFO,e);
        app.logEvent(Eventype.INFO,e1);


        ctx.close();


    }

    /*private void logEvent(String message){
        message=message.replaceAll(String.valueOf(client.getId()),client.getFullName());
        eventLogger.logEvent(new Event(message));
    }*/

    private void logEvent(Event event){
        defaultLogger.logEvent(event);
    }

    private void logEvent(Eventype eventype, Event event){
        EventLogger logger= loggers.get(eventype);
        if(logger==null){
            logger=defaultLogger;
        }
        logger.logEvent(event);
    }

}
