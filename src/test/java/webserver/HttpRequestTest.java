package webserver;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class HttpRequestTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    void request_get() throws FileNotFoundException {
        String http_request = "GET /user/create?userId=javajigi&password=password&name=JaeSung HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept: */*\r\n" +
                "";

        InputStream in = new FileInputStream(testDirectory + "http_get.txt");
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getPath()).isEqualTo("/user/create");
        assertThat(request.getVersion()).isEqualTo("HTTP/1.1");
        assertThat(request.getHeader("Host")).isEqualTo("localhost");
        assertThat(request.getHeader("Connection")).isEqualTo("keep-alive");
        assertThat(request.getHeader("Accept")).isEqualTo("*/*");
        assertThat(request.getParameter("userId")).isEqualTo("javajigi");
        assertThat(request.getParameter("password")).isEqualTo("password");
        assertThat(request.getParameter("name")).isEqualTo("JaeSung");
    }
    @Test
    void request_post() throws FileNotFoundException {
        String http_request = "POST /user/create HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Accept: */*\r\n" +
                "\r\n" +
                "userId=javajigi&password=password&name=JaeSung";

        InputStream in = new FileInputStream(testDirectory + "http_post.txt");
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/user/create");
        assertThat(request.getVersion()).isEqualTo("HTTP/1.1");
        assertThat(request.getHeader("Host")).isEqualTo("localhost");
        assertThat(request.getHeader("Connection")).isEqualTo("keep-alive");
        assertThat(request.getHeader("Content-Length")).isEqualTo("46");
        assertThat(request.getHeader("Content-Type")).isEqualTo("application/x-www-form-urlencoded");
        assertThat(request.getParameter("userId")).isEqualTo("javajigi");
        assertThat(request.getParameter("password")).isEqualTo("password");
        assertThat(request.getParameter("name")).isEqualTo("JaeSung");
    }
}
