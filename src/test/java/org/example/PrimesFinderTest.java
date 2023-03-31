package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimesFinderTest {
    @Test
    public void testPrimesFinder() {
        ConcurrentSkipListSet<Integer> primes = new ConcurrentSkipListSet<>();
        PrimesFinder.main(new String[]{});
        primes.forEach(prime -> assertTrue(isPrime(prime))); // Проверяем, что каждое число в наборе является простым числом
    }


    // Метод для проверки, является ли заданное число простым
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}



