package xianzhan.java.base.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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

            Socket socket = sc.socket();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // 接收所有请求数据
            byte[] bytes = new byte[8096];
            // 会一直阻塞在 read() 方法这里
            while ((is.read(bytes)) != -1) {
                // request
                String line = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(line);

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
                os.write(resBytes);
                os.flush();
            }

            sc.close();

            System.out.println("请求处理完毕! 等待下一个请求");
        }
    }
}
