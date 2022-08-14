package webserver.request;

public enum Method {
    GET, POST;

    public static Method from(String value) {
        return valueOf(value.toUpperCase());
    }
}
