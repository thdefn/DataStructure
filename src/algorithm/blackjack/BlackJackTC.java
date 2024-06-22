package algorithm.blackjack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackTC {
    private static final int DEALER_STAND_ON = 17;
    private static final int BUST_LIMIT = 21;

    public static void main(String[] args) {
        int[] inputSizes = {512000, 1024000, 2000000, 4000000, 8000000, 16000000};  // 다양한 입력 크기
        int iterations = 10;  // 각 입력 크기에 대해 여러 번 실행하여 평균값을 구함

        List<Integer> inputSizeList = new ArrayList<>();
        List<Long> durationList = new ArrayList<>();


        for (int size : inputSizes) {
            long totalDuration = 0;

            for (int i = 0; i < iterations; i++) {
                List<Integer> cards = initializeDeck(size);
                long startTime = System.nanoTime();
                List<List<Boolean>> record = new ArrayList<>();
                for (int j = 0; j < 1000; j++) {
                    Collections.shuffle(cards);
                    List<Boolean> cases = new ArrayList<>();
                    blackJack(cards, cases);
                    record.add(cases);
                }
                long endTime = System.nanoTime();
                totalDuration += (endTime - startTime);
            }

            long averageDuration = totalDuration / iterations;
            inputSizeList.add(size);
            durationList.add(averageDuration);
            System.out.println("입력 크기: " + size + ", 평균 실행 시간: " + averageDuration + " ns");
//
//            // 그래프를 그리기 위해 JFrame을 생성합니다.
        }
            JFrame frame = new JFrame("Time Complexity Analysis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new GraphPanel(inputSizeList, durationList));
            frame.setVisible(true);
    }

    public static List<Integer> initializeDeck(int size) {
        List<Integer> cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < size / 13; j++) {  // 입력 크기에 따라 카드 수를 조절
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
