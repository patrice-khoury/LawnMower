package lanmower.app;

import lanmower.service.GardenService;
import lanmower.service.LawnMower;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LawnMowerAppConfiguration {
    @Bean
    public LawnMower lawnMower() {
        return new LawnMower();
    }

    @Bean
    public GardenService gardenService(final LawnMower lawnMower) {
        return new GardenService(lawnMower);
    }
}
