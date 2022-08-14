package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.request.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProtocolTest {
    @DisplayName("문자열 HTTP/1.1로 Protocol enum 을 생성할 수 있다.")
    @Test
    void create_http_1_1() {
        assertThat(Protocol.from("HTTP/1.1"))
                .isEqualTo(Protocol.HTTP_1_1);
    }

    @DisplayName("부적절한 문자열일 경우 예외가 발생한다.")
    @Test
    void invalid_value() {
        assertThatThrownBy(
                () -> Method.from("INVALID")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
