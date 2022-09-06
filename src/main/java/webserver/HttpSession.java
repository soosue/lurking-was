package webserver;

import java.util.UUID;

public class HttpSession {
    private final String id;

    public HttpSession() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
