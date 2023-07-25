package 정렬.풀이;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 나이순정렬 {
    static class Member{
        int age;
        String name;

        Member(int age, String name){
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Member[] arr = new Member[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Member(sc.nextInt(), sc.next());
        }
        Arrays.sort(arr, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return o1.age - o2.age;
            }
        });

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i].age + " " + arr[i].name);
        }
    }
}
