package webserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class HttpSessionTest {
    private HttpSession session;

    @BeforeEach
    void init() {
        session = new HttpSession();
    }

    @Test
    void getId() {
        // 랜덤함을 증명할 수 있을까?
        UUID.fromString(session.getId());
        assertThatNoException();
    }

    @DisplayName("setAttribute, getAttribute, removeAttribute 메서드를 테스트 한다.")
    @Test
    void attributeTest() {
        String value = "soosue";
        session.setAttribute("id", value);

        Object getValue = session.getAttribute("id");
        assertThat(value).isEqualTo(getValue);

        session.removeAttribute("id");

        getValue = session.getAttribute("id");
        assertThat(getValue).isNull();
    }

    @Test
    void invalidate() {
        session.setAttribute("id", "soosue");
        session.setAttribute("name", "yoonsoo");

        session.invalidate();

        assertThat(session.getAttribute("id")).isNull();
        assertThat(session.getAttribute("name")).isNull();
    }
}
