package 정렬.풀이;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 베스트셀러 {
    static class SalesRate {
        int count;
        String title;

        SalesRate(int count, String title) {
            this.count = count;
            this.title = title;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] titles = new String[N];
        for (int i = 0; i < N; i++) {
            titles[i] = sc.next();
        }
        Arrays.sort(titles, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        SalesRate[] rates = new SalesRate[N];
        int cnt = 0;
        int salesCount = 0;
        int maxCount = 0;
        for (int i = 0; i < N - 1; i++) {
            salesCount++;
            if (!titles[i].equals(titles[i + 1])) {
                rates[cnt++] = new SalesRate(salesCount, titles[i]);
                if (salesCount > maxCount) maxCount = salesCount;
                salesCount = 0;
            }
        }
        salesCount++;
        rates[cnt++] = new SalesRate(salesCount, titles[N - 1]);
        if (salesCount > maxCount) maxCount = salesCount;

        for (int i = 0; i < cnt; i++) {
            if (rates[i].count == maxCount) {
                System.out.println(rates[i].title);
                break;
            }
        }
    }
}
