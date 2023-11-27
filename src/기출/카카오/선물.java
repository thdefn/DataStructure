package 기출.카카오;

import java.util.HashMap;
import java.util.Map;

public class 선물 {
    public static void main(String[] args) {


    }

    public static int solution(String[] friends, String[] gifts) {
        Map<String, Integer> giftCountMap = new HashMap<>();
        Map<String, Integer> giftIndexMap = new HashMap<>();

        for (String gift : gifts) {
            String[] giverAndTaker = gift.split(" ");
            String giver = giverAndTaker[0];
            String taker = giverAndTaker[1];
            giftIndexMap.put(giver, giftIndexMap.getOrDefault(giver, 0) + 1);
            giftIndexMap.put(taker, giftIndexMap.getOrDefault(taker, 0) - 1);

            giftCountMap.put(gift, giftCountMap.getOrDefault(gift, 0) + 1);
        }

        int answer = 0;
        int[] giftPredict = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            String friendA = friends[i];
            for (int j = i + 1; j < friends.length; j++) {
                String friendB = friends[j];
                int giftCountFromAtoB = giftCountMap.getOrDefault(friendA + " " + friendB, 0);
                int giftCountFromBtoA = giftCountMap.getOrDefault(friendB + " " + friendA, 0);
                if (giftCountFromAtoB == giftCountFromBtoA) {
                    int giftIndexA = giftIndexMap.getOrDefault(friendA, 0);
                    int giftIndexB = giftIndexMap.getOrDefault(friendB, 0);
                    if (giftIndexA > giftIndexB)
                        answer = Math.max(++giftPredict[i], answer);
                    else if (giftIndexA < giftIndexB)
                        answer = Math.max(++giftPredict[j], answer);
                } else if (giftCountFromAtoB > giftCountFromBtoA) {
                    answer = Math.max(++giftPredict[i], answer);
                } else {
                    answer = Math.max(++giftPredict[j], answer);
                }
            }
        }
        return answer;
    }
}
