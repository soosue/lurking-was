package webserver;

import com.google.common.base.Objects;

public class Cookie {
    private final String key;
    private final String value;

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Cookie empty() {
        return new Cookie("", "");
    }

    public static Cookie[] from(String cookieKeyValue) {
        if (cookieKeyValue == null || cookieKeyValue.isEmpty() || cookieKeyValue.isBlank()) {
            return new Cookie[0];
        }


        String[] split = cookieKeyValue.split(";");
        Cookie[] cookies = new Cookie[split.length];

        for (int i = 0; i < cookies.length; i++) {
            String[] keyValue = split[i].trim().split("=");
            cookies[i] = new Cookie(keyValue[0], keyValue[1]);
        }

        return cookies;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cookie cookie = (Cookie) o;
        return Objects.equal(key, cookie.key) && Objects.equal(value, cookie.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key, value);
    }
}
