package webserver;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공
            logger.info("login success {}, userId:{} pwd:{}", user, userId, password);
            loginSuccess(response);
            return;
        }

        // 로그인 실패
        logger.info("login fail {}, userId:{} pwd:{}", user, userId, password);
        loginFail(response);
    }

    private void loginFail(HttpResponse response) {
        response.addHeader("Set-Cookie", "loggedIn=fail");
        response.sendRedirect("/user/login_failed.html");
    }

    private void loginSuccess(HttpResponse response) {
        response.addHeader("Set-Cookie", "loggedIn=true");
        response.sendRedirect("/index.html");
    }
}
