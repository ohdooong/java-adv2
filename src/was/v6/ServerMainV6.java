package was.v6;

import was.httpserver.servlet.reflection.ReflectionServlet;

import java.util.List;

public class ServerMainV6 {
    public static void main(String[] args) {
        List<Object> objects = List.of(new SiteController(), new SearchController());
        ReflectionServlet reflectionServlet = new ReflectionServlet(objects);


    }
}
