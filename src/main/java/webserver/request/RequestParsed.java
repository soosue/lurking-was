package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParsed {
    private final StartLine startLine;
    private final RequestHeader header;
    private final RequestBody body;

    public RequestParsed(StartLine startLine, RequestHeader header, RequestBody body) {
        this.startLine = startLine;
        this.header = header;
        this.body = body;
    }

    public static RequestParsed from(BufferedReader br) throws IOException {
        StartLine startLine = StartLine.from(br.readLine());
        RequestHeader requestHeader = RequestHeader.from(br);
        RequestBody requestBody = RequestBody.from(br, requestHeader.bodyLength());
        return new RequestParsed(startLine, requestHeader, requestBody);
    }

    public boolean isHtmlRequest() {
        return startLine.isHtmlRequest();
    }

    public String path() {
        return startLine.pathString();
    }

    public boolean hasMethod(Method method) {
        return startLine.method() == method;
    }

    public boolean pathStartsWith(String path) {
        return startLine.pathStartsWith(path);
    }

    public int bodyLength() {
        return header.bodyLength();
    }

    public String getQuery(String key) {
        return body.getQuery(key);
    }

    public String getHeader(String key) {
        return header.get(key);
    }

    @Override
    public String toString() {
        return "RequestParsed{" +
                "startLine=" + startLine +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
