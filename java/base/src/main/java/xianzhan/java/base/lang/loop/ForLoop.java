package xianzhan.java.base.lang.loop;

import java.util.List;

/**
 * @author xianzhan
 * @since 2020-11-29
 */
public class ForLoop {

    public static void main(String[] args) {
        common();
        iter();
    }

    private static void common() {
        for (var i = -1; i <= 1; i++) {
            System.out.println(i);
        }
    }

    private static void iter() {
        var list = List.of(-1, 0, 1);
        for (var i : list) {
            System.out.println(i);
        }
    }
}
