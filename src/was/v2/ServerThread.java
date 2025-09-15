package was.v2;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            sleep(5000);
            responseToClient(writer);
        }
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

        writer.println(sb.toString());
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

    private static void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
