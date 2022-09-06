package webserver;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNoException;

class HttpSessionTest {

    @Test
    void getId() {
        HttpSession session = new HttpSession();

        // 랜덤함을 증명할 수 있을까?
        UUID.fromString(session.getId());
        assertThatNoException();
    }
}
