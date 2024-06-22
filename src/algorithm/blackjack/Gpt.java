package algorithm.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gpt {
    // Card values: 2-10, Jack=11, Queen=12, King=13, Ace=1 or 11
    private static final int[] DECK = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
    private static List<Integer> deck = new ArrayList<>();
    private static int deckIndex = 0;

    // Dealer must stand on 17
    private static final int DEALER_STAND = 17;

    // Method to initialize a known deck order
    private static void initializeDeck() {
        deck = new ArrayList<>();
        // Add multiple decks if necessary, here we assume a single deck
        for (int i = 0; i < 4; i++) {
            for (int card : DECK) {
                deck.add(card);
            }
        }
        Collections.shuffle(deck); // Shuffle to simulate a real deck order
        deckIndex = 0;
    }

    // Method to draw a card from the deck
    private static int drawCard() {
        if (deckIndex >= deck.size()) {
            initializeDeck();
        }
        return deck.get(deckIndex++);
    }

    // Calculate the best action (hit or stand) for the player
    private static String getPlayerDecision(int playerTotal, int dealerUpCard) {
        // If player total is 21, they should stand
        if (playerTotal == 21) return "stand";

        // Simple strategy: keep hitting if below 17
        // In a real scenario, the player would have a more complex strategy
        if (playerTotal < 17) return "hit";
        return "stand";
    }

    // Calculate the hand value, considering aces
    private static int calculateHandValue(List<Integer> hand) {
        int total = 0;
        int aces = 0;

        for (int card : hand) {
            if (card == 11) aces++;
            total += card;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    // Simulate one game and return the outcome (win/loss)
    private static boolean playGame() {
        // Initial hands
        List<Integer> playerHand = new ArrayList<>();
        List<Integer> dealerHand = new ArrayList<>();

        // Deal initial cards
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());

        int dealerUpCard = dealerHand.get(0);
        int playerTotal = calculateHandValue(playerHand);

        // Player's turn
        while (true) {
            String decision = getPlayerDecision(playerTotal, dealerUpCard);
            if (decision.equals("stand")) break;
            playerHand.add(drawCard());
            playerTotal = calculateHandValue(playerHand);
            if (playerTotal > 21) return false; // Player busts
        }

        // Dealer's turn
        int dealerTotal = calculateHandValue(dealerHand);
        while (dealerTotal < DEALER_STAND) {
            dealerHand.add(drawCard());
            dealerTotal = calculateHandValue(dealerHand);
        }

        // Determine the outcome
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            return true; // Player wins
        }
        return false; // Player loses or ties (dealer wins ties in this simulation)
    }

    public static void main(String[] args) {
        initializeDeck();
        int games = 1000000; // Number of games to simulate
        int wins = 0;

        for (int i = 0; i < games; i++) {
            if (playGame()) wins++;
        }

        double winRate = (double) wins / games;
        System.out.println("Win rate: " + winRate);
    }
}
