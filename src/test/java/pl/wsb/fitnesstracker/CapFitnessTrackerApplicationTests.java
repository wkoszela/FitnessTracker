package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = pl.wsb.fitnesstracker.FitnessTracker.class)
class CapFitnessTrackerApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
