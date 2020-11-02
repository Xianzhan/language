package xianzhan.java.base.lang.reflect.proxy;

import java.util.concurrent.TimeUnit;

/**
 * 代理接口实现类
 *
 * @author xianzhan
 * @since 2020-11-02
 */
public class ProxyInterfaceImpl implements ProxyInterface {
    @Override
    public void run() {
        double random = Math.random();
        System.out.println(random);

        try {
            TimeUnit.MICROSECONDS.sleep((long) random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
