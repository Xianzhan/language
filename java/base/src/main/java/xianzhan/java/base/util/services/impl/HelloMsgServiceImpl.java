package xianzhan.java.base.util.services.impl;

import xianzhan.java.base.util.services.MessageService;

/**
 * Hello 实现
 *
 * @author xianzhan
 * @since 2020-11-01
 */
public class HelloMsgServiceImpl implements MessageService {
    @Override
    public String message() {
        return "Hello";
    }
}
