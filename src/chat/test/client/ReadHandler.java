package chat.test.client;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    private boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            // 서버로부터 문자 받기
            String received = null;
            try {
                received = input.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log("server -> client: " + received);
        }
    }
}
