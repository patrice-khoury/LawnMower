package lanmower.app;

import lanmower.service.GardenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class LawnMowerApplication {
    private final static Logger LOG = LoggerFactory.getLogger(LawnMowerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LawnMowerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final GardenService gardenService) {
        return args -> {
            if (args.length < 1) {
                throw new IllegalArgumentException("Please provide the lawn instructions file as a parameter");
            }

            final String filePath = args[0];
            LOG.info("Processing lawn instructions from file " + filePath);
            final File lawnInstructions = new File(filePath);
            final String lawnResult = gardenService.lawn(lawnInstructions);
            LOG.info("Garden successfully lawn, result :" + lawnResult);
        };
    }
}
