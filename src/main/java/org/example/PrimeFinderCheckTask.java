package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// Класс задачи для нахождения простых чисел в заданном диапазоне
class PrimeFinderCheckTask implements Runnable {
    private final int number;
    private final ArrayBlockingQueue<Integer> primes;
    private final AtomicInteger primesCount;

    public PrimeFinderCheckTask(ArrayBlockingQueue<Integer> primes, int number, AtomicInteger primesCount) {
        this.number = number;
        this.primes = primes;
        this.primesCount = primesCount;
    }

    @Override
    public void run() {
        // Проверяем каждое число в заданном диапазоне на простоту и добавляем его в очередь, если оно простое
        try{
            if (isPrime(number)) {
                primes.add(number);
                primesCount.incrementAndGet();
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
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
