import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    void nameNullEception() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void nameNullMassege() {
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource (strings = {"", " ", "/t"})
    void nameEmptyEception(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource (strings = {"", " ", "/t"})
    void nameEmptyMassege(String name) {
        try {
            new Horse(name, 1, 1);
            fail();
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @Test
    void negativeSpeedValueEception() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Name", -4, 1));
    }

    @Test
    void negativeSpeedValueMassege() {
        try {
            new Horse("Name", -2, 1);
            fail();
        } catch (IllegalArgumentException exception) {
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void negativeDistanceValueEception() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Name", 1, -1));
    }

    @Test
    void negativeDistanceValueMassege() {
        try {
            new Horse("Name", 1, -1);
            fail();
        } catch (IllegalArgumentException exception) {
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void getName() {
        String expectedName = "Ivan";
        Horse horse = new Horse(expectedName, 1, 1);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 1;
        Horse horse = new Horse("name", expectedSpeed, 1);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double expectedDistance = 1;
        Horse horse = new Horse("name", 1, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void moveGetRandom() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Ivan", 1, 2).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 0.2, 2.0",
            "5.0, 0.5, 2.5",
            "7.0, 0.9, 6.3"
    })
    void moveCalculatesCorrectDistance(double speed, double randomValue, double expectedDistance) {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            Horse horse = new Horse("Ivan", speed, 0);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.0001);

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
