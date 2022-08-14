package webserver.request;

import org.junit.jupiter.api.Test;
import webserver.Protocol;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLineTest {
    @Test
    void get_test() {
        RequestLine req = RequestLine.from("GET /users HTTP/1.1");

        assertThat(req.getMethod()).isEqualTo(Method.GET);
        assertThat(req.getPath()).isEqualTo(new Path("/users"));
        assertThat(req.getProtocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(req.getVersion()).isEqualTo("1.1");
    }

    @Test
    void post_test() {
        RequestLine req = RequestLine.from("POST /users HTTP/1.1");

        assertThat(req.getMethod()).isEqualTo(Method.POST);
        assertThat(req.getPath()).isEqualTo(new Path("/users"));
        assertThat(req.getProtocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(req.getVersion()).isEqualTo("1.1");
    }

    @Test
    void queryString_test() {
        RequestLine req = RequestLine.from("GET /users?name1=value1&name2=value2 HTTP/1.1");

        assertThat(req.getMethod()).isEqualTo(Method.GET);
        assertThat(req.getPath()).isNotNull();
        assertThat(req.getPath().get("name1")).isEqualTo("value1");
        assertThat(req.getPath().get("name2")).isEqualTo("value2");
        assertThat(req.getProtocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(req.getVersion()).isEqualTo("1.1");
    }
}
