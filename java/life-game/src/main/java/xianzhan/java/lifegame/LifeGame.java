package xianzhan.java.lifegame;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

/**
 * 生命游戏
 *
 * @author xianzhan
 * @since 2020-11-21
 */
public class LifeGame {

    private static final int L = 15;
    private static final int H = 30;

    private boolean[][] panel;
    private boolean[][] compute;

    public LifeGame() {
        var random = new Random();

        compute = new boolean[L][H];
        panel = new boolean[L][H];
        for (var i = 0; i < L; i++) {
            for (var j = 0; j < H; j++) {
                panel[i][j] = random.nextBoolean();
            }
        }
    }

    public void generate() {
        for (var i = 0; i < L; i++) {
            for (var j = 0; j < H; j++) {
                var neighbors = getNeighbors(panel, i, j);
                if (neighbors == 3) {
                    compute[i][j] = true;
                } else if (neighbors == 2) {
                    compute[i][j] = panel[i][j];
                } else {
                    compute[i][j] = false;
                }
            }
        }

        var temp = panel;
        panel = compute;
        compute = temp;
    }

    private int getNeighbors(boolean[][] panel, int x, int y) {
        var neighbors = 0;
        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                var nx = x + i;
                if (nx < 0 || nx == L) {
                    continue;
                }
                var ny = y + j;
                if (ny < 0 || ny == H) {
                    continue;
                }

                neighbors += panel[nx][ny] ? 1 : 0;
            }
        }
        neighbors += panel[x][y] ? -1 : 0;
        return neighbors;
    }

    public void print(PrintStream p, Function<LifeGame, ?> format) {
        Object apply = format.apply(this);
        p.print(apply);
    }

    public void print() {
        print(System.out, Objects::toString);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder(L * H + H);
        for (boolean[] x : panel) {
            for (boolean y : x) {
                builder.append(y ? 'O' : ' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
