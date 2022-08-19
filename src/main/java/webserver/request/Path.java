package webserver.request;

import com.google.common.base.Objects;
import org.springframework.util.Assert;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Path {
    private final String path;
    private final QueryString queryString;

    public Path(String path) {
        Assert.notNull(path, "requestLine is null");

        String[] splitPath = path.split("\\?");

        this.path = splitPath[0];
        this.queryString = splitPath.length > 1 ? QueryString.from(splitPath[1]) : QueryString.empty();
    }

    public String getQueryString(String key) {
        return URLDecoder.decode(queryString.get(key), StandardCharsets.UTF_8);
    }

    public String getPath() {
        return path;
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

    @Override
    public String toString() {
        return "Path{" +
                "path='" + path + '\'' +
                ", queryString=" + queryString +
                '}';
    }

    public String lastUri() {
        int index = path.lastIndexOf("/");
        return path.substring(index);
    }
}
