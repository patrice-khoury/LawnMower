package lanmower.service;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Position;
import lanmower.util.PositionUtils;

import java.util.List;

public class LawnMower {
    public Position lawn(final Garden garden, final Position position, final List<Command> commands) {
        Position currentPosition = position;

        for (Command command : commands) {
            currentPosition = processCommand(garden, currentPosition, command);
        }

        return currentPosition;
    }

    private Position processCommand(final Garden garden, final Position position, final Command command) {
        final Position newPosition;

        switch (command) {
            case A -> newPosition = PositionUtils.move(garden, position);
            case D, G -> newPosition = PositionUtils.rotate(position, command);
            default -> throw new IllegalArgumentException("Unkown command : " + command);
        }
        return newPosition;
    }
}
