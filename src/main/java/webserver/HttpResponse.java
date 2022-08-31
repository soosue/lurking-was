package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private final DataOutputStream dos;

    private final Map<String, String> headers = new HashMap<>();

    public HttpResponse(OutputStream out) {
        dos = new DataOutputStream(out);
    }

    public void forward(String url) {
        try {
//            byte[] content = Files.readAllBytes(Path.of("./src/main/resources/templates" + url));
            byte[] content = Files.readAllBytes(new File("./webapp" + url).toPath());
            if (url.endsWith(".css")) {
                headers.put("Content-Type", "text/css");
            } else if (url.endsWith(".js")) {
                headers.put("Content-Type", "text/javascript");
            } else {
                headers.put("Content-Type", "text/html;charset=utf-8");
            }
            headers.put("Content-Length", content.length + "");

            write200Header();
            writeBody(content);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void forwardBody(String body) {
        byte[] content = body.getBytes(UTF_8);
        headers.put("Content-Type", "text/html;charset=utf-8");
        headers.put("Content-Length", content.length + "");
        write200Header();
        writeBody(content);
    }

    public void sendRedirect(String url) {
        headers.put("Location", url);
        write302Header();
    }

    private void write200Header() {
        try {
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            writeHeaders();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void write302Header() {
        try {
            dos.writeBytes("HTTP/1.1 301 FOUND\r\n");
            writeHeaders();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void writeHeaders() {
        try {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                dos.writeBytes(key + ": " + headers.get(key) + "\r\n");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void writeBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
