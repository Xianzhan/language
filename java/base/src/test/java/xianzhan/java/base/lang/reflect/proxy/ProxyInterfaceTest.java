package xianzhan.java.base.lang.reflect.proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @author xianzhan
 * @since 2020-11-02
 */
public class ProxyInterfaceTest {

    @Test
    public void testNewProxyInstance() {
        ProxyInterface pi = new ProxyInterfaceImpl();

        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(
                ProxyInterface.class.getClassLoader(),
                new Class[]{ProxyInterface.class},
                (object, method, args) -> {
                    var start = System.currentTimeMillis();
                    Object ret = method.invoke(pi, args);
                    var end = System.currentTimeMillis();
                    System.out.println("耗时: " + (end - start) + "ms");
                    return ret;
                }
        );

        Assertions.assertNotNull(proxy, "生成代理不能为 null");
        proxy.run();
    }
}
