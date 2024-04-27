package algorithm.sort;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class BirthdayMergeSort {
    private static class Person {
        int birth;
        String name;

        public Person(int birth, String name) {
            this.birth = birth;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sort/birthday.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/algorithm/sort/birthday.out"));
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
            mergeSort(days, 0, days.size() - 1);
            for (Person p : days){
                System.out.println(p.birth);
            }
        }
        System.out.println("merge sort memory usage: " + totalUsedMemory/40 + " bytes");
        System.out.println("merge sort estimated time: " + totalEstimatedTime/40);
        System.out.println();
    }

    private static ArrayList<Person> mergeSort(ArrayList<Person> arrays, int l, int r) {
        if (l == r)
            return new ArrayList<>(Collections.singletonList(arrays.get(l)));

        int m = (l + r) / 2;
        ArrayList<Person> left = mergeSort(arrays, l, m);
        ArrayList<Person> right = mergeSort(arrays, m + 1, r);
        return merge(left, right);
    }

    private static ArrayList<Person> merge(ArrayList<Person> left, ArrayList<Person> right) {
        ArrayList<Person> merged = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l < left.size() && r < right.size()) {
            if (left.get(l).birth <= right.get(r).birth)
                merged.add(left.get(l++));
            else merged.add(right.get(r++));
        }

        while (l < left.size()) {
            merged.add(left.get(l++));
        }

        while (r < right.size()) {
            merged.add(right.get(r++));
        }

        return merged;
    }
}
