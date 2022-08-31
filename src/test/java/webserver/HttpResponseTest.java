package webserver;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

class HttpResponseTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    void responseForward() throws FileNotFoundException {
        HttpResponse response = new HttpResponse(createOutputStream("http_forward.txt"));
        response.forward("/index.html");
    }

    @Test
    void responseRedirect() throws FileNotFoundException {
        HttpResponse response = new HttpResponse(createOutputStream("http_redirect.txt"));
        response.sendRedirect("/index.html");
    }

    @Test
    void responseCookies() throws FileNotFoundException {
        HttpResponse response = new HttpResponse(createOutputStream("http_cookie.txt"));
        response.addHeader("Set-Cookie", "loggedIn=true");
        response.sendRedirect("/index.html");
    }

    private OutputStream createOutputStream(String filename) throws FileNotFoundException {
        return new FileOutputStream(testDirectory + filename);
    }
}
