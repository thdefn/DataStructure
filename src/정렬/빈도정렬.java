package 정렬;

import java.util.*;

/**
 * 주어지는 숫자의 범위 C가 최대 10억이기 때문에 Count 배열을 이용해 빈도를 구하는 방법은 메모리가 부족하다
 * 클래스의 활용
 * Arrays.sort NullPointException
 * LinkedHashMap : 삽입 순서를 유지하는 HashMap O(NLog(N))
 */
public class 빈도정렬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int C = sc.nextInt();

        // O(N*1)
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < N; i++) {
            int message = sc.nextInt();
            map.put(message, map.getOrDefault(message, 0) + 1);
        }

        // O(N*Log(N))
        Integer[] keys = map.keySet().toArray(new Integer[map.size()]);
        // 1. 더 많이 등장한 숫자 먼저
        // 2. 등장 횟수가 같다면 입력으로 먼저 들어온 것이 먼저
        Arrays.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2) - map.get(o1);
            }
        });

        // O(N*1)
        for (int key: keys) {
            int count = map.get(key);
            while (count-- > 0)
                System.out.print(key + " ");
        }
        System.out.println();
    }

    static class Message {
        int num;
        int index;

        Message(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    static class Frequency {
        int num;
        int count;
        int firstIndex;

        Frequency(int num, int count, int firstIndex) {
            this.num = num;
            this.count = count;
            this.firstIndex = firstIndex;
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int C = sc.nextInt();

        // O(N)
        Message[] messages = new Message[N];
        for (int i = 0; i < N; i++)
            messages[i] = new Message(sc.nextInt(), i);

        // O(N*Log(N))
        Arrays.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.num - o2.num;
            }
        });

        // O(N)
        Frequency[] frequencies = new Frequency[N];
        int frequencyIndex = 0;
        frequencies[frequencyIndex] = new Frequency(messages[0].num, 1, messages[0].index);
        for (int i = 1; i < N; i++) {
            if (messages[i].num != messages[i - 1].num)
                frequencies[++frequencyIndex] = new Frequency(messages[i].num, 0, messages[i].index);
            frequencies[frequencyIndex].count++;
        }
        // 1. 더 많이 등장한 숫자 먼저
        // 2. 등장 횟수가 같다면 입력으로 먼저 들어온 것이 먼저
        // O(N*Log(N))
        Arrays.sort(frequencies, 0, frequencyIndex + 1, new Comparator<Frequency>() {
            @Override
            public int compare(Frequency o1, Frequency o2) {
                if (o1.count == o2.count)
                    return o1.firstIndex - o2.firstIndex;
                return o2.count - o1.count;
            }
        });

        // O(N)
        for (int i = 0; i <= frequencyIndex; i++) {
            while (frequencies[i].count-- > 0)
                System.out.print(frequencies[i].num + " ");
        }
        System.out.println();
    }
}
