package algorithm.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlackJackCardCounting {
    private static final int DEALER_STAND_ON = 17;
    private static final int BUST_LIMIT = 21;
    private static final int[] CARD_VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
    private static final int[] CARD_COUNT_VALUES = {-1, 1, 1, 1, 1, 0, 0, 0, -1, -1, -1, -1, -1}; // 카운팅에 따른 값 (-1, 0, 1)
    private static final int COUNT_THRESHOLD = 2; // 카운팅 임계값

    public static void main(String[] args) {
        List<Integer> deck = initializeDeck();
        List<Boolean> cases = new ArrayList<>();
        int gamesToSimulate = 1000000;
        simulateGames(deck, gamesToSimulate, cases);

        long totalGames = cases.size();
        long winGames = cases.stream().filter(Boolean::booleanValue).count();
        float winRate = (float) winGames / totalGames * 100;

        System.out.println("플레이어의 최종 승률: " + (100 - winRate) + "%");
    }

    public static List<Integer> initializeDeck() {
        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) { // 각 카드를 네 번씩 추가하여 덱 초기화
            for (int value : CARD_VALUES) {
                cards.add(value);
            }
        }
        return cards;
    }

    public static void simulateGames(List<Integer> deck, int numGames, List<Boolean> cases) {
        Random random = new Random();
        for (int i = 0; i < numGames; i++) {
            List<Integer> copyDeck = new ArrayList<>(deck); // 복사된 덱 사용
            int dealerScore = 0;
            int count = 0; // 초기 카운팅 값
            boolean result = dealerBlackJack(copyDeck, dealerScore, count);
            cases.add(result);
        }
    }

    public static boolean dealerBlackJack(List<Integer> cards, int dealerScore, int count) {
        Random random = new Random();
        while (dealerScore < DEALER_STAND_ON && dealerScore <= BUST_LIMIT) {
            int index = random.nextInt(cards.size());
            int drawnCard = cards.remove(index); // 덱에서 카드를 뽑음
            dealerScore += drawnCard;

            // 카운팅 업데이트
            count += CARD_COUNT_VALUES[drawnCard - 2]; // drawnCard는 2부터 시작하기 때문에 인덱스에 맞춰서 처리

            // 카운팅에 따라 스탠딩 결정
            if (count >= COUNT_THRESHOLD) {
                return false; // 스탠드
            }
        }
        return true; // 히트
    }
}
