package webserver;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.QueryString;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LoginProcessor extends AbstractRequestProcessor {
    private final QueryString queryString;
    private static final Logger logger = LoggerFactory.getLogger(LoginProcessor.class);

    public LoginProcessor(OutputStream out, String queryString) {
        super(out);
        this.queryString = QueryString.from(queryString);
    }

    @Override
    public void process() {
        String userId = queryString.get("userId");
        String password = queryString.get("password");

        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공
            DataOutputStream dos = new DataOutputStream(out);
            response302HeaderLoginSuccess(dos);
            logger.info("login success {}, userId:{} pwd:{}", user, userId, password);
            return;
        }

        DataOutputStream dos = new DataOutputStream(out);
        response302HeaderLoginFail(dos);
        logger.info("login fail {}, userId:{} pwd:{}", user, userId, password);
    }

    private void response302HeaderLoginFail(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 FOUND\r\n");
            dos.writeBytes("Location: /user/login_failed.html\r\n");
            dos.writeBytes("Set-Cookie: loggedIn=false\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response302HeaderLoginSuccess(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 FOUND\r\n");
            dos.writeBytes("Location: /index.html\r\n");
            dos.writeBytes("Set-Cookie: loggedIn=true\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
