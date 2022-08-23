package webserver.request;

import org.junit.jupiter.api.Test;
import webserver.Protocol;

import static org.assertj.core.api.Assertions.assertThat;

class StartLineTest {
    @Test
    void get_test() {
        StartLine startLine = StartLine.from("GET /users HTTP/1.1");

        assertThat(startLine.method()).isEqualTo(Method.GET);
        assertThat(startLine.path()).isEqualTo(new Path("/users"));
        assertThat(startLine.protocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(startLine.version()).isEqualTo("1.1");
    }

    @Test
    void post_test() {
        StartLine req = StartLine.from("POST /users HTTP/1.1");

        assertThat(req.method()).isEqualTo(Method.POST);
        assertThat(req.path()).isEqualTo(new Path("/users"));
        assertThat(req.protocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(req.version()).isEqualTo("1.1");
    }

    @Test
    void queryString_test() {
        StartLine req = StartLine.from("GET /users?name1=value1&name2=value2 HTTP/1.1");

        assertThat(req.method()).isEqualTo(Method.GET);
        assertThat(req.path()).isNotNull();
        assertThat(req.path().getQueryString("name1")).isEqualTo("value1");
        assertThat(req.path().getQueryString("name2")).isEqualTo("value2");
        assertThat(req.protocol()).isEqualTo(Protocol.HTTP_1_1);
        assertThat(req.version()).isEqualTo("1.1");
    }
}
