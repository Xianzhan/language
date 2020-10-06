package xianzhan.java.base.net.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 同步阻塞
 * 运行后使用浏览器请求 localhost
 *
 * @author xianzhan
 * @since 2020-09-20
 */
public class IO1Blocking {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);

        Socket accept;
        // 阻塞, 等待请求
        while ((accept = ss.accept()) != null) {
            InputStream is = accept.getInputStream();
            OutputStream os = accept.getOutputStream();

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
            accept.close();

            System.out.println("请求处理完毕! 等待下一个请求");
        }
    }
}
