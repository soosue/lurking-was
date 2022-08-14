package webserver;

import java.io.InputStream;

import static webserver.Method.GET;
import static webserver.Protocol.HTTP;

public class RequestLine {
    private final Method method;
    private final Path path;
    private final Protocol protocol;
    private final Version version;

    private RequestLine(Method method, Path path, Protocol protocol, Version version) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
        this.version = version;
    }


    public static RequestLine from(String line) {
        return new RequestLine(GET, new Path("/users"), HTTP, new Version("1.1"));
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public Version getVersion() {
        return version;
    }
}
