package lanmower.domain;

import java.util.List;

public class LawnMower {
    private final Position position;

    public LawnMower(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Position lawn(final Garden garden, final List<Command> commands) {
        return null;
        // TODO
    }
}
