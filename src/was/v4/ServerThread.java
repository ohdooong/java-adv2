package was.v4;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class ServerThread implements Runnable{

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
        this.writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (this.socket;
            this.reader;
            this.writer
        ) {

            HttpRequest request = new HttpRequest(reader);
            HttpResponse response = new HttpResponse(writer);


            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("Http 요청 정보 출력");
            log(requestString);

            log("HTTP 응답 생성중...");
            if (request.getPath().equals("GET /site1")) {
                site1(response);
            } else if (request.getPath().equals("GET /site2")) {
                site2(response);
            } else if (request.getPath().equals("GET /search")) {
                search(request, response, requestString);
            } else if (request.getPath().equals("GET / ")) {
                home(response);
            } else {
                notFound(response);
            }
            response.flush();
        }
    }

    private void home(HttpResponse response) {
        response.writeBody("<h1>Home page</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href=\"/site1\">site1</a></li>");
        response.writeBody("<li><a href=\"/site2\">site2</a></li>");
        response.writeBody("<li><a href=\"/search?q=hello\">검색</a></li>");

        response.writeBody("</ul>");
        response.writeBody("<button>안녕하세요</button>");
    }

    private void notFound(HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 페이지를 찾을 수 없습니다.</h1>");
    }


    private void search(HttpRequest request,HttpResponse response, String requestString) {

        String query = request.getQueryParameter("q");

        response.writeBody("<h1>Search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>" + query + "</li>");
        response.writeBody("</ul>");
    }

    private void site2(HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }

    private void site1(HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }

    private static void responseToClient(PrintWriter writer) {
        String body = "<h1>Hello World!</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append("\r\n")
            .append("Content-Type: text/html").append("\r\n")
            .append("Content-Length: ").append(length).append("\r\n")
            .append("\r\n")
            .append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(body);
        writer.flush();
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine().trim()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\r\n");
        }
        return sb.toString();
    }
}
