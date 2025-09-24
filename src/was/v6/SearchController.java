package was.v6;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public class SearchController {
    public void search(HttpRequest request, HttpResponse response) {
        String query = request.getQueryParameter("q");

        response.writeBody("<h1>Search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>" + query + "</li>");
        response.writeBody("</ul>");

    }
}
