package lanmower.util;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Orientation;
import lanmower.domain.Position;
import lanmower.domain.LawnInstructions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GardenUtils {
    public static final String INPUT_SEPARATOR = " ";
    private GardenUtils() {}

    public static GardenConfiguration fromFile(final File inputFile) throws IOException {
        final List<String> configurations = FileUtils.readLines(inputFile, StandardCharsets.UTF_8);
        // We must have at least three lines : garden and one lawnmower
        if (configurations.size() < 3) {
            throw new IllegalArgumentException("Configuration is incomplete. Expected at least 3 lines, got " + configurations.size());
        }

        final Garden garden = parseGarden(configurations.getFirst());
        final List<LawnInstructions> instructions = new ArrayList<>(configurations.size() - 1);

        for (int i = 1; i < configurations.size(); i += 2) {
            final Position position = parsePosition(configurations.get(i));
            final List<Command> commands = parseCommands(configurations.get(i + 1));

            final LawnInstructions instruction = new LawnInstructions(position, commands);

            instructions.add(instruction);
        }
        return new GardenConfiguration(garden, instructions);
    }

    public static Garden parseGarden(final String input) {
        final String[] coordinates = input.split(INPUT_SEPARATOR);
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Incomplete garden coordinates :" + input);
        }
        return new Garden(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    public static Position parsePosition(final String input) {
        final String[] coordinates = input.split(INPUT_SEPARATOR);
        if (coordinates.length != 3) {
            throw new IllegalArgumentException("Incomplete lawnMower initial position :" + input);
        }
        return new Position(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Orientation.valueOf(coordinates[2]));
    }

    public static List<Command> parseCommands(final String input) {
        final List<Command> commands = new ArrayList<>(input.length());
        for (char commandInput : input.toCharArray()) {
            final Command command = Command.valueOf(String.valueOf(commandInput));
            commands.add(command);
        }
        return commands;
    }
}
