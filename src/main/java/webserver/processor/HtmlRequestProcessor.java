package webserver.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.RequestParsed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlRequestProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HtmlRequestProcessor.class);

    public HtmlRequestProcessor(RequestParsed requestParsed) {
        super(requestParsed);
    }

    @Override
    public String process() throws IOException {
        byte[] body = Files.readAllBytes(Path.of("src/main/resources/templates" + request.path()));
        return new ResponseFactory.ResponseBuilder()
                .ok200()
                .contentType("text/html")
                .contentLength(body.length)
                .body(body)
                .build();
    }
}
