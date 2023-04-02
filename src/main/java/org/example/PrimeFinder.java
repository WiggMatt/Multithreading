package org.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;


public class PrimeFinder {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors(); // Количество потоков, которое будет использоваться

    static ArrayBlockingQueue <Integer> findPrimes(int primesNum) {
        // Создаем пул с фиксированным количеством потоков
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        // Создаем очередь для хранения простых чисел
        ArrayBlockingQueue<Integer> primes = new ArrayBlockingQueue<>(primesNum);
        AtomicInteger primesCount = new AtomicInteger(0);

        while (primes.size() < primesNum) {
            // создаем новую задачу и добавляем ее в очередь исполнения
            executor.execute(new PrimeFinderCheckTask(primes, primesCount));
            if(primes.size() == primesNum){
                // завершаем работу исполнительного сервиса
                executor.shutdownNow();
            }
        }
        // Останавливаем исполнительный сервис, когда все задачи выполнены
        executor.shutdownNow();

        try {
            if (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)){
                System.out.println("Error");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return primes;
    }

    public static void printPrimes(int primesNum) {
        List<Integer> sortedPrimes = new ArrayList<>(findPrimes(primesNum));
        Collections.sort(sortedPrimes);
        for (Integer prime : sortedPrimes) {
            System.out.println(prime);
        }
        System.out.println("Размер коллекции: "+ sortedPrimes.size());
    }
}

