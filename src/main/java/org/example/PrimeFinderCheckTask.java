package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class PrimeFinderCheckTask implements Runnable {
    private final ArrayBlockingQueue<Integer> primes;
    private final AtomicInteger primesCount;

    public PrimeFinderCheckTask(ArrayBlockingQueue<Integer> primes, AtomicInteger primesCount) {
        this.primes = primes;
        this.primesCount = primesCount;
    }

    @Override
    public void run() {
        int num = primesCount.incrementAndGet();
        try{
            if (isPrime(num)) {
                primes.add(num);
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
