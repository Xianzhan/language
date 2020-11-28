package xianzhan.java.lifegame;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhan
 * @since 2020-11-21
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        var cls = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
        cls.start().waitFor();

        var life = new LifeGame();
        for (; ; ) {
            life.print();
            life.generate();

            TimeUnit.MILLISECONDS.sleep(900L);

            cls.start().waitFor();
        }
    }
}
