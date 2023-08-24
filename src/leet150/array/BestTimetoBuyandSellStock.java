package leet150.array;

public class BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int[] prices = {7, 2, 5, 1, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        int answer = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                int profit = max - min;
                if (profit > answer) answer = profit;
                min = prices[i];
                max = 0;
            }
            if (prices[i] > max) max = prices[i];
        }
        int profit = max - min;
        if (profit > answer) answer = profit;
        return answer;
    }
}
