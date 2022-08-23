package webserver.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.Cookie;
import webserver.request.RequestParsed;

import java.util.Arrays;

public class UserListProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(UserListProcessor.class);

    public UserListProcessor(RequestParsed requestParsed) {
        super(requestParsed);
    }

    @Override
    public String process() {
        String cookieValue = request.getHeader("Cookie");
        Cookie[] cookies = Cookie.from(cookieValue);
        Cookie loggedInCookie = extractLoggedInCookie(cookies);

        if ("true".equals(loggedInCookie.getValue())) {
            logger.info("you are loggedIn");
            UserListHtml userListHtml = new UserListHtml();
            String body = userListHtml.show();

            return new ResponseFactory.ResponseBuilder()
                    .ok200()
                    .contentType("text/html")
                    .contentLength(body.length())
                    .body(body)
                    .build();
        }
        logger.info("you are not loggedIn");

        return new ResponseFactory.ResponseBuilder()
                .found302()
                .location("/user/login.html")
                .build();
    }

    private Cookie extractLoggedInCookie(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getKey().equals("loggedIn"))
                .findFirst()
                .orElse(Cookie.empty());
    }
}
