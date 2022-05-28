import java.util.Scanner;

public class Program {
    private static final int MAX_VALUE = 65535;
    private static final int MAX_UNIQUE_SYMB = 1000;

    public static void main(String[] args) {

        System.out.println("");
        System.out.println("->");

        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            printMessage(sc, "IllegalArgument");
        }

        String str = sc.nextLine();
        int length = str.length();
        
        if (length == 0) {
            printMessage(sc, "Error: No arguments");
        }

        char[] input = str.toCharArray();
        int countSymbols[] = new int[MAX_VALUE];

        for (int i = 0; i < length; ++i) {
            ++countSymbols[input[i]];
        }

        int[] notNullSymb = new int[MAX_UNIQUE_SYMB];
        int[] notNullValue = new int[MAX_UNIQUE_SYMB];

        int countNotNull = 0;
        for (int i = 0; i < MAX_VALUE; ++i) {
            if (countSymbols[i] != 0) {
                notNullSymb[countNotNull] = i;
                notNullValue[countNotNull] = countSymbols[i];
                ++countNotNull;
            }
        }

        int[] frequencyArrSymb = new int[10];
        int[] frequencyArrValue = new int[10];
        int max;
        int maxIndex;
        for (int j = 0; j < 10; ++j) {
            max = -1;
            maxIndex = -1;
            for (int i = 0; i < countNotNull; ++i) {
                if (notNullValue[i] > max) {
                    max = notNullValue[i];
                    maxIndex = i;
                }
            }
            frequencyArrSymb[j] = notNullSymb[maxIndex];
            frequencyArrValue[j] = max;
            notNullSymb[maxIndex] = 0;
            notNullValue[maxIndex] = 0;
        }
        printOutput(frequencyArrSymb, frequencyArrValue);
        sc.close();
    }

    private static void printOutput(int[] frequencyArrSymb, int[] frequencyArrValue) {

        int arrSize = 0;
        for (int i = 0; i < 10; i++) {
            if (frequencyArrValue[i] == 0) {
                break;
            }
            ++arrSize;
        }

        int[] latticeCount = new int[arrSize];
        int maxFreqValue = frequencyArrValue[0];

        for (int i = 0; i < arrSize; ++i) {
            int currFreqValue = frequencyArrValue[i];
            latticeCount[i] = (int)(currFreqValue / (double)maxFreqValue * 10);
        }

        System.out.println("");
        for (int i = 11; i > 0; --i) {
            int height = i;
            int LowerLayer = i - 1;
            for (int j = 0; j < arrSize; ++j) {
                if (latticeCount[j] >= height) {
                    System.out.printf("  #");
                } else if (latticeCount[j] >= LowerLayer) {
                    System.out.printf("%3d", frequencyArrValue[j]);
                } else {
                    break;
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < arrSize; ++i) {
            System.out.printf("%3c", (char)frequencyArrSymb[i]);
        }
        System.out.println("");
    }

    private static void printMessage(Scanner sc, String str) {

        System.err.println(str);
        sc.close();
        System.exit(-1);
    }
}