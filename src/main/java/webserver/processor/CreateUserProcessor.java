package webserver.processor;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.ResponseFactory;
import webserver.request.RequestParsed;

public class CreateUserProcessor extends AbstractRequestProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserProcessor.class);

    public CreateUserProcessor(RequestParsed request) {
        super(request);
    }

    @Override
    public String process() {
        User user = new User(request.getQuery("userId"),
                request.getQuery("password"),
                request.getQuery("name"),
                request.getQuery("email")
        );

        DataBase.addUser(user);
        logger.info("create user {}", user);

        return new ResponseFactory.ResponseBuilder()
                .found302()
                .location("/index.html")
                .build();
//        return ResponseFactory.response302Header("/index.html");
    }
}
