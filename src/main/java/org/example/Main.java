package org.example;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        PrimeFinder.printPrimes(10000);

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
