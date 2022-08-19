package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.OutputStream;

import static webserver.ResponseFactory.response200Header;
import static webserver.ResponseFactory.responseBody;

public class BasicRequestProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BasicRequestProcessor.class);

    public BasicRequestProcessor(OutputStream out) {
        super(out);
    }

    @Override
    public void process() {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] body = "Hello World".getBytes();
        response200Header(dos, body.length);
        responseBody(dos, body);
    }
}
