package algorithm.blackjack;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private final List<Integer> inputSizes;
    private final List<Long> durations;

    public GraphPanel(List<Integer> inputSizes, List<Long> durations) {
        this.inputSizes = inputSizes;
        this.durations = durations;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int padding = 25;
        int labelPadding = 25;
        int pointWidth = 4;
        int numberYDivisions = 10;

        // 그래프 배경
        g.setColor(Color.WHITE);
        g.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
        g.setColor(Color.BLACK);

        // y축 그리기
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = height - ((i * (height - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (inputSizes.size() > 0) {
                g.setColor(Color.GRAY);
                g.drawLine(padding + labelPadding + 1 + pointWidth, y0, width - padding, y1);
                g.setColor(Color.BLACK);
                String yLabel =  formatNumber(getMaxDuration() * ((i * 1.0) / numberYDivisions));
                FontMetrics metrics = g.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g.drawLine(x0, y0, x1, y1);
        }

        // x축 그리기
        for (int i = 0; i < inputSizes.size(); i++) {
            if (inputSizes.size() > 1) {
                int x0 = i * (width - padding * 2 - labelPadding) / (inputSizes.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = height - padding - labelPadding;
                int y1 = y0 - pointWidth;
                g.setColor(Color.GRAY);
                g.drawLine(x0, height - padding - labelPadding - 1 - pointWidth, x1, padding);
                g.setColor(Color.BLACK);
                String xLabel = inputSizes.get(i) + "";
                FontMetrics metrics = g.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            }
        }


//        // y=x 선 그리기
//        g.setColor(Color.BLACK);
//        g.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, padding);

//        // y=x^2 그래프 그리기
//        g.setColor(Color.BLACK);
//        //g.setStroke(new BasicStroke(2f));
//        double xScale = ((double) (width - padding * 2 - labelPadding)) / (inputSizes.get(inputSizes.size() - 1) - inputSizes.get(0));
//        double yScale = ((double) (height - padding * 2 - labelPadding)) / (getMaxDuration() - 0);
//        List<Point> graphPoints = new ArrayList<>();
//        for (int i = 0; i < inputSizes.size(); i++) {
//            int x1 = (int) ((inputSizes.get(i) - inputSizes.get(0)) * xScale + padding + labelPadding);
//            int y1 = height - (int) (Math.pow(inputSizes.get(i), 2) * yScale + padding + labelPadding);
//            graphPoints.add(new Point(x1, y1));
//        }
//        for (int i = 0; i < graphPoints.size() - 1; i++) {
//            int x1 = graphPoints.get(i).x;
//            int y1 = graphPoints.get(i).y;
//            int x2 = graphPoints.get(i + 1).x;
//            int y2 = graphPoints.get(i + 1).y;
//            g.drawLine(x1, y1, x2, y2);
//        }
        // 그래프 그리기
        g.setColor(Color.GREEN);
       // g.setStroke(new BasicStroke(2f));
        for (int i = 0; i < inputSizes.size() - 1; i++) {
            int x1 = i * (width - padding * 2 - labelPadding) / (inputSizes.size() - 1) + padding + labelPadding;
            int y1 = height - padding - labelPadding - (int) ((durations.get(i) * 1.0 / getMaxDuration()) * (height - 2 * padding - labelPadding));
            int x2 = (i + 1) * (width - padding * 2 - labelPadding) / (inputSizes.size() - 1) + padding + labelPadding;
            int y2 = height - padding - labelPadding - (int) ((durations.get(i + 1) * 1.0 / getMaxDuration()) * (height - 2 * padding - labelPadding));
            g.drawLine(x1, y1, x2, y2);
        }

        // 점 그리기
        g.setColor(Color.GREEN);
        for (int i = 0; i < inputSizes.size(); i++) {
            int x = i * (width - padding * 2 - labelPadding) / (inputSizes.size() - 1) + padding + labelPadding;
            int y = height - padding - labelPadding - (int) ((durations.get(i) * 1.0 / getMaxDuration()) * (height - 2 * padding - labelPadding));
            g.fillOval(x - pointWidth / 2, y - pointWidth / 2, pointWidth, pointWidth);
        }
    }

    private long getMaxDuration() {
        long maxDuration = Long.MIN_VALUE;
        for (Long duration : durations) {
            maxDuration = Math.max(maxDuration, duration);
        }
        return maxDuration;
    }

    private String formatNumber(double number) {
        // 숫자 포맷을 설정하여 과학적 표기법 방지
        DecimalFormat format = new DecimalFormat("0.####"); // 소수점 이하 네 자리까지 표시
        return format.format(number);
    }
}
