package network.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {

    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManager sessionManager = new SessionManager();

        ServerSocket serverSocket = new ServerSocket(12345);

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Session session = new Session(socket, sessionManager);
                new Thread(session).start();

            }

        } catch (IOException e) {

        }





    }



}
