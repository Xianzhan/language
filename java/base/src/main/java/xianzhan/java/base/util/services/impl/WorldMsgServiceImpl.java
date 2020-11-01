package xianzhan.java.base.util.services.impl;

import xianzhan.java.base.util.services.MessageService;

/**
 * World 实现
 *
 * @author xianzhan
 * @since 2020-11-01
 */
public class WorldMsgServiceImpl implements MessageService {
    @Override
    public String message() {
        return "World";
    }
}
