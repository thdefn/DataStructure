package algorithm.blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Graph extends JPanel {
    private List<Float> winRates;
    private int maxDataPoints;
    private float winRateResult;

    public Graph(List<Float> winRates, int maxDataPoints, float winRateResult) {
        this.winRates = winRates;
        this.maxDataPoints = maxDataPoints;
        this.winRateResult = winRateResult;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw axes
        int width = getWidth();
        int height = getHeight();

        g2.drawLine(50, height - 50, width - 50, height - 50); // X axis
        g2.drawLine(50, height - 50, 50, 50); // Y axis

        // Draw data
        if (winRates == null || winRates.isEmpty()) return;

        double maxWinRate = 100.0;
        int margin = 50;
        int graphWidth = width - 2 * margin;
        int graphHeight = height - 2 * margin;

        // Draw x-axis labels
        g2.drawString("Game Iteration", width / 2, height - 10);
        for (int i = 0; i <= 10; i++) {
            int x = margin + i * graphWidth / 10;
            g2.drawLine(x, height - margin, x, height - margin + 5);
            g2.drawString(String.valueOf(i * maxDataPoints / 10), x - 10, height - margin + 20);
        }

        // Draw y-axis labels
        g2.drawString("Win Rate (%)", 10, height / 2);
        for (int i = 0; i <= 10; i++) {
            int y = height - margin - i * graphHeight / 10;
            g2.drawLine(margin - 5, y, margin, y);
            g2.drawString(String.valueOf(i * 10), margin - 30, y + 5);
        }

        // Draw the 30% win rate line
        int y30 = height - margin - (int) (winRateResult * graphHeight / maxWinRate);
        g2.setColor(Color.GREEN);
        g2.drawLine(margin, y30, width - margin, y30);
        g2.setColor(Color.BLACK);

        int step = Math.max(1, winRates.size() / maxDataPoints); // Calculate step size

        for (int i = step; i < winRates.size(); i += step) {
            int x1 = margin + (i - step) * graphWidth / winRates.size();
            int y1 = height - margin - (int) (winRates.get(i - step) * graphHeight / maxWinRate);
            int x2 = margin + i * graphWidth / winRates.size();
            int y2 = height - margin - (int) (winRates.get(i) * graphHeight / maxWinRate);
            g2.drawLine(x1, y1, x2, y2);
        }
    }
}
