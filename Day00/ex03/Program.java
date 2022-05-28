import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int     weekCount = 0;
        long    allGrades = 0;
        int     grade = 0;
        int     minGrade;
        String  str;
        long    currValue;

        while (sc.hasNextLine()) {
            str = sc.next();
            if (checkArguments(str, sc, weekCount) == 1) {
                break;
            }

            minGrade = 9;
            for (int i = 0; i < 5; ++i) {
                if (!sc.hasNextInt()) {
                    printError(sc);
                }

                grade = sc.nextInt();
                if (!(1 <= grade && grade <= 9)) {
                    printError(sc);
                }
                if (grade < minGrade) {
                    minGrade = grade;
                }
            }

            currValue = minGrade;
            for (int i = 0; i < weekCount; ++i) {
                currValue *= 10;
            }

            allGrades += currValue;
            ++weekCount;
        }
        printWeek(weekCount, allGrades);
    }

    private static void printError(Scanner sc) {

        System.err.println("IllegalArgument");
        sc.close();
        System.exit(-1);
    }

    private static int checkArguments(String str, Scanner sc, int weekCount) {
        
        if (str.equals("42")) {
            return (1);
        }
        if ((!str.equals("Week") || !sc.hasNextInt())
            || (weekCount == 18 || weekCount + 1 != sc.nextInt())) {
            printError(sc);
        }
        return (0);
    }

    private static void printWeek(int weekCount, long allGrades) {
        
        for (int i = 0; i < weekCount; ++i, allGrades /= 10) {
            System.out.print("Week ");
            System.out.print(i + 1);
            System.out.print(" ");
            for (int j = 0; j < (allGrades % 10); ++j) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}