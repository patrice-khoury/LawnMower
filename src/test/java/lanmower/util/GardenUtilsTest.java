package lanmower.util;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Orientation;
import lanmower.domain.Position;
import lanmower.service.LawnInstructions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GardenUtilsTest {
    @Test
    void parseGarden() {
        final String input = "1 2";
        final Garden result = GardenUtils.parseGarden(input);
        assertThat(result).extracting(
                Garden::getMinX,
                Garden::getMinY,
                Garden::getMaxX,
                Garden::getMaxY)
                .containsExactly(
                        0,
                                0,
                                1,
                                2
                );
    }

    @Test
    void parsePosition() {
        final String input = "1 2 N";
        final Position result = GardenUtils.parsePosition(input);
        assertThat(result).extracting(
                        Position::getX,
                        Position::getY,
                        Position::getOrientation)
                .containsExactly(
                        1,
                                2,
                                Orientation.N
                );
    }

    @Test
    void parseCommands() {
        final String input = "DGA";
        final List<Command> result = GardenUtils.parseCommands(input);
        assertThat(result).isNotEmpty().containsExactly(
                    Command.D,
                    Command.G,
                    Command.A
                );
    }

    @Test
    void fromFile() throws IOException {
        final String inputPath = getClass().getClassLoader().getResource("garden_input1.txt").getFile();
        final File input = new File(inputPath);
        final GardenConfiguration result = GardenUtils.fromFile(input);

        assertThat(result).isNotNull();
        final Garden garden = result.getGarden();
        assertThat(garden).extracting(
                        Garden::getMinX,
                        Garden::getMinY,
                        Garden::getMaxX,
                        Garden::getMaxY)
                .containsExactly(
                        0,
                        0,
                        1,
                        2
                );

        final List<LawnInstructions> instructions = result.getInstructions();
        assertThat(instructions).hasSize(1);
        final LawnInstructions instruction = instructions.getFirst();

        final Position position = instruction.getLawnMower().getPosition();
        assertThat(position).extracting(
                        Position::getX,
                        Position::getY,
                        Position::getOrientation)
                .containsExactly(
                        3,
                        4,
                        Orientation.N
                );

        final List<Command> commands = instruction.getCommands();
        assertThat(commands).isNotEmpty().containsExactly(
                Command.D,
                Command.G,
                Command.A
        );
    }
}
