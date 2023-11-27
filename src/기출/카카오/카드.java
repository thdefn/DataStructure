package 기출.카카오;

import java.util.*;

public class 카드 {

    public static void main(String[] args) {
        int coin= 4;
        int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
        System.out.println(solution(coin, cards));
    }

    public static int solution(int coin, int[] cards) {
        boolean[] isExist = new boolean[cards.length + 1];
        for (int i = 0; i < cards.length; i++) {
            isExist[cards[i]] = true;
        }


        Queue<Integer> myCard = new LinkedList<>();
        for (int i = 0; i < cards.length / 3; i++) {
            myCard.add(cards[i]);
        }
        int index = cards.length / 3;
        int paidSum = cards.length + 1;
        int answer = 1;
        boolean goToNextRound = true;

        while (goToNextRound) {
            int cardA = cards[index++];
            int cardB = cards[index++];

            if (coin > 0 && isExist[paidSum - cardA]) {
                myCard.add(cardA);
                coin--;
            }
            if (coin > 0 && isExist[paidSum - cardB]) {
                myCard.add(cardB);
                coin--;
            }
            goToNextRound = false;

            for (int card: myCard) {
                if(myCard.contains(paidSum - card)){
                    goToNextRound = true;
                    myCard.remove(card);
                    myCard.remove(paidSum - card);
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
