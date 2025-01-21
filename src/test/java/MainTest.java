import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22)
    void testMainExecutionTime() {
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
