package chat.answer.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {



        if (totalMessage.startsWith("/join")) {

            if (!validationCommand(totalMessage, session)) {
                return;
            }

            String[] args = totalMessage.split(DELIMETER);


            String username = args[1];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장했습니다.");
        } else if (totalMessage.startsWith("/message")) {

            if (!validationCommand(totalMessage, session)) {
                return;
            }
            String[] args = totalMessage.split(DELIMETER);
            String message = args[1];
            sessionManager.sendAll("[" + session.getUsername() + "] "+message);
        } else if (totalMessage.startsWith("/change")) {

            if (!validationCommand(totalMessage, session)) {
                return;
            }
            String[] args = totalMessage.split(DELIMETER);
            String changeName = args[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "으로 이름을 변경하였습니다.");
            session.setUsername(changeName);
        } else if (totalMessage.startsWith("/users")) {

            List<String> allUsername = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자: ").append(allUsername.size()).append("\n");
            for (String username : allUsername) {
                sb.append(" - ").append(username).append("\n");
            }
            session.send(sb.toString());
        } else {
            session.send("처리할 수 없는 명령어 입니다. " + totalMessage);
        }

        if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        }

    }


    private boolean validationCommand(String totalMessage, Session session) throws IOException {
        boolean result = true;
        int size = totalMessage.split(DELIMETER).length;
        if (size != 2) {
            session.send("처리할 수 없는 명령어 입니다. " + totalMessage);
            result = false;
        }
        return result;
    }


}
