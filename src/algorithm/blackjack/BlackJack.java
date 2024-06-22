package algorithm.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJack {
    private static final int DEALER_STAND_ON = 17;
    private static final int BUST_LIMIT = 21;

    public static void main(String[] args) {
        List<Integer> cards = initializeDeck();
        List<List<Boolean>> record = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Collections.shuffle(cards);
            List<Boolean> cases = new ArrayList<>();
            blackJack(cards, cases);
            record.add(cases);
        }

        long total = 0;
        long winningCase = 0;
        for (int i = 0; i < 1; i++) {
            long totalCount = record.get(i).size();
            long winningCount = record.get(i).stream().filter(c -> c).count();
            total += totalCount;
            winningCase += winningCount;
        }

        float result = (float) winningCase / total * 100;
        System.out.println("최종 승률 : " + result);
    }

    public static List<Integer> initializeDeck() {
        List<Integer> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(i);
            }
        }
        return cards;
    }

    public static void blackJack(List<Integer> cards, List<Boolean> cases) {
        cheatingBlackJack(cards, 0, 0, 0, true, cases);
    }

    private static int cheatingBlackJack(List<Integer> cards, int i, int playerScore, int dealerScore, boolean playerTurn, List<Boolean> cases) {
        if (i == 0) {
            playerScore += cards.get(0);
            playerScore += cards.get(1);
            dealerScore += cards.get(2);
            dealerScore += cards.get(3);
            i = 4;
        }


        if (playerTurn && playerScore > BUST_LIMIT) {
            cases.add(false);
            return -1;
        }

        if (!playerTurn && dealerScore > BUST_LIMIT) {
            cases.add(true);
            return 1;
        }

        if (playerTurn) {
            int hitResult = cheatingBlackJack(cards, i + 1, playerScore + cards.get(i), dealerScore, true, cases);
            int standResult = cheatingBlackJack(cards, i, playerScore, dealerScore, false, cases);
            return Math.max(hitResult, standResult);
        } else {
            while (dealerScore < DEALER_STAND_ON && i < cards.size()) {
                dealerScore += cards.get(i++);
                if (dealerScore > BUST_LIMIT) {
                    cases.add(true);
                    return 1; // 딜러가 버스트하면 $1 승리
                }
            }
        }

        if (dealerScore > playerScore) {
            cases.add(false);
            return -1;
        } else if (dealerScore == playerScore) {
            cases.add(false);
            return 0;
        } else {
            cases.add(true);
            return 1;
        }
    }
}
