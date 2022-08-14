package webserver;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLineTest {
    @Test
    void get_test() {
        RequestLine req = RequestLine.from("GET /users HTTP/1.1");

        assertThat(req.getMethod()).isEqualTo(Method.GET);
        assertThat(req.getPath()).isEqualTo(new Path("/users"));
        assertThat(req.getProtocol()).isEqualTo(Protocol.HTTP);
        assertThat(req.getVersion()).isEqualTo(new Version("1.1"));
    }

    @Test
    void post_test() {
        RequestLine req = RequestLine.from("POST /users HTTP/1.1");

        assertThat(req.getMethod()).isEqualTo(Method.POST);
        assertThat(req.getPath()).isEqualTo(new Path("/users"));
        assertThat(req.getProtocol()).isEqualTo(Protocol.HTTP);
        assertThat(req.getVersion()).isEqualTo(new Version("1.1"));
    }
}
