package xianzhan.java.base.util.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 消息服务接口测试
 *
 * @author xianzhan
 * @since 2020-11-01
 */
public class MessageServiceTest {

    @Test
    public void testMessage() {
        ServiceLoader<MessageService> loader = ServiceLoader.load(MessageService.class);
        Iterator<MessageService> iterator = loader.iterator();

        MessageService hello = iterator.next();
        Assertions.assertEquals("Hello", hello.message(), "Hello");

        MessageService world = iterator.next();
        Assertions.assertEquals("World", world.message(), "World");
    }
}
