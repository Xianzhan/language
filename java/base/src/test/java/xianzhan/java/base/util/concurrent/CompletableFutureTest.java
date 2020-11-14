package xianzhan.java.base.util.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 测试类
 *
 * @author xianzhan
 * @since 2020-10-26
 */
public class CompletableFutureTest {

    // 创建 by 构造器

    @Test
    public void testNew() throws Exception {
        CompletableFuture<String> cf = new CompletableFuture<>();
        System.out.println(cf.isDone());

        System.out.println(cf.get(3, TimeUnit.SECONDS));
    }
}
