package org.slf4j.impl2;

import org.slf4j.ILoggerFactory;

public class StaticLoggerBinder {
    private static StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    private final ILoggerFactory iLoggerFactory = new CustomILoggerFactory();

    public static StaticLoggerBinder getSingleton() {
        return new StaticLoggerBinder();
    }

    public ILoggerFactory getLoggerFactory() {
        return iLoggerFactory;
    }
}
