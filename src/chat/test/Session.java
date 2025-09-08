package chat.test;

import chat.test.util.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static util.MyLogger.log;

public class Session implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;
    private boolean closed = false;
    private String name;

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

        try {
            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server: " + received);


                processMessage(received);
//                try {
//                    String[] splitMessage = received.split("|");
//                    processMessage(splitMessage);
//                } catch (PatternSyntaxException e) {
//                    log(e.getMessage());
//                    output.writeUTF("메시지를 올바른 형식으로 입력하십시오.");
//                }
            }
        } catch (IOException e) {
            log(e.getMessage());
        } finally {
            sessionManager.remove(this);
            close();
        }

    }

    private void processMessage(String message) throws IOException {

        int regIndex = message.indexOf("|");
        if (regIndex == -1) {
            sendMessage("올바른 형식이 아닙니다.");
        }

        String command = message.substring(0, regIndex);

        log("command: " + command);

        switch(command) {
            case "/join", "/change" -> {
                this.name = message.substring(regIndex + 1);
                sendMessage("당신의 이름은: " +this.name+"입니다.");
            }
            case "/message" -> {
                if (checkName()) {
                    sendMessage("이름을 입력해주세요.");
                    return;
                }
                sendEveryOne(message.substring(regIndex + 1));
            }
            case "/users" -> {
                if (checkName()) {
                    sendMessage("이름을 입력해주세요.");
                    return;
                }
                String sessionsList = sessionManager.getSessionsList();
                sendMessage(sessionsList);
            }
            case "/exit" -> {
                throw new IOException("세션 연결 종료");
            }

        }


    }

    private void sendMessage(String message) throws IOException {
        output.writeUTF(message);
        log("server -> client: " + message);
    }

    private void sendEveryOne(String message) throws IOException {
        List<Session> sessions = sessionManager.getSessions();
        log(Arrays.toString(sessions.toArray()));
        for (Session session : sessions) {
            session.sendMessage(message);
        }
    }

    private boolean checkName() {
        return this.name.isEmpty();
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
