package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Override
    public final void service(HttpRequest request, HttpResponse response) {
        if (request.isPost()) {
            doPost(request, response);
        }
        doGet(request, response);
    }

    protected void doGet(HttpRequest request, HttpResponse response) {};
    protected void doPost(HttpRequest request, HttpResponse response) {};
}
