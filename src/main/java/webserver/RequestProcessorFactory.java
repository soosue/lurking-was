package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.processor.*;
import webserver.request.RequestParsed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static webserver.request.Method.POST;

public class RequestProcessorFactory {
    private static final Logger logger = LoggerFactory.getLogger(RequestProcessorFactory.class);

    public static RequestProcessor requestProcessor(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        RequestParsed requestParsed = RequestParsed.from(br);
        logger.info("requestParsed {}", requestParsed);

        if (requestParsed.isHtmlRequest()) {
            return new HtmlRequestProcessor(requestParsed);

        } else if (requestParsed.hasMethod(POST) && requestParsed.pathStartsWith("/user/create")) {
            return new CreateUserProcessor(requestParsed);

        } else if (requestParsed.pathStartsWith("/user/login")) {
            return new LoginProcessor(requestParsed);

        } else if (requestParsed.pathStartsWith("/user/list")) {
            return new UserListProcessor(requestParsed);

        } else if (requestParsed.pathStartsWith("/css/")) {
            return new CssProcessor(requestParsed);

        } else if (requestParsed.pathStartsWith("/js/")) {
            return new JsProcessor(requestParsed);

        }

        return new BasicRequestProcessor(requestParsed);
    }
}
