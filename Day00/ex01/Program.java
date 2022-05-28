import java.util.Scanner;

public class Program {

    private static void printResult(boolean isPrime, int count) {

        System.out.print(isPrime);
        System.out.print(' ');
        System.out.println(count - 1);
        System.exit(0);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) {
            System.err.println("Illegal Argument");
            sc.close();
            System.exit(-1);
        } else {
            int number = sc.nextInt();

            if (number <= 1) {
                System.err.println("Illegal Argument");
                sc.close();
                System.exit(-1);
            }

            boolean isPrime = true;
            int i = 2;
            for (i = 2; i * i <= number && isPrime; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    sc.close();
                    printResult(isPrime, i);
                }
            }
            sc.close();
            printResult(isPrime, i);
        }
    }
}
