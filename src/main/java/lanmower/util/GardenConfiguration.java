package lanmower.util;

import lanmower.domain.Garden;
import lanmower.service.LawnInstructions;

import java.util.List;

public class GardenConfiguration {
    private final Garden garden;
    private final List<LawnInstructions> instructions;

    public GardenConfiguration(Garden garden, List<LawnInstructions> instructions) {
        this.garden = garden;
        this.instructions = instructions;
    }

    public Garden getGarden() {
        return garden;
    }

    public List<LawnInstructions> getInstructions() {
        return instructions;
    }
}
