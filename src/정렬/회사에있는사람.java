package 정렬;

import java.util.*;

/**
 * Set - TreeSet
 * 사전 역순에 대한 출력이므로 ordered collection 인 TreeSet 활용
 */
public class 회사에있는사람 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Set<String> set = new TreeSet<>();
        // N * O(L * Log(N))
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            String status = sc.next();
            // O(L * Log(N)) - L은 name의 길이
            if (status.equals("enter")) // 집합에 추가
                set.add(name);
            else set.remove(name); // 집합에서 제거
        }
        String[] orderedName = set.toArray(new String[set.size()]);
        for (int i = orderedName.length - 1; i >= 0; i--)
            System.out.println(orderedName[i]);
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[][] records = new String[N][2];
        for (int i = 0; i < N; i++) {
            records[i][0] = sc.next();
            records[i][1] = sc.next();
        }
        // 1. 이름 순서에 따라 출입 기록을 차례로 정렬한다.
        Arrays.sort(records, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o2[0].compareTo(o1[0]);
            }
        });

        // 2. 각 사원마다 마지막 기록이 enter 라면, 출력한다. (사전 역순)
        for (int i = 0; i < N - 1; i++)
            if (!records[i][0].equals(records[i + 1][0])
                    && records[i][1].equals("enter"))
                System.out.println(records[i][0]);
        // 이후의 값에 대한 비교이므로 마지막값의 처리는 따로
        // n - 1 번 레코드는 그 사람의 마지막 레코드
        if (records[N - 1][1].equals("enter"))
            System.out.println(records[N - 1][0]);
    }
}
