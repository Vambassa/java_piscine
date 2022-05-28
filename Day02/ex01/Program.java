import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Program {

    private static final String DICT_FILE = "dictionary.txt";

    public static List<String> fileA = new ArrayList<>();
    public static List<String> fileB = new ArrayList<>();
    public static TreeSet<String> dict = new TreeSet<>();

    public static void main(String[] args) {

        if (args.length != 2) {
            printError("Need 2 infiles");
        }

        try {

            addToStructures(args[0], fileA, dict);
            addToStructures(args[1], fileB, dict);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DICT_FILE));

            for (String s : dict) {
                bufferedWriter.write(s + " ");
            }
            bufferedWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.printf("Similarity = %.4s\n", getResult());
    }

    public static void addToStructures(String infile, List<String> file, TreeSet<String> dict) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(infile));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] wordsArr = line.split(" ");
            for (String s : wordsArr) {
                file.add(s);
            }
            for (String s : file) {
                dict.add(s);
            }
        }
        bufferedReader.close();
    }

    public static String getResult() {

        int size = dict.size();
        int[] freqA = countFrequency(fileA);
        int[] freqB = countFrequency(fileB);

        int numerator = 0;
        for (int i = 0; i < size; ++i) {
            numerator += freqA[i] * freqB[i];
        }

        double denominator = 0;
        double tmpSum = 0;
        for (int i = 0; i < size; ++i) {
            tmpSum += Math.pow(freqA[i], 2);
            denominator += Math.pow(freqB[i], 2);
        }
        denominator = Math.sqrt(denominator) * Math.sqrt(tmpSum);

        return String.format("%.3f", numerator / denominator);
    }

    public static int[] countFrequency(List<String> file) {

        int count;
        int i = 0;
        int[] arr = new int[dict.size()];

        for (String s : dict) {
            count = 0;
            for (String str : file) {
                if (s.equals(str)) {
                    ++count;
                }
            }
            arr[i] = count;
            ++i;
        }
        return arr;
    }

    public static void  printError(String str)
    {
        System.err.println(str);
        System.exit(1);
    }
}