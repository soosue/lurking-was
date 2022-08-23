package webserver.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.RequestParsed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CssProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CssProcessor.class);

    public CssProcessor(RequestParsed requestParsed) {
        super(requestParsed);
    }

    @Override
    public String process() throws IOException {
        logger.info("read css {}", request.path());

        byte[] body = Files.readAllBytes(Path.of("src/main/resources/static" + request.path()));
        return new ResponseFactory.ResponseBuilder()
                .ok200()
                .contentType("text/css")
                .contentLength(body.length)
                .body(body)
                .build();
    }
}