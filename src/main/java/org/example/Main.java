package org.example;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(PrimeFinder.find(1000));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
