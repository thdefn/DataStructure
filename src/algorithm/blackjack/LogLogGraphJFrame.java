package algorithm.blackjack;

import org.apache.commons.math.stat.regression.SimpleRegression;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogLogGraphJFrame extends JPanel {
    private List<Integer> inputSizes;
    private List<Long> durations;
    private double slope; // 기울기

    public LogLogGraphJFrame(List<Integer> inputSizes, List<Long> durations) {
        this.inputSizes = inputSizes;
        this.durations = durations;
        calculateSlope(); // 기울기 계산
    }

    private void calculateSlope() {
        // 로그-로그 변환을 수행하여 기울기 계산
        List<Double> logInputSizes = new ArrayList<>();
        List<Double> logDurations = new ArrayList<>();

        // 자연 로그로 변환
        for (int size : inputSizes) {
            logInputSizes.add(Math.log(size));
        }

        for (long duration : durations) {
            logDurations.add(Math.log(duration));
        }

        // 기울기 계산을 위한 변수 초기화
        double sumLogSize = 0;
        double sumLogDuration = 0;
        double sumLogSizeSquared = 0;
        double sumLogSizeDuration = 0;

        int n = inputSizes.size();

        // 각 합계 계산
        for (int i = 0; i < n; i++) {
            sumLogSize += logInputSizes.get(i);
            sumLogDuration += logDurations.get(i);
            sumLogSizeSquared += logInputSizes.get(i) * logInputSizes.get(i);
            sumLogSizeDuration += logInputSizes.get(i) * logDurations.get(i);
        }

        // 기울기 계산
        double numerator = n * sumLogSizeDuration - sumLogSize * sumLogDuration;
        double denominator = n * sumLogSizeSquared - sumLogSize * sumLogSize;

        slope = numerator / denominator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int margin = 50;
        int width = getWidth() - 2 * margin;
        int height = getHeight() - 2 * margin;

        // 그래프의 최대 입력 크기와 최대 실행 시간 찾기
        double maxLogSize = inputSizes.stream().mapToDouble(Math::log).max().orElse(0);
        double maxLogDuration = durations.stream().mapToDouble(Math::log).max().orElse(0);

        // 기울기로 선 그리기
        g2.setColor(Color.BLUE);
        int x1 = margin;
        int y1 = getHeight() - margin - (int) (slope * maxLogSize * height / maxLogDuration);
        int x2 = getWidth() - margin;
        int y2 = getHeight() - margin;
        g2.drawLine(x1, y1, x2, y2);

        // x축 레이블 그리기
        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) {
            int x = margin + i * width / 10;
            g2.drawLine(x, getHeight() - margin, x, getHeight() - margin + 5);
            g2.drawString(String.valueOf((int) Math.pow(Math.E, (double) i / 2)), x - 10, getHeight() - margin + 20);
        }

        // y축 레이블 그리기
        for (int i = 0; i <= 10; i++) {
            int y = getHeight() - margin - i * height / 10;
            g2.drawLine(margin - 5, y, margin, y);
            g2.drawString(String.valueOf((long) Math.pow(Math.E, (double) i / 2)), margin - 40, y + 5);
        }

        // 축 이름 그리기
        g2.drawString("log(Input Size)", getWidth() / 2 - 50, getHeight() - 10);
        g2.drawString("log(Execution Time)", 0, getHeight() / 2);

        // 그래프 제목 그리기
        g2.drawString("Log-Log Graph with Line", getWidth() / 2 - 60, margin - 10);
    }
    public static void main(String[] args) {
        Integer[] size = new Integer[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 256000, 512000, 1024000, 2000000, 4000000, 8000000, 16000000, 40000000};
       // , 2321694200704L
        List<Integer> inputSizes = Arrays.asList(size);
        Long[] du = new Long[]{8959279L, 16583566L, 32203883L, 63768316L, 127390483L, 255984316L, 545866187L, 2459886745L, 5007934312L, 15361155845L, 34687230066L, 77792243521L, 250345099770L, 693178485187L, 2321694200704L};
        List<Long> durations = Arrays.asList(du);

        // 로그-로그 스케일로 변환
        double[] logInputSizes = inputSizes.stream().mapToDouble(Math::log).toArray();
        double[] logDurations = durations.stream().mapToDouble(Math::log).toArray();

        // 기울기 계산을 위한 객체 생성
        SimpleRegression regression = new SimpleRegression();

        // 데이터 포인트 추가
        for (int i = 0; i < inputSizes.size(); i++) {
            regression.addData(logInputSizes[i], logDurations[i]);
        }

        // 기울기 출력
        double slope = regression.getSlope();
        System.out.println("Log-Log slope: " + slope);
    }

    public double getSlope() {
        return slope;
    }
}
