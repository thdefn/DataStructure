package 정렬;

import java.util.*;

/**
 * Map - key, value 쌍 Collection 중복된 key 는 갖지 않는다
 * HashMap 삽입, 삭제, 조회 연산 O(1)
 * TreeMap 삽입, 삭제, 조회 연산 O(Log(size))
 */
public class 베스트셀러 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Map<String, Integer> titles = new HashMap<>();
        // O(N * 1) -> HashMap 을 사용하므로 String 비교 연산 없다
        for (int i = 0; i < N; i++) {
            String title = sc.next();
            // O(N)
            titles.put(title, titles.getOrDefault(title, 0) + 1);
        }

        String maxTitle = "";
        int maxCount = 0;
        // O(N)
        for (Map.Entry<String, Integer> title : titles.entrySet()) {
            String titleName = title.getKey();
            int count = title.getValue();
            if (count > maxCount ||
                    (count == maxCount && titleName.compareTo(maxTitle) < 0)) {
                maxTitle = titleName;
                maxCount = count;
            }
        }
        System.out.println(maxTitle);
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] titles = new String[N];
        for (int i = 0; i < N; i++)
            titles[i] = sc.next();

        // O(N * Log(N))
        Arrays.sort(titles);

        String maxTitle = titles[0];
        int maxCount = 1;
        int currentCount = 1;
        // O(N)
        for (int i = 1; i < N; i++) {
            if (!titles[i].equals(titles[i - 1]))
                currentCount = 0;
            currentCount++;
            if (currentCount > maxCount) {
                maxTitle = titles[i];
                maxCount = currentCount;
            }
        }
        System.out.println(maxTitle);
    }
}
