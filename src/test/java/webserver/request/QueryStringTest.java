package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QueryStringTest {
    @DisplayName("QueryString 을 파싱한다.")
    @Test
    void parse_queryString() {
        QueryString queryString = QueryString.from("name=soo");
        assertThat(queryString.get("name")).isEqualTo("soo");
    }

    @DisplayName("QueryStrings 을 파싱한다.")
    @Test
    void parse_queryStrings() {
        QueryString queryString = QueryString.from("name=soo&value=good");
        assertAll(
                () -> assertThat(queryString.get("name")).isEqualTo("soo"),
                () -> assertThat(queryString.get("value")).isEqualTo("good")
        );
    }

    @DisplayName("QueryString 미 존재 시 get 하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "name=soo", "name"})
    void get_invalid_queryString(String value) {
        QueryString queryString = QueryString.from(value);
        assertThatThrownBy(
                () -> queryString.get("invalidKey")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
