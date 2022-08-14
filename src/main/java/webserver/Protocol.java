package webserver;

public enum Protocol {
    HTTP_1_1("1.1");

    private final String version;

    Protocol(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public static Protocol from(String value) {
        String[] protocolAndVersion = value.split("/");
        String protocol = protocolAndVersion[0];
        String version = protocolAndVersion[1];

        if ("HTTP".equals(protocol) && "1.1".equals(version)) {
            return HTTP_1_1;
        }
        throw new IllegalArgumentException("no protocol likes " + value);
    }
}
