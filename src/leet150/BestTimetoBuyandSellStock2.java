package leet150;

public class BestTimetoBuyandSellStock2 {
    public static void main(String[] args) {
        int[] prices = {7, 2, 5, 1, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int before = 0;
        int max = 0;
        int answer = 0;
        for (int i = 0; i < prices.length; i++) {
            if (before > prices[i]) {
                int profit = max - min;
                answer += profit;
                min = prices[i];
                max = 0;
            }
            before = prices[i];
            if (prices[i] > max) max = prices[i];
        }
        int profit = max - min;
        answer += profit;
        return answer;
    }
}
