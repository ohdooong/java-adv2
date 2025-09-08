package chat.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class SocketCloseUtil {
    public static void closeAll(Socket socket, InputStream input, OutputStream output) {
        log("SocketCloseUtil.closeAll: " + Thread.currentThread().getName());
        close(input);
        close(output);
        close(socket);
    }

    public static void close(InputStream input) {
        log("SocketCloseUtil.close input: " + Thread.currentThread().getName());
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(OutputStream output) {
        log("SocketCloseUtil.close output: " + Thread.currentThread().getName());
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void close(Socket socket) {
        log("SocketCloseUtil.close socket: " + Thread.currentThread().getName());
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

}
