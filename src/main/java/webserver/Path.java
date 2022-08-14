package webserver;

import com.google.common.base.Objects;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class Path {
    private final String path;
    private final Map<String, String> map = new HashMap<>();

    public Path(String path) {
        Assert.notNull(path, "requestLine is null");

        this.path = path;
        parseQueryString(path);
    }

    public String get(String key) {
        return map.get(key);
    }

    private void parseQueryString(String path) {
        if (path.contains("?")) {
            String queryStringPart = path.split("\\?")[1];

            String[] queryStrings = queryStringPart.split("&");
            putQueryStrings(queryStrings);
        }
    }

    private void putQueryStrings(String[] queryStrings) {
        for (String queryString : queryStrings) {
            String[] splitQueryString = queryString.split("=");
            map.put(splitQueryString[0], splitQueryString[1]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        return Objects.equal(path, path1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }
}
