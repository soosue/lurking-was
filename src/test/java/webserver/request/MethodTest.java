package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MethodTest {
    @DisplayName("GET 문자열로 Method enum 을 생성할 수 있다.")
    @Test
    void create_get() {
        assertThat(Method.from("GET"))
                .isEqualTo(Method.GET);
    }

    @DisplayName("POST 문자열로 Method enum 을 생성할 수 있다.")
    @Test
    void create_post() {
        assertThat(Method.from("POST"))
                .isEqualTo(Method.POST);
    }

    @DisplayName("부적절한 문자열일 경우 예외가 발생한다.")
    @Test
    void invalid_value() {
        assertThatThrownBy(
                () -> Method.from("INVALID")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
