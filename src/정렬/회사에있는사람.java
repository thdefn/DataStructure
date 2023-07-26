package 정렬;

import java.util.Arrays;
import java.util.Scanner;

public class 회사에있는사람 {
    static class Worker implements Comparable<Worker> {
        String name;
        boolean status;

        Worker(String name, boolean status) {
            this.name = name;
            this.status = status;
        }

        @Override
        public int compareTo(Worker o) {
            if (o.name.equals(name))
                return status ? -1 : 1;
            return o.name.compareTo(name);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++)
            workers[i] = new Worker(sc.next(), "enter".equals(sc.next()));
        Arrays.sort(workers);
        for (int i = 0; i < n - 1; i++) {
            if (!workers[i].status) continue;
            if (workers[i].name.equals(workers[i + 1].name) && !workers[i + 1].status)
                continue;
            System.out.println(workers[i].name);
        }
        if(workers[n-1].status) System.out.println(workers[n-1].name);
    }
}
