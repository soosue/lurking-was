package webserver.processor;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.RequestParsed;

public class LoginProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(LoginProcessor.class);

    public LoginProcessor(RequestParsed requestParsed) {
        super(requestParsed);
    }

    @Override
    public String process() {
        String userId = request.getQuery("userId");
        String password = request.getQuery("password");

        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공
            logger.info("login success {}, userId:{} pwd:{}", user, userId, password);
            return new ResponseFactory.ResponseBuilder()
                    .found302()
                    .location("/index.html")
                    .setCookie("loggedIn=true")
                    .build();
        }

        logger.info("login fail {}, userId:{} pwd:{}", user, userId, password);
        return new ResponseFactory.ResponseBuilder()
                .found302()
                .location("/user/login_failed.html")
                .setCookie("loggedIn=false")
                .build();
    }
}
