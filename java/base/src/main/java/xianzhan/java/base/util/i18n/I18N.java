package xianzhan.java.base.util.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author xianzhan
 * @since 2020-11-01
 */
public class I18N {


    private static ResourceBundle bundle;

    public static void setResource(String basename) {
        setResource(basename, Locale.getDefault());
    }

    public static void setResource(String basename, Locale locale) {
        bundle = ResourceBundle.getBundle(basename, locale);
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }
}
