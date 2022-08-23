package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ResponseFactory {
    private static final Logger logger = LoggerFactory.getLogger(ResponseFactory.class);

    public enum ContentType {
        TEXT_HTML("text/html"),
        TEXT_CSS("text/css"),
        TEXT_JAVASCRIPT("text/javascript");

        private final String type;

        ContentType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public static class ResponseBuilder {
        private final StringBuilder sb = new StringBuilder();

        public ResponseBuilder ok200() {
            sb.append("HTTP/1.1 200 OK\r\n");
            return this;
        }

        public ResponseBuilder found302() {
            sb.append("HTTP/1.1 302 Found\r\n");
            return this;
        }

        public ResponseBuilder location(String location) {
            sb.append("Location: " + location + "\r\n");
            return this;
        }

        public ResponseBuilder contentType(String type) {
            sb.append("Content-Type: " + type + ";charset=utf-8\r\n");
            return this;
        }

        public ResponseBuilder contentType(ContentType type) {
            sb.append("Content-Type: " + type + ";charset=utf-8\r\n");
            return this;
        }

        public ResponseBuilder contentLength(int length) {
            sb.append("Content-Length: " + length + "\r\n");
            return this;
        }

        public ResponseBuilder setCookie(String cookie) {
            sb.append("Set-Cookie: " + cookie + "\r\n");
            return this;
        }

        public ResponseBuilder body(String body) {
            sb.append("\r\n");
            sb.append(body);
            return this;
        }

        public String build() {
            return sb.toString();
        }

        public ResponseBuilder body(byte[] body) {
            return body(new String(body, StandardCharsets.UTF_8));
        }
    }
}
