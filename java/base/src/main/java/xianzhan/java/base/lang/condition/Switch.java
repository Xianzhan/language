package xianzhan.java.base.lang.condition;

import java.util.Random;

/**
 * @author xianzhan
 * @since 2020-10-01
 */
public class Switch {

    private static final Random random = new Random();

    public static void main(String[] args) {
        statement();
        expression();
        mulExpression();
    }

    private static void statement() {
        int result;
        switch (random.nextInt(3)) {
            case 0: {
                result = 0;
                break;
            }
            case 1: {
                result = 1;
                break;
            }
            case 2: {
                result = 2;
                break;
            }
            default: {
                result = -1;
                break;
            }
        }
        System.out.println(result);
    }

    private static void expression() {
        int result = switch (random.nextInt(3)) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            default -> -1;
        };
        System.out.println(result);
    }

    private static void mulExpression() {
        int result = switch (random.nextInt(3)) {
            case 0 -> {
                System.out.println(0);
                yield 0;
            }
            case 1 -> {
                System.out.println(1);
                yield 1;
            }
            case 2 -> {
                System.out.println(2);
                yield 2;
            }
            default -> {
                System.out.println("default");
                yield -1;
            }
        };
        System.out.println(result);
    }
}
