package xianzhan.java.base.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用 netsh 命令启动进程查看 wifi
 *
 * @author xianzhan
 * @since 2020-10-08
 */
public class Proc {

    public static void main(String[] args) {
        List<String> wifi = scanWifi();
        System.out.println(wifi);
    }

    private static List<String> scanWifi() {
        List<String> networkList = new ArrayList<>();
        try {
            // Execute command
            String command = "netsh wlan show networks mode=Bssid";
            Process p = Runtime.getRuntime().exec(command);
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream(), "GBK")
            );
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("SSID ") && !line.contains("BSSID ")) {
                    networkList.add(line.split(":")[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return networkList;
    }
}
