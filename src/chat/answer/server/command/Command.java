package chat.answer.server.command;

import chat.answer.server.Session;

import java.io.IOException;

public interface Command {
    void execute(String[] args, Session session) throws IOException;


}
