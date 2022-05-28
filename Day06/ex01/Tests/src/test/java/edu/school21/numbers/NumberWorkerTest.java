package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    NumberWorker worker;

    @BeforeEach
    void beforeEachMethod() {
        worker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 7, 13, 163, 199})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 144, 256, 309})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(worker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1000, -397, -1, 0, 1})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> worker.isPrime(number));
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    void checkSum(int number, int expected) {
        Assertions.assertEquals(expected, worker.digitsSum(number));
    }
}
