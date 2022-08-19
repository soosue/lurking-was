package webserver;

import java.io.OutputStream;

public abstract class AbstractRequestProcessor implements RequestProcessor {
    protected final OutputStream out;

    public AbstractRequestProcessor(OutputStream out) {
        this.out = out;
    }
}
