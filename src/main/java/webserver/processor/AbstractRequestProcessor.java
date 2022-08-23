package webserver.processor;

import webserver.request.RequestParsed;

public abstract class AbstractRequestProcessor implements RequestProcessor {
    protected final RequestParsed request;

    public AbstractRequestProcessor(RequestParsed requestParsed) {
        this.request = requestParsed;
    }
}
