package algorithm.island.graph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultiGraphPanel extends JPanel {
    private List<List<Integer>> datasets;
    private List<Color> colors;
    private int xLabelStep = 2;
    private List<String> labels;

    public MultiGraphPanel(List<List<Integer>> datasets){
        this.datasets = datasets;
        this.colors = new ArrayList<>();
        this.labels = new ArrayList<>();
        // Add some colors for the different datasets
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);

        labels.add("island1");
        labels.add("island2");
        labels.add("island3");
        labels.add("integrated island");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 25;
        int labelPadding = 25;
        int pointWidth = 4;
        int numberYDivisions = 10;

        // Draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width - (2 * padding) - labelPadding, height - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // Create hatch marks and grid lines for y-axis
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, width - padding, y1);
            g2.setColor(Color.BLACK);
            String yLabel = ((int) ((getMaxScore() * ((i * 1.0) / numberYDivisions)) * 100)) / 100 + "";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
        }

        // Create hatch marks and grid lines for x-axis
        int maxDataSize = getMaxDataSize();
        for (int i = 0; i < maxDataSize; i++) {
            if (i % xLabelStep == 0) {  // 일정 간격마다 레이블을 표시
                int x0 = i * (width - padding * 2 - labelPadding) / (maxDataSize - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = height - padding - labelPadding;
                int y1 = y0 - pointWidth;
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawLine(x0, height - padding - labelPadding - 1 - pointWidth, x1, padding);
                g2.setColor(Color.BLACK);
                String xLabel = i + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            }
        }

        // Create x and y axes
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        for (int k = 0; k < datasets.size(); k++) {
            List<Integer> data = datasets.get(k);
            g2.setColor(colors.get(k % colors.size()));
            g2.setStroke(new BasicStroke(2f));

            // Draw the graph line
            for (int i = 0; i < data.size() - 1; i++) {
                int x1 = i * (width - padding * 2 - labelPadding) / (maxDataSize - 1) + padding + labelPadding;
                int y1 = height - padding - labelPadding - (data.get(i) * (height - padding * 2 - labelPadding)) / getMaxScore();
                int x2 = (i + 1) * (width - padding * 2 - labelPadding) / (maxDataSize - 1) + padding + labelPadding;
                int y2 = height - padding - labelPadding - (data.get(i + 1) * (height - padding * 2 - labelPadding)) / getMaxScore();
                g2.drawLine(x1, y1, x2, y2);
            }

            g2.setStroke(oldStroke);
            g2.setColor(colors.get(k % colors.size()));

            // Draw the points
            for (int i = 0; i < data.size(); i++) {
                int x = i * (width - padding * 2 - labelPadding) / (maxDataSize - 1) + padding + labelPadding;
                int y = height - padding - labelPadding - (data.get(i) * (height - padding * 2 - labelPadding)) / getMaxScore();
                g2.fillOval(x - pointWidth / 2, y - pointWidth / 2, pointWidth, pointWidth);
            }
        }
        drawLegend(g2, width, height);
    }

    private void drawLegend(Graphics2D g2, int width, int height) {
        int legendX = width - 150;
        int legendY = 30;
        int legendWidth = 120;
        int legendHeight = datasets.size() * 20 + 10;

        g2.setColor(Color.WHITE);
        g2.fillRect(legendX, legendY, legendWidth, legendHeight);
        g2.setColor(Color.BLACK);
        g2.drawRect(legendX, legendY, legendWidth, legendHeight);

        for (int i = 0; i < datasets.size(); i++) {
            g2.setColor(colors.get(i % colors.size()));
            g2.fillRect(legendX + 10, legendY + 10 + i * 20, 10, 10);
            g2.setColor(Color.BLACK);
            g2.drawString(labels.get(i), legendX + 30, legendY + 20 + i * 20);
        }
    }

    private int getMaxScore() {
        int maxScore = Integer.MIN_VALUE;
        for (List<Integer> data : datasets) {
            for (int score : data) {
                maxScore = Math.max(maxScore, score);
            }
        }
        return maxScore;
    }

    private int getMaxDataSize() {
        int maxSize = 0;
        for (List<Integer> data : datasets) {
            maxSize = Math.max(maxSize, data.size());
        }
        return maxSize;
    }
}
