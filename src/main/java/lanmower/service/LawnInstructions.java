package lanmower.service;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.LawnMower;
import lanmower.domain.Position;

import java.util.List;

public class LawnInstructions {
    private final LawnMower lawnMower;

    private final List<Command> commands;


    public LawnInstructions(LawnMower lawnMower, List<Command> commands) {
        this.lawnMower = lawnMower;
        this.commands = commands;
    }

    public Position execute(final Garden garden) {
        return lawnMower.lawn(garden, commands);
    }

    public LawnMower getLawnMower() {
        return lawnMower;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
