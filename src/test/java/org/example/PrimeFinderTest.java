package org.example;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ArrayBlockingQueue;



import static org.junit.jupiter.api.Assertions.assertEquals;
class PrimeFinderTest {
    @Test
    public void testPrimeFinder() {
        int[] expectedLastPrimes = {541, 3571, 7919, 104729};// ожидаемые значения последнего числа
        int[] expectedSizes = {100, 500, 1000, 10000}; // количество чисел, необхожимых к выводу

        for (int i = 0; i < expectedLastPrimes.length; i++) {
            int expectedLastPrime = expectedLastPrimes[i];
            int expectedSize = expectedSizes[i];
            ArrayBlockingQueue<Integer> primes = PrimeFinder.findPrimes(expectedSize);
            // Получаем последнее простое число
            Integer[] primeArray = primes.toArray(new Integer[0]);
            int lastPrime = primeArray[primeArray.length - 1];
            // Проверяем, что количество простых чисел равно ожидаемому
            assertEquals(expectedSize, primes.size());
            // Проверяем, что последнее простое число равно ожидаемому
            assertEquals(expectedLastPrime, lastPrime);
        }
    }
}



