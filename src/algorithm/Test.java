package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> li = Arrays.asList("hi", "김김", "123");
        Collections.sort(li, Collections.reverseOrder());
        System.out.println(li);
    }
}
