package com.wisdom;

import com.wisdom.socket.SocketApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Object[] objects = new Object[2];
        objects[0] = Application.class;
        objects[1] = SocketApplication.class;
        return application.sources(objects);
    }

}
