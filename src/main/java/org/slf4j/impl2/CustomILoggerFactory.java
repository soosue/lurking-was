package org.slf4j.impl2;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class CustomILoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String name) {
        return new CustomLogger();
    }
}
