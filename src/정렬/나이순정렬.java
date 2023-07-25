package 정렬;

import java.util.Arrays;
import java.util.Scanner;

/**
 * compare(Object o1, Object o2) : o1에서 o2를 빼면 오름차순이다
 * Object 배열 정렬 = ComparableTimSort -> Stable 하다. 입력 순서대로 유지된다.
 * 복잡한 값의 기록에는 클래스를 활용하자
 */
public class 나이순정렬 {
    static class Member implements Comparable<Member> {
        int age;
        String name;

        Member(int age, String name) {
            this.age = age;
            this.name = name;
        }

        // 1. 나이가 작은 회원 먼저
        // 2. 나이가 같으면 먼저 가입한 사람 먼저
        @Override
        public int compareTo(Member o) {
            return age - o.age;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Member[] members = new Member[N];
        for (int i = 0; i < N; i++)
            members[i] = new Member(sc.nextInt(), sc.next());

        // O(NLogN)
        Arrays.sort(members);

        for (Member member : members)
            System.out.println(member.age + " " + member.name);
    }
}
