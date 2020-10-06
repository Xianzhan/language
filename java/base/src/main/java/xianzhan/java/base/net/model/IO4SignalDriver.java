package xianzhan.java.base.net.model;

import sun.misc.Signal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 信号驱动
 *
 * @author xianzhan
 * @since 2020-09-28
 */
public class IO4SignalDriver {

    private static BlockingQueue<Socket> sockets;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        sockets = new LinkedBlockingQueue<>();
        ResponseThread rt = new ResponseThread();
        rt.setName("Response thread");
        rt.start();

        Socket accept;
        // 阻塞, 等待请求
        while ((accept = ss.accept()) != null) {
            sockets.add(accept);

            InputStream is = accept.getInputStream();
            // 接收所有请求数据
            byte[] bytes = new byte[8096];
            // 在 read() 方法这里如果使用 while 会一直阻塞
            // 直接把所有数据读取出来
            // 不然无法发送信号
            if ((is.read(bytes)) != -1) {
                // request
                String line = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(line);
            }

            Signal.raise(new Signal("INT"));
        }
    }

    private static class ResponseThread extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                Signal.handle(new Signal("INT"), sig -> {
                    System.out.println(sig);

                    if ("INT".equals(sig.getName())) {
                        try (Socket socket = sockets.take()) {
                            OutputStream os = socket.getOutputStream();

                            // response
                            String response = "Hello world!";
                            String http = """
                                    HTTP/1.1 200 OK
                                    Server: IO4SignalDriver
                                    Content-Type: text/html; charset=utf-8
                                    Content-Length: %d
                                                        
                                    %s
                                    """.formatted(response.getBytes(StandardCharsets.UTF_8).length, response);
                            byte[] resBytes = http.getBytes(StandardCharsets.UTF_8);
                            os.write(resBytes);
                            os.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
