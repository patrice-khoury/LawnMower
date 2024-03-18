package lanmower.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GardenServiceTest {
    private final GardenService gardenService = new GardenService(new LawnMower());

    @Test
    void lawn() throws IOException {
        final String inputPath = getClass().getClassLoader().getResource("garden_input2.txt").getFile();
        final File input = new File(inputPath);

        final String result = gardenService.lawn(input);
        assertThat(result).isEqualTo("1 3 N\n5 1 E");
    }
}
