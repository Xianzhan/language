package xianzhan.java.sensitive.words;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author xianzhan
 * @since 2020-11-14
 */
public class SensitiveWordEngineTest {

    private SensitiveWordEngine engine;

    private final List<String> sensitiveList = List.of(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six"
    );

    private final String text = """
            One-half is slightly less than five-eighths.
            We recovered about two-thirds of the stolen cash.
            """;

    @BeforeEach
    public void init() {
        SensitiveWordEngine.createStateMachine(sensitiveList);
        engine = SensitiveWordEngine.getInstance();
    }

    @Test
    public void testReplaceSensitiveWord() {
        String ret = """
                One-half is slightly less than ****-eighths.
                We recovered about ***-thirds of the stolen cash.
                """;

        String s = engine.replaceSensitiveWord(text);
        Assertions.assertEquals(ret, s);
    }

    @Test
    public void testListSensitiveWord() {
        List<String> strings = engine.listSensitiveWord(text);
        Assertions.assertEquals(List.of("five", "two"), strings);
    }
}
