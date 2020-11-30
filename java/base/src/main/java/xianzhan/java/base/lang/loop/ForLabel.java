package xianzhan.java.base.lang.loop;

/**
 * @author xianzhan
 * @since 2020-11-30
 */
public class ForLabel {

    public static void main(String[] args) {
        breakLabel();
        continueLabel();
    }

    private static void breakLabel() {
        label:
        // i 永远都是等于 -1
        // j == 0 的时候已经 break 最外层的循环
        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                for (var k = -1; k <= 1; k++) {
                    if (j == 0) {
                        // 此处相当于 return
                        break label;
                    }
                }
            }
        }
    }

    private static void continueLabel() {
        label:
        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                // j 永远无法到达 1
                // j 等于 0 的时候就跳到外层进行下一循环
                for (var k = -1; k <= 1; k++) {
                    if (j == 0) {
                        continue label;
                    }
                }
            }
        }
    }
}
