package was.v3;

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
            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("Http 요청 정보 출력");
            log(requestString);

            log("HTTP 응답 생성중...");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }



            //responseToClient(writer);
        }
    }

    private void home(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>Home page</h1>");
        writer.println("<ul>");
        writer.println("<li><a href=\"/site1\">site1</a></li>");
        writer.println("<li><a href=\"/site2\">site2</a></li>");
        writer.println("<li><a href=\"/search?q=hello\">검색</a></li>");

        writer.println("</ul>");
        writer.println("<button>안녕하세요</button>");
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>404 Not Found</h1>");
        writer.flush();
    }


    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);

        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<li>" + query + "</li>");
        writer.println("<li>" + decode + "</li>");

    }

    private void site2(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site1</h1>");
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site1</h1>");
        writer.flush();
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
