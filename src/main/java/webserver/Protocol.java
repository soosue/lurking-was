package webserver;

import java.util.Arrays;

public enum Protocol {
    HTTP_1_1("HTTP", "1.1");

    private final String name;
    private final String version;

    Protocol(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public static Protocol from(String value) {
        return Arrays.stream(values())
                .filter(protocol -> protocol.toString().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no protocol likes " + value));
    }

    @Override
    public String toString() {
        return String.format("%s/%s", name, version);
    }
}
