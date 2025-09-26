package annotation.validator;

public class Team {
    private String teamName;
    private int memberCount;

    public Team(String teamName, int memberCount) {
        this.teamName = teamName;
        this.memberCount = memberCount;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
