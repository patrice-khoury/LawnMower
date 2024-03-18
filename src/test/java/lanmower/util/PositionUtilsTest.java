package lanmower.util;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Orientation;
import lanmower.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionUtilsTest {
    @Test
    void rotate() {
        Position input = new Position(0, 0, Orientation.N);
        Position result = PositionUtils.rotate(input, Command.G);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                        0,
                Orientation.W
        );

        input = new Position(0, 0, Orientation.N);
        result = PositionUtils.rotate(input, Command.D);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                0,
                Orientation.E
        );
    }

    @Test
    void move() {
        Garden garden = new Garden(1, 1);
        Position input = new Position(0, 0, Orientation.N);
        Position result = PositionUtils.move(garden, input);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                1,
                Orientation.N
        );
    }

    @Test
    void move_off_limit() {
        Garden garden = new Garden(1, 0);
        Position input = new Position(0, 0, Orientation.N);
        Position result = PositionUtils.move(garden, input);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                0,
                Orientation.N
        );
    }

    @Test
    void move_negative_on_axis() {
        Garden garden = new Garden(1, 1);
        Position input = new Position(0, 1, Orientation.S);
        Position result = PositionUtils.move(garden, input);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                0,
                Orientation.S
        );
    }

    @Test
    void move_negative_on_axis_off_limit() {
        Garden garden = new Garden(1, 1);
        Position input = new Position(0, 0, Orientation.S);
        Position result = PositionUtils.move(garden, input);
        assertThat(result).extracting(
                Position::getX,
                Position::getY,
                Position::getOrientation
        ).containsExactly(
                0,
                0,
                Orientation.S
        );
    }
}
