package xianzhan.java.base.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 同步非阻塞
 * 启动后会一直打印
 * 浏览器访问 localhost
 *
 * @author xianzhan
 * @since 2020-09-21
 */
public class IO2NonBlocking {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(80));

        // 设置为非阻塞, accept() 方法会立即返回
        ssc.configureBlocking(false);
        SocketChannel sc;
        for (; ; ) {
            // 此处不阻塞
            sc = ssc.accept();
            if (sc == null) {
                System.out.println("未有请求");
                TimeUnit.SECONDS.sleep(1);
                continue;
            }

            ByteBuffer buffer = ByteBuffer.allocate(8096);
            sc.read(buffer);
            buffer.flip();
            String req = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(req);

            // response
            String response = "Hello world!";
            String http = """
                        HTTP/1.1 200 OK
                        Server: IO1Block
                        Content-Type: text/html; charset=utf-8
                        Content-Length: %d
                                            
                        %s
                        """.formatted(response.getBytes(StandardCharsets.UTF_8).length, response);
            byte[] resBytes = http.getBytes(StandardCharsets.UTF_8);
            ByteBuffer resBuffer = ByteBuffer.wrap(resBytes);
            sc.write(resBuffer);

            sc.close();

            System.out.println("请求处理完毕! 等待下一个请求");
        }
    }
}
