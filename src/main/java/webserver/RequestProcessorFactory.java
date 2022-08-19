package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.Method;
import webserver.request.RequestLine;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class RequestProcessorFactory {
    private static final Logger logger = LoggerFactory.getLogger(RequestProcessorFactory.class);

    public static RequestProcessor requestProcessor(InputStream in, OutputStream out) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        RequestLine requestParsed = RequestLine.from(br.readLine());
        logger.info("requestParsed {}", requestParsed);

        if (requestParsed.getPath().lastUri().contains(".html")) {
            return new HtmlRequestProcessor(out, requestParsed.getPath().getPath());
        } else if (requestParsed.getMethod() == Method.POST && requestParsed.getPath().getPath().equals("/user/create")) {
            String header  = "";
            int contentLength = 0;
            while (!"".equals(header = br.readLine())) {
                if (header.contains("Content-Length")) {
                    contentLength = Integer.parseInt(header.split(" ")[1]);
                }
                logger.info("header {}", header);
            }


            char[] body = new char[contentLength];
            br.read(body, 0, contentLength);
            String body2 = URLDecoder.decode(String.copyValueOf(body), StandardCharsets.UTF_8);
//            String body2 = br.readLine();
            logger.info("body {}", body2);
            return new CreateUserProcessor(out, body2);
        } else if (requestParsed.getPath().getPath().equals("/user/login")) {
            String header = "";
            int contentLength = 0;
            while (!"".equals(header = br.readLine())) {
                if (header.contains("Content-Length")) {
                    contentLength = Integer.parseInt(header.split(" ")[1]);
                }
                logger.info("header {}", header);
            }

            char[] body = new char[contentLength];
            br.read(body, 0, contentLength);
            String bodyDecoded = URLDecoder.decode(String.valueOf(body), "utf8");
            return new LoginProcessor(out, bodyDecoded);
        } else if (requestParsed.getPath().getPath().equals("/user/list")) {
            return new UserListProcessor(br, out);
        } else if (requestParsed.getPath().getPath().startsWith("/css/")) {
            return new CssProcessor(out, requestParsed.getPath().getPath());
        } else if (requestParsed.getPath().getPath().startsWith("/js/")) {
            return new JsProcessor(out, requestParsed.getPath().getPath());
        }

        return new BasicRequestProcessor(out);
    }
}
