package 시간복잡도.풀이;

import java.util.Scanner;

public class 개미2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String maxPoint = sc.nextLine();
        int maxWidth = Integer.parseInt(maxPoint.split(" ")[0]);
        int maxHeight = Integer.parseInt(maxPoint.split(" ")[1]);

        String startPoint = sc.nextLine();
        int x = Integer.parseInt(startPoint.split(" ")[0]);
        int y = Integer.parseInt(startPoint.split(" ")[1]);
        int times = Integer.parseInt(sc.next());
        int timesX = times % (maxWidth*2);
        int timesY = times % (maxHeight*2);
        int deltaX = 1;
        int deltaY = 1;

        for (int i = 0; i < timesX; i++) {
            if (x == maxWidth || x == 0) deltaX = -deltaX;
            x += deltaX;
        }

        for (int i = 0; i < timesY; i++) {
            if (y == maxHeight || y == 0) deltaY = -deltaY;
            y += deltaY;
        }

        System.out.printf("%d %d", x, y);
    }
}
