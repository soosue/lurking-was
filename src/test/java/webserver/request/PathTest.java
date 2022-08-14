package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {
    @DisplayName("Path 에 QueryString 이 없을 때 없게 path")
    @ParameterizedTest
    @ValueSource(strings = {"/users", "/users?"})
    void no_queryString_path(String value) {
        Path path = new Path(value);
        assertThat(path.getPath()).isEqualTo("/users");
    }

    @DisplayName("Path 에 QueryString 이 있을 때 없게 path")
    @Test
    void queryString_path() {
        Path path = new Path("/users?name1=value1&name2=value2");
        assertThat(path.getPath()).isEqualTo("/users");
    }
}
