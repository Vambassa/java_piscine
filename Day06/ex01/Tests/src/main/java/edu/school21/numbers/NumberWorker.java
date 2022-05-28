package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {

        if (number <= 1) {
            throw new IllegalNumberException("IllegalNumberException");
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {

        long num;
        num = Math.abs(number);
        int result = 0;

        while (num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}