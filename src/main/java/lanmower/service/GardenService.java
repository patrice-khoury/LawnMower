package lanmower.service;

import lanmower.domain.Garden;
import lanmower.domain.Position;
import lanmower.util.GardenConfiguration;
import lanmower.util.GardenUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GardenService {
    public List<Position> lawn(final File lawnConfiguration) throws IOException {
        final GardenConfiguration configuration = GardenUtils.fromFile(lawnConfiguration);
        final Garden garden = configuration.getGarden();
        final List<LawnInstructions> instructions = configuration.getInstructions();

        return instructions.stream()
                .map(instruction-> instruction.execute(garden))
                .collect(Collectors.toList());
    }
}
