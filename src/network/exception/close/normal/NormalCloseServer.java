package network.exception.close.normal;

import java.io.IOException;
import java.net.Socket;

public class NormalCloseServer {
    public static void main(String[] args) throws IOException {
        new Socket("localhost", 12345);

    }
}
