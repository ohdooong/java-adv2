package chat.answer.server.command;

import chat.answer.server.Session;
import chat.answer.server.SessionManager;

import java.io.IOException;

public class ExitCommand implements Command {


    @Override
    public void execute(String[] args, Session session) throws IOException {
        throw new IOException("exit");
    }
}
