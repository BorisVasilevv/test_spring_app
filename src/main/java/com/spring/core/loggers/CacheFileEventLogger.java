package com.spring.core.loggers;

import com.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger implements EventLogger{


    private int cacheSize;

    private List<Event> cache;


    public CacheFileEventLogger( String filename, int cacheSize){
        super(filename);
        this.cacheSize=cacheSize;
        cache=new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size()==cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    /*@Override
    protected void init() throws IOException {
        super.init();
    }*/


    private void destroy(){
        if(!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }


    private void writeEventsFromCache(){
        for (Event e:cache) {
            super.logEvent(e);
        }
    }
}
