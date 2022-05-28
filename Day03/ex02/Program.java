import java.io.IOException;
import java.util.*;

public class Program {

    private static int arrSize;
    private static int numThread;
    private static int arr[];
    private static ArrayList<Summarizer> threads = new ArrayList<>();
    private static int delta;


    public static void main(String[] args) {

        if (args.length != 2 || !args[0].startsWith("--arraySize=")
            || !args[1].startsWith("--threadsCount=")) {
            printError("Invalid arguments");
        }

        try {
            arrSize = Integer.parseInt(args[0].substring("--arraySize=".length()));
            numThread = Integer.parseInt(args[1].substring("--threadsCount=".length()));
        } catch (Exception e) {
            System.err.println(e);
        }

        arr = new int[arrSize];
        int mainSum = 0;
        for (int i = 0; i < arrSize; ++i) {
            arr[i] = (int)(Math.random() * 2001) - 1000;
            mainSum += arr[i];
        }

        System.out.println("Sum: " + mainSum);

        delta = arrSize / numThread;

        for (int i = 0; i < numThread; ++i) {
            
            int end;
            int start = delta * i;
            if (i == numThread - 1) {
                end = arrSize - 1;
            } else {
                end = delta * (i + 1) - 1;
            }
            Summarizer summarizer = new Summarizer(arr, start, end, i);
            threads.add(summarizer);
            summarizer.start();
        }

        try {
            int result = 0;
            for (int i = 0; i < numThread; ++i) {
                threads.get(i).join();
                result += threads.get(i).getArrSum();
            }
            System.out.println("Sum by threads: " + result);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public static void printError(String str) {
        System.err.println(str);
        System.exit(1);
    }
}