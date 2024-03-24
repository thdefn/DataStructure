package algorithm.sort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BirthdaySort {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/birthday.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Arrays.sort(new int[]{1,2,3});
        ArrayList<Integer> days = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] input = line.split(" ");
            names.add(input[0]);
            days.add(Integer.parseInt(input[1]));
        }

        System.out.println(mergeSort(days, 0, days.size() - 1));
    }

    private static ArrayList<Integer> mergeSort(ArrayList<Integer> arrays, int l, int r) {
        if (l == r)
            return new ArrayList<>(Arrays.asList(arrays.get(l)));

        int m = (l + r) / 2;
        ArrayList<Integer> left = mergeSort(arrays, l, m);
        ArrayList<Integer> right = mergeSort(arrays, m + 1, r);
        return merge(left, right);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> merged = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l < left.size() && r < right.size()) {
            if (left.get(l) <= right.get(r))
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
