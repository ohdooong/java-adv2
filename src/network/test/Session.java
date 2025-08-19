package network.test;

import network.test.util.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;
    private boolean closed = false;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        log("Session 생성자: " + Thread.currentThread().getName());
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
        log("Session.run: " + Thread.currentThread().getName());
    }

    public synchronized void close() {
        log("Session.close 1: " + Thread.currentThread().getName());
        if (closed) {
            return;
        }
        SocketCloseUtil.closeAll(socket, input, output);
        log("Session.close 2: 자원정리 완료");
        closed = true;
    }
}
