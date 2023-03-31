package org.example;

import java.util.concurrent.ConcurrentSkipListSet;

// Класс задачи для нахождения простых чисел в заданном диапазоне
class PrimeFinderTask implements Runnable {
    private final int start;
    private final int end;
    private final ConcurrentSkipListSet<Integer> primes;

    public PrimeFinderTask(int start, int end, ConcurrentSkipListSet<Integer> primes) {
        this.start = start;
        this.end = end;
        this.primes = primes;

    }

    @Override
    public void run() {

        // Проверяем каждое число в заданном диапазоне на простоту и добавляем его в очередь, если оно простое
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                   primes.add(i);
                }
            }
        }

    // Метод, который проверяет, является ли заданное число простым
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
