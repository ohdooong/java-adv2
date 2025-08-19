package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결 : " + socket);

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        output.write(1);

        Thread.sleep(1000);

        try {
            int read = input.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
