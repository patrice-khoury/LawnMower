package lanmower.domain;

import java.util.List;

public class LawnInstructions {
    private final Position position;

    private final List<Command> commands;


    public LawnInstructions(Position position, List<Command> commands) {
        this.position = position;
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Position getPosition() {
        return position;
    }
}
