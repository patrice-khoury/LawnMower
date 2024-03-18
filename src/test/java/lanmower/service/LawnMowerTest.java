package lanmower.service;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Orientation;
import lanmower.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LawnMowerTest {
    private final LawnMower lawnMower = new LawnMower();
    @Test
    void lawn() {
        final Garden garden = new Garden(5, 5);

        Position result = lawnMower.lawn(garden, new Position(1, 2, Orientation.N), List.of(
                Command.G,
                Command.A,
                Command.G,
                Command.A,
                Command.G,
                Command.A,
                Command.G,
                Command.A,
                Command.A));

        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                1,
                3,
                Orientation.N
        );

        result = lawnMower.lawn(garden, new Position(3, 3, Orientation.E), List.of(
                Command.A,
                Command.A,
                Command.D,
                Command.A,
                Command.A,
                Command.D,
                Command.A,
                Command.D,
                Command.D,
                Command.A));

        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                5,
                1,
                Orientation.E
        );
    }
}
