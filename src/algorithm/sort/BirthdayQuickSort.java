package algorithm.sort;

import java.io.*;
import java.util.ArrayList;

public class BirthdayQuickSort {
    private static class Person {
        int birth;
        String name;

        public Person(int birth, String name) {
            this.birth = birth;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/birthday.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/algorithm/birthday.out"));
        String line;
        ArrayList<Person> days = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] input = line.split(" ");
            days.add(new Person(Integer.parseInt(input[1]), input[0]));
        }

        long totalEstimatedTime = 0;
        long totalUsedMemory = 0;

        for (int i = 0; i < 40; i++) {
            long startTime = System.nanoTime();
            quickSort(days, 0, days.size() - 1);
            Runtime.getRuntime().gc();
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long estimatedTime = System.nanoTime() - startTime;
            totalEstimatedTime += estimatedTime;
            totalUsedMemory += usedMemory;
        }
        System.out.println("quick sort memory usage: " + totalUsedMemory / 40 + " bytes");
        System.out.println("quick sort estimated time: " + totalEstimatedTime / 40);
        System.out.println();
    }

    private static void quickSort(ArrayList<Person> array, int begin, int end) {
        if (begin < end) {
            int part = partition(array, begin, end);
            quickSort(array, begin, part - 1);
            quickSort(array, part + 1, end);
        }
    }

    private static int partition(ArrayList<Person> array, int begin, int end) {
        int pivot = array.get(end).birth;
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array.get(j).birth <= pivot) {
                i++;

                if (i != j) swap(array, i, j);
            }
        }

        swap(array, end, i + 1);
        return i + 1;
    }

    private static void swap(ArrayList<Person> array, int indexA, int indexB) {
        Person temp = array.get(indexA);
        array.set(indexA, array.get(indexB));
        array.set(indexB, temp);
    }
}
