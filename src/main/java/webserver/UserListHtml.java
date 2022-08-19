package webserver;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import db.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UserListHtml {
    private static final Logger logger = LoggerFactory.getLogger(UserListHtml.class);
    public String show() {
        try {
            TemplateLoader loader = new ClassPathTemplateLoader();
            loader.setPrefix("/templates");
            loader.setSuffix("");
            loader.setCharset(StandardCharsets.UTF_8);

            Handlebars handlebars = new Handlebars(loader);
            Template template = handlebars.compile("/user/list.html");

            Map<String, Object> map = new HashMap<>();
            map.put("users", DataBase.findAll());

            return template.apply(map);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return "";
    }
}
