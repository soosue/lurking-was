package webserver;

import com.google.common.base.Objects;

public class Path {
    private final String path;
    public Path(String path) {
        this.path = path;
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
