package lanmower.util;

import lanmower.domain.Command;
import lanmower.domain.Garden;
import lanmower.domain.Orientation;
import lanmower.domain.Position;

import java.util.Map;

import static lanmower.domain.Command.D;
import static lanmower.domain.Command.G;
import static lanmower.domain.Orientation.*;

public class PositionUtils {
    private final static Map<Command, Map<Orientation, Orientation>> ROTATION_POSITIONS = Map.of(
            G, Map.of(N, W, W, S, S, E, E, N),
            D, Map.of(N, E, E, S, S, W, W, N));

    private PositionUtils() {}

    private static Orientation getNewOrientation(final Orientation current, final Command command) {
        final Map<Orientation, Orientation> orientations = ROTATION_POSITIONS.get(command);
        if (orientations == null) {
            throw new IllegalArgumentException("Invalid direction : " + command);
        }
        return orientations.get(current);
    }

    public static Position rotate(final Position position, final Command command) {
        final Orientation newOrientation = getNewOrientation(position.getOrientation(), command);
        return position.rotate(newOrientation);
    }

    private static boolean canMove(final int newCoordinate, final int limit, final int moveFactor) {
        final int offLimitFactor;
        if (moveFactor > 0) {
            offLimitFactor = limit - newCoordinate;
        } else {
            offLimitFactor = newCoordinate - limit;
        }
        return offLimitFactor >= 0;
    }

    private static int getNewCoordinate(final int current, final int moveFactor, final int limit) {
        final int newCoordinate = current + moveFactor;
        final boolean canMove = canMove(newCoordinate, limit, moveFactor);

        return canMove ? newCoordinate : current;
    }

    public static Position move(final Garden garden, final Position position) {
        int newX = position.getX();
        int newY = position.getY();

        switch (position.getOrientation()) {
            case N -> {
                newY = getNewCoordinate(position.getY(),  1, garden.getMaxY());
            }
            case S -> {
                newY = getNewCoordinate(position.getY(),  -1, garden.getMinY());
            }
            case W -> {
                newX = getNewCoordinate(position.getX(),  -1, garden.getMinX());
            }
            case E -> {
                newX = getNewCoordinate(position.getX(),  1, garden.getMaxX());
            }
        }
        return position.move(newX, newY);
    }
}