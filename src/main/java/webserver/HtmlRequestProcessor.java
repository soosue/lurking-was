package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static webserver.ResponseFactory.response200Header;
import static webserver.ResponseFactory.responseBody;

public class HtmlRequestProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HtmlRequestProcessor.class);

    private final String path;

    public HtmlRequestProcessor(OutputStream out, String path) {
        super(out);
        this.path = path;
    }

    @Override
    public void process() {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] body = new byte[0];
        try {
            body = Files.readAllBytes(Path.of("src/main/resources/templates" + path));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        response200Header(dos, body.length);
        responseBody(dos, body);
    }
}
