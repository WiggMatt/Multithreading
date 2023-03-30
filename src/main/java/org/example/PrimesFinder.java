package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class PrimesFinder {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors(); // Количество потоков, которое будет использоваться
    private static final int BATCH_SIZE = 1000; // Размер пакета чисел, который будет обрабатываться каждым потоком
    private static final int NUM_PRIMES_TO_FIND = 1000; // Количество простых чисел, которые нужно найти

    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();

        // Создаем пул с фиксированным количеством потоков
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        // Создаем очередь для хранения простых чисел
        ConcurrentSkipListSet<Integer> primes = new ConcurrentSkipListSet<>();
        // Создаем список задач
        List<PrimeFinderTask> tasks = new ArrayList<>();

        // Создаем задачи для каждого пакета чисел и запускаем их в исполнительном сервисе
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * BATCH_SIZE + 1;
            int end = (i + 1) * BATCH_SIZE;
            PrimeFinderTask task = new PrimeFinderTask(start, end, primes);
            tasks.add(task);
            executor.submit(task);
        }

        // Останавливаем исполнительный сервис, когда все задачи выполнены
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // Сортируем список простых чисел и выводим на экран
        List<Integer> sortedPrimes = new ArrayList<>(primes);
        Collections.sort(sortedPrimes);

        for (int i = 0; i < NUM_PRIMES_TO_FIND && i < sortedPrimes.size(); i++) {
            System.out.println(sortedPrimes.get(i));
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - time) + " ms");
        System.out.println(sortedPrimes.size());
    }
}

