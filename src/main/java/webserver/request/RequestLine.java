package webserver.request;

import org.springframework.util.Assert;
import webserver.Protocol;

public class RequestLine {
    private final Method method;
    private final Path path;
    private final Protocol protocol;

    private RequestLine(Method method, Path path, Protocol protocol) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
    }


    public static RequestLine from(String line) {
        Assert.notNull(line, "requestLine is null");
        String[] parsedLine = line.trim().split("\\s+");

        return new RequestLine(Method.valueOf(parsedLine[0]), new Path(parsedLine[1]), Protocol.from(parsedLine[2]));
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

    public String getVersion() {
        return protocol.getVersion();
    }
}
