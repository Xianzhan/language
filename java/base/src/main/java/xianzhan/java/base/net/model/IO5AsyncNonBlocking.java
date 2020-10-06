package xianzhan.java.base.net.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * 异步非阻塞
 * 浏览器访问 localhost
 *
 * @author xianzhan
 * @since 2020-09-28
 */
public class IO5AsyncNonBlocking {

    private static final int    BUFF = 8096;
    private static final Object LOCK = new Object();

    private static AsynchronousServerSocketChannel listener;

    public static void main(String[] args) throws IOException, InterruptedException {
        init();
        accept();

        synchronized (LOCK) {
            // 主线程阻塞, 否则会直接退出
            LOCK.wait();
        }
    }

    private static void init() throws IOException {
        // 管理共享资源
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(
                // 此处为了方便使用 JDK 默认的缓存线程池
                Executors.newCachedThreadPool(), 3
        );
        listener = AsynchronousServerSocketChannel.open(group);
        // 配置参数
        listener.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        listener.setOption(StandardSocketOptions.SO_RCVBUF, BUFF);
        // 绑定主机端口
        listener.bind(new InetSocketAddress("localhost", 80));
    }

    private static void accept() {
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
                final ByteBuffer buffer = ByteBuffer.allocate(BUFF);
                try (socketChannel) {
                    socketChannel.read(buffer).get();
                    String req = new String(buffer.array(), StandardCharsets.UTF_8);
                    System.out.println(req);

                    // response
                    String response = "Hello world!";
                    String http = """
                            HTTP/1.1 200 OK
                            Server: IO5AsyncNonBlocking
                            Content-Type: text/html; charset=utf-8
                            Content-Length: %d
                                                
                            %s
                            """.formatted(response.getBytes(StandardCharsets.UTF_8).length, response);
                    byte[] resBytes = http.getBytes(StandardCharsets.UTF_8);
                    ByteBuffer resBuff = ByteBuffer.wrap(resBytes);
                    socketChannel.write(resBuff);

                } catch (InterruptedException | ExecutionException | IOException e) {
                    e.printStackTrace();
                } finally {
                    // 下个 accept 也如此处理
                    listener.accept(null, this);
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Accept failed: ");
                exc.printStackTrace();
            }
        });
    }
}
