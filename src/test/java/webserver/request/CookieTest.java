package webserver.request;

import org.junit.jupiter.api.Test;
import webserver.Cookie;

import static org.assertj.core.api.Assertions.assertThat;

class CookieTest {
    @Test
    void makeCookies() {
//        String cookieHeader = "Cookie: loggedIn=false; key=value; Idea-faae9773=1b80071d-8558-4a0c-9057-1be12b0cf5dc";
        String cookieKeyValues = "loggedIn=false; key=value; Idea-faae9773=1b80071d-8558-4a0c-9057-1be12b0cf5dc";
        String singleCookie = "loggedIn=false";

        Cookie[] cookies = Cookie.from(cookieKeyValues);

        assertThat(cookies).contains(
                new Cookie("loggedIn", "false"),
                new Cookie("key", "value"),
                new Cookie("Idea-faae9773", "1b80071d-8558-4a0c-9057-1be12b0cf5dc")
        );
    }

//    @Test
    void makeCookies세미콜론() {
//        String cookieHeader = "Cookie: loggedIn=false; key=value; Idea-faae9773=1b80071d-8558-4a0c-9057-1be12b0cf5dc";
        String cookieKeyValues = "loggedIn=false; key=value; Idea-faae9773=1b80071d-8558-4a0c-9057-1be12b0cf5dc";
        String singleCookie = "loggedIn=false";

        Cookie[] cookies = Cookie.from(cookieKeyValues);

        assertThat(cookies).contains(
                new Cookie("loggedIn", "false"),
                new Cookie("key", "value"),
                new Cookie("Idea-faae9773", "1b80071d-8558-4a0c-9057-1be12b0cf5dc")
        );
    }

    @Test
    void makeCookie() {
        String cookieKeyValues = "loggedIn=false";

        Cookie[] cookies = Cookie.from(cookieKeyValues);

        assertThat(cookies).contains(
                new Cookie("loggedIn", "false")
        );
    }

}
