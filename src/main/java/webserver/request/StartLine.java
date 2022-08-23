package webserver.request;

import webserver.Protocol;

public class StartLine {
    private final Method method;
    private final Path path;
    private final Protocol protocol;

    public StartLine(Method method, Path path, Protocol protocol) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
    }

    public static StartLine from (String startLine) {
        String[] parsedLine = startLine.trim().split("\\s+");
        return new StartLine(
                Method.valueOf(parsedLine[0]),
                new Path(parsedLine[1]),
                Protocol.from(parsedLine[2])
        );
    }

    public Method method() {
        return method;
    }

    public Path path() {
        return path;
    }

    public Protocol protocol() {
        return protocol;
    }

    public String version() {
        return protocol.getVersion();
    }

    public boolean isHtmlRequest() {
        return path.isHtmlRequest();
    }

    public String pathString() {
        return path.getPath();
    }

    public boolean pathStartsWith(String path) {
        return this.path.startsWith(path);
    }

}
