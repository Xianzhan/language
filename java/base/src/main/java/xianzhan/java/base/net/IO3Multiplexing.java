package xianzhan.java.base.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * IO 多路复用
 * 浏览器访问 localhost
 *
 * @author xianzhan
 * @since 2020-09-24
 */
public class IO3Multiplexing {

    private static Selector selector;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(80));

        // 注册
        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        for (; ; ) {
            // 阻塞
            int events = selector.select();
            if (events == 0) {
                continue;
            }

            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                keyIter.remove();

                if (key.isAcceptable()) {
                    // 有新请求进来, 注册
                    register(key);
                } else if (key.isReadable()) {
                    // 有请求数据
                    readAndWriteData(key);
                }
            }
        }
    }

    private static void register(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
        System.out.println("accept a client: " + sc.getRemoteAddress());
    }

    private static void readAndWriteData(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer reqBuffer = ByteBuffer.allocate(8096);
        sc.read(reqBuffer);
        reqBuffer.flip();
        String req = new String(reqBuffer.array(), StandardCharsets.UTF_8);
        System.out.println(req);

        // response
        String response = "Hello world!";
        String http = """
                HTTP/1.1 200 OK
                Server: IO3Multiplexing
                Content-Type: text/html; charset=utf-8
                Content-Length: %d
                                    
                %s
                """.formatted(response.getBytes(StandardCharsets.UTF_8).length, response);
        byte[] resBytes = http.getBytes(StandardCharsets.UTF_8);
        ByteBuffer resBuffer = ByteBuffer.wrap(resBytes);
        sc.write(resBuffer);

        key.cancel();
    }
}
