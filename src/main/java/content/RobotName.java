package content;

public enum RobotName {

    SQUASH_BOT("SQUASH_BOT"),
    ZOOM_BOT("ZOOM_BOT"),
    HAMMER_BOT("HAMMER_BOT"),
    SPIN_BOT("SPIN_BOT"),
    HULK_X90("HULK_X90"),
    TRUNDLE_BOT("TRUNDLE_BOT");

    private String name;

    private RobotName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
