package webserver.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeader {
    private static final Logger logger = LoggerFactory.getLogger(RequestHeader.class);

    private final Map<String, String> headers;

    public RequestHeader(Map<String, String> headers) {
        this.headers = headers;
    }

    public static RequestHeader from(BufferedReader br) throws IOException {
        Map<String, String> headers = new HashMap<>();
        parse(br, headers);
        return new RequestHeader(headers);
    }

    private static void parse(BufferedReader br, Map<String, String> headers) throws IOException {
        String header = "";
        while (!"".equals(header = br.readLine())) {
            String[] headerSplit = header.split(":");
            headers.put(headerSplit[0].trim(), headerSplit[1].trim());
            logger.info("header {}", header);
        }
    }

    public int bodyLength() {
        return Integer.parseInt(
                headers.getOrDefault("Content-Length", "0")
        );
    }

    public String get(String key) {
        return headers.get(key);
    }
}
