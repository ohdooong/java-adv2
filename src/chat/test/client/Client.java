package chat.test.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static chat.test.Server.*;
import static util.MyLogger.log;

public class Client {

    public static void main(String[] args) {
        log("클라이언트 시작");

        try(Socket socket = new Socket("localhost", PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            log("소켓 연결: " + socket);


            WriterHandler wh = new WriterHandler(output);
            Thread writerThread = new Thread(wh);
            writerThread.start();

            ReadHandler rh = new ReadHandler(input);
            Thread readerThread = new Thread(rh);
            readerThread.start();


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
