package webserver.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class RequestBody {
    private static final Logger logger = LoggerFactory.getLogger(RequestBody.class);

    private final QueryString queryString;

    public RequestBody(String body) {
        this.queryString = QueryString.from(body);
    }

    public static RequestBody from(BufferedReader br, int bodyLength) throws IOException {
        char[] body = new char[bodyLength];
        br.read(body, 0, bodyLength);

        String bodyString = URLDecoder.decode(String.copyValueOf(body), StandardCharsets.UTF_8);
        logger.info("body {}", bodyString);

        return new RequestBody(bodyString);
    }

    public String getQuery(String key) {
        return queryString.get(key);
    }
}
