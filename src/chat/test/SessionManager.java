package chat.test;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        System.out.println("SessionManager.add");
        sessions.add(session);
    }
    public synchronized void remove(Session session) {
        sessions.remove(session);
    }
    public synchronized List<Session> getSessions() {
        return sessions;
    }
    public synchronized String getSessionsList() {
        StringBuilder sb = new StringBuilder();
        for (Session session : sessions) {
            sb.append(session.toString()).append("\n");
        }
        return sb.toString();
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }


}
