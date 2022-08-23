package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.processor.RequestProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            RequestProcessor requestProcessor = RequestProcessorFactory.requestProcessor(in);
            String response = requestProcessor.process();

            DataOutputStream dos = new DataOutputStream(out);
            dos.write(response.getBytes(StandardCharsets.UTF_8));
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
