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

public class CssProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CssProcessor.class);

    private String path;

    public CssProcessor(OutputStream out, String path) {
        super(out);
        this.path = path;
    }

    @Override
    public void process() {
        DataOutputStream dos = new DataOutputStream(out);

        try {
            byte[] body = Files.readAllBytes(Path.of("src/main/resources/static" + path));
            response200Header(dos, "text/css", body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
