package xianzhan.java.base.util.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * @author xianzhan
 * @since 2020-11-01
 */
public class I18NTest {

    @Test
    public void testDirection() {
        I18N.setResource("i18n.direction");
        var bundle = I18N.getBundle();
        Assertions.assertEquals("上", bundle.getString(Direction.UP), "上");

        I18N.setResource("i18n.direction", Locale.ENGLISH);
        bundle = I18N.getBundle();
        Assertions.assertEquals("up", bundle.getString(Direction.UP), "UP");
    }
}
