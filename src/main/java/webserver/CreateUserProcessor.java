package webserver;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.QueryString;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CreateUserProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserProcessor.class);

    private final QueryString queryString;

    public CreateUserProcessor(OutputStream out, String body) {
        super(out);
        this.queryString = QueryString.from(body);
    }

    @Override
    public void process() {
        User user = new User(queryString.get("userId"),
                queryString.get("password"),
                queryString.get("name"),
                queryString.get("email")
        );

        DataBase.addUser(user);
        logger.info("create user {}", user);

        DataOutputStream dos = new DataOutputStream(out);

        response302Header(dos);
    }

    private void response302Header(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found\r\n");
            dos.writeBytes("Location: /index.html");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
