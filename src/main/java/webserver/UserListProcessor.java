package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import static webserver.ResponseFactory.response200Header;

public class UserListProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(UserListProcessor.class);
    private final BufferedReader br;
    public UserListProcessor(BufferedReader br, OutputStream out) {
        super(out);
        this.br = br;
    }

    @Override
    public void process() {
        Cookie cookie = extractCookie();
        if (cookie.getValue().equals("true")) {
            UserListHtml userListHtml = new UserListHtml();
            String body = userListHtml.show();


            DataOutputStream dos = new DataOutputStream(out);
            response200Header(dos, body.length());
            responseBody(dos, body);
        }

        // 권한x login.html
        DataOutputStream dos = new DataOutputStream(out);
        response302Header(dos);
    }

    private void responseBody(DataOutputStream dos, String body) {
        try {
            dos.writeBytes(body);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response302Header(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found\r\n");
            dos.writeBytes("Location: /user/login.html");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private Cookie extractCookie() {
        String cookieKeyValue = extractCookieKeyValue();
        Cookie[] cookies = Cookie.from(cookieKeyValue);
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getKey().equals("loggedIn"))
                .findFirst()
                .orElse(Cookie.empty());
    }

    private String extractCookieKeyValue() {
        try {
            String header = "";
            while (!"".equals((header = br.readLine()))) {
                if (header.contains("Cookie")) {
                    int idx = header.indexOf(' ');
                    return header.substring(idx + 1);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
