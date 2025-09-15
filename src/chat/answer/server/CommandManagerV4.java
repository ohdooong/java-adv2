package chat.answer.server;

import chat.answer.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV4 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        String[] args = totalMessage.split(DELIMETER);
        String commandKey = args[0];

        // NullObject Pattern
        Command command = commands.getOrDefault(commandKey, defaultCommand);
        command.execute(args, session);
    }


    private boolean validationCommand(String commandKey,String totalMessage, Session session) throws IOException {

        if (commandKey.equals("/exit") || commandKey.equals("/users")) {
            return true;
        }

        boolean result = true;
        int size = totalMessage.split(DELIMETER).length;
        if (size != 2) {
            session.send("처리할 수 없는 명령어 입니다. " + totalMessage);
            result = false;
        }
        return result;
    }


}
