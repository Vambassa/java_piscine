import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int     count = 0;
        long    number;
        int     sum;
        boolean isPrime;

        while (sc.hasNextLong()) {
            number = sc.nextLong();
            sum = 0;
            isPrime = true;

            if (number == 42) {
                break;
            }

            while (number > 0) {
                sum += number % 10;
                number /= 10;
            }

            for (int i = 2; i * i <= sum; i++) {
                if (sum % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime && sum > 1) {
                count++;
            }
        }
        System.out.println("Count of coffee-request - " + count);
        sc.close();
    }
}
