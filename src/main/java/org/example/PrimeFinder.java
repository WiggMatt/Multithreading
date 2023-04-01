package org.example;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;

public class PrimeFinder {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors(); // Количество потоков, которое будет использоваться

    static ArrayBlockingQueue <Integer>  find (int primesNum) {

        // Создаем пул с фиксированным количеством потоков
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        // Создаем очередь для хранения простых чисел
        ArrayBlockingQueue<Integer> primes = new ArrayBlockingQueue<>(primesNum);
        AtomicInteger primesCount = new AtomicInteger(0);
        int numToCheck = 2;

        while (primesCount.get() < primesNum) {
            // создаем новую задачу и добавляем ее в очередь исполнения
            executor.execute(new PrimeFinderCheckTask(primes, numToCheck, primesCount));
            numToCheck++;
            if(primesCount.get() == primesNum){
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
        System.out.println("Размер коллекции: "+ primes.size());
        return primes;
    }
}

