package webserver.request;

import com.google.common.base.Objects;
import org.springframework.util.Assert;

public class Path {
    private final String path;
    private final QueryString queryString;

    public Path(String path) {
        Assert.notNull(path, "requestLine is null");

        String[] splitPath = path.split("\\?");

        this.path = splitPath[0];
        this.queryString = splitPath.length > 1 ? QueryString.from(splitPath[1]) : QueryString.empty();
    }

    public String get(String key) {
        return queryString.get(key);
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
}
