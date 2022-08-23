package webserver.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.RequestParsed;

public class BasicRequestProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BasicRequestProcessor.class);

    public BasicRequestProcessor(RequestParsed out) {
        super(out);
    }

    @Override
    public String process() {
        logger.info("default");
        byte[] body = "Hello World".getBytes();
//        String body = "Hello World";
        return new ResponseFactory.ResponseBuilder()
                .ok200()
                .contentType("text/html")
                .contentLength(body.length)
                .body(body)
                .build();
    }
}
