import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

    public static final String INFILE = "signatures.txt";
    public static final String OUTFILE = "result.txt";

    public static void main(String[] args) {

        Map<String, String> signatures = new HashMap<>();

        try {
            FileInputStream input = new FileInputStream(new File(INFILE));
            StringBuilder builder = new StringBuilder();
            int c;

            while ((c = input.read()) != -1) {

                if ((char)c == '\n') {
                    String[] line = builder.toString().split(", ");
                    signatures.put(line[1].trim(), line[0]);
                    builder.setLength(0);
                } else {
                    builder.append((char)c);
                }
            }
            input.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        String path = "";
        Scanner scanner = new Scanner(System.in);

        while (!(path = scanner.nextLine()).equals("42")) {

            StringBuilder hex = new StringBuilder();

            try {
                FileInputStream instream = new FileInputStream(path);
                FileOutputStream resultFile = new FileOutputStream(OUTFILE, true);

                for (int i = 0; instream.available() > 0 && i < 10; ++i) {
                    hex.append(String.format("%02X ", instream.read()));
                }

                String fileSignature = hex.toString();
                String value = "";

                for (String key : signatures.keySet()) {
                    if (fileSignature.startsWith(key)) {
                        value = signatures.get(key);
                        resultFile.write(value.getBytes());
                        resultFile.write('\n');
                        System.out.println("PROCESSED");
                    }
                }

            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}