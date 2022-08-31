package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        logger.info("url: {}",request.getPath());
        response.forward(request.getPath());
    }
}
