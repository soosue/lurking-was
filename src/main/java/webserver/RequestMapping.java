package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(RequestMapping.class);

    private static Map<String, Controller> controllers = new HashMap<>() {{
        put("/user/login", new LoginController());
        put("/user/list", new UserListController());
        put("/user/create", new CreateUserController());
    }};
    private static Controller defaultController = new DefaultController();

    public static Controller getController(HttpRequest request) {
        return controllers.getOrDefault(request.getPath(), defaultController);
    }
}
