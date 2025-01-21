import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    void constructorShouldThrowExceptionWhenHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null),
                "Expected IllegalArgumentException when horses is null"
        );

        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assert expectedMessage.equals(actualMessage) : "Unexpected exception message";
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    void constructorShouldThrowExceptionWhenHorsesIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()),
                "Expected IllegalArgumentException when horses is empty"
        );

        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assert expectedMessage.equals(actualMessage) : "Unexpected exception message";
    }
    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse 1", 1.0, 10.0);
        Horse horse2 = new Horse("Horse 2", 1.0, 20.0);
        Horse horse3 = new Horse("Horse 3", 1.0, 15.0);
        List<Horse> horses = Arrays.asList(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();

        assertSame(horse2, winner);
    }
}
