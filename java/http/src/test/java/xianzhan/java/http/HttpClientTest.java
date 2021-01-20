package xianzhan.java.http;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

/**
 * @author xianzhan
 * @since 2021-01-20
 */
public class HttpClientTest {

    @Test
    public void testDownloadFile() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI uri = URI.create("https://static.rust-lang.org/rustup/dist/x86_64-pc-windows-msvc/rustup-init.exe");
//        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString("{}");
        HttpRequest request = HttpRequest.newBuilder(uri)
//                .headers("Content-Type", "application/json")
//                .POST(bodyPublisher)
                .build();

        HttpResponse.BodyHandler<Path> pathBodyHandler = HttpResponse.BodyHandlers.ofFile(Path.of("rustup-init.exe"));
        HttpResponse<Path> response = httpClient.send(request, pathBodyHandler);
        System.out.println(response);
    }
}
