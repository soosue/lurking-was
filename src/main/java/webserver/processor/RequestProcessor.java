package webserver.processor;

import java.io.IOException;

public interface RequestProcessor {
    String process() throws IOException;
}
