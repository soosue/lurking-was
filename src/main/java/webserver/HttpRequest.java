package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    private String method;
    private String path;
    private String version;
    private Map<String, String> params;
    private Map<String, String> headers;

    public HttpRequest(InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF_8));
            String line = br.readLine();

            parseRequestLine(line);

            parseHeader(br);

            parseBody(br);


        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void parseBody(BufferedReader br) throws IOException {
        if ("POST".equals(method)) {
            String body = br.readLine();
            params = parseQueryString(body);
        }
    }

    private void parseHeader(BufferedReader br) throws IOException {
        headers = new HashMap<>();

        String header = "";
        while (!"".equals(header = br.readLine())) {
            String[] headerTokens = header.split(":");
            headers.put(headerTokens[0], headerTokens[1].trim());
        }

    }

    private void parseRequestLine(String line) {
        String[] tokens = line.split(" ");
        method = tokens[0];
        version = tokens[2];

        if ("POST".equals(method)) {
            path = tokens[1];
            return;
        }

        int paramIndex = tokens[1].indexOf("?");
        if (paramIndex == -1) {
            path = tokens[1];
            return;
        }

        path = tokens[1].substring(0, paramIndex);
        params = parseQueryString(tokens[1].substring(paramIndex + 1));
    }

    private Map<String, String> parseQueryString(String params) {
        HashMap<String, String> paramMap = new HashMap<>();

        String[] paramTokens = params.split("&");
        for (String paramToken : paramTokens) {
            String[] tokens = paramToken.split("=");
            paramMap.put(tokens[0], tokens[1].trim());
        }

        return paramMap;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return params.get(key);
    }

    public String getVersion() {
        return version;
    }
}
