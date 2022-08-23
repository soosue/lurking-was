package webserver.request;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryString {
    private final Map<String, String> queryString;

    private QueryString(Map<String, String> queryString) {
        this.queryString = queryString;
    }

    private QueryString(String value) {
        this(parseQueryString(value));
    }

    public static QueryString empty() {
        return new QueryString(Collections.emptyMap());
    }

    public static QueryString from(String value) {
        return new QueryString(value);
    }

    private static Map<String, String> parseQueryString(String value) {
        Assert.notNull(value, "value is null");

        if (!value.contains("&") && !value.contains("=")) {
            return Collections.emptyMap();
        }

        String[] queryStrings = value.split("&");

        return Arrays.stream(queryStrings)
                .map(queryString -> queryString.split("="))
                .collect(Collectors.toUnmodifiableMap(
                        split -> split[0],
                        split -> split[1]
                ));
    }

    public String get(String key) {
        return queryString.getOrDefault(key, "");
    }

    @Override
    public String toString() {
        return "QueryString{" +
                "queryString=" + queryString +
                '}';
    }
}
