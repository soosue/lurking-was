package webserver;

import org.springframework.util.Assert;

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
        Assert.notNull(line, "requestLine is null");
        String[] parsedLine = line.trim().split("\\s+");

        String[] protocolAndVersion = parsedLine[2].split("/");
        String protocol = protocolAndVersion[0];
        String version = protocolAndVersion[1];

        return new RequestLine(Method.valueOf(parsedLine[0]), new Path(parsedLine[1]), Protocol.valueOf(protocol), new Version(version));
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
