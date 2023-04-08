package com.spring.core.loggers;

import com.spring.core.beans.Event;

public interface EventLogger {
    void logEvent(Event event);

    String getName();
}
