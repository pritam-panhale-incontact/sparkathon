package sparkathon.dashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        Process p;
        try {
            p = Runtime.getRuntime().exec("aws kinesis describe-stream --stream-name sparkathon");

            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("StreamStatus") && line.contains("ACTIVE")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
