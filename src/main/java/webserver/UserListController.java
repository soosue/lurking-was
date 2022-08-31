package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;

import static webserver.ResponseFactory.response200Header;

public class UserListController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        String cookieValues = request.getHeader("Cookie");
        String loggedInCookie = extractLoggedInCookie(cookieValues);
        if ("true".equals(loggedInCookie)) {
            UserListHtml userListHtml = new UserListHtml();
            String body = userListHtml.show();

            response.forwardBody(body);
            return;
        }

        // 권한x login.html
        response.sendRedirect("/user/login.html");
    }

    private String extractLoggedInCookie(String cookieValue) {
        if (cookieValue == null) {
            return null;
        }

        String[] cookieTokens = cookieValue.split(";");
        for (String cookieToken : cookieTokens) {
            String[] token = cookieToken.split("=");
            if ("loggedIn".equals(token[0])) {
                return token[1];
            }
        }
        return null;
    }
}
