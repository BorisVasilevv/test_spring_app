package com.spring.core;

import com.spring.core.aspects.StatisticAspect;
import com.spring.core.beans.Client;
import com.spring.core.beans.Event;
import com.spring.core.beans.EventType;
import com.spring.core.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;

    private Map<EventType,EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType,EventLogger> map){
        this.client=client;
        this.defaultLogger=eventLogger;
        this.loggers=map;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx= new ClassPathXmlApplicationContext("main.xml", "loggers.xml");
        App app=(App) ctx.getBean("app");
        Event e=(Event)ctx.getBean("event");
        Event.isDay();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }*/

        Event e1=(Event)ctx.getBean("event");
        e.setMessage("wfegr");
        e1.setMessage("message to 1");

        app.logEvent(EventType.WARNING,e);
        app.logEvent(EventType.INFO,e1);
        app.logEvent(null, new Event("efr"));
        app.logEvent(EventType.ERROR, new Event("error"));

        Map<Class<?>, Integer> integerMap= ((StatisticAspect)ctx.getBean("statisticAspect")).getStatistic();

        ctx.close();
        for (Class<?> key:integerMap.keySet()) {
            System.out.print(key.getName());
            System.out.print(" – ");
            System.out.println(integerMap.get(key));
        }



    }

    /*private void logEvent(String message){
        message=message.replaceAll(String.valueOf(client.getId()),client.getFullName());
        eventLogger.logEvent(new Event(message));
    }*/

    private void logEvent(Event event){
        defaultLogger.logEvent(event);
    }

    private void logEvent(EventType eventType, Event event){
        event.setMessage(event.getMessage().replaceAll(String.valueOf(client.getId()), client.getFullName()));
        EventLogger logger= loggers.get(eventType);
        if(logger==null){
            logger=defaultLogger;
        }
        logger.logEvent(event);
    }

}
