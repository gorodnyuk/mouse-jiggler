package ru.gorodnyuk;


import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JigglerImpl implements Jiggler {

    private static final int ORIGIN = 1;
    private static final int INCLUSIVE_BOUND = 10 + 1;

    private final Robot robot;
    private final Random xRandom;
    private final Random yRandom;

    public JigglerImpl() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        xRandom = new Random();
        yRandom = new Random();
    }

    @Override
    public void jiggle(int delay) {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();
            double mouseXCoordinate = point.getX();
            double mouseYCoordinate = point.getY();

            System.out.println("x = " + mouseXCoordinate);
            System.out.println("y = " + mouseYCoordinate);
            System.out.println("new x = " + (mouseXCoordinate + xRandom.nextInt(ORIGIN, INCLUSIVE_BOUND)));
            System.out.println("new y = " + (mouseYCoordinate + yRandom.nextInt(ORIGIN, INCLUSIVE_BOUND)));

            robot.mouseMove(
                    (int) mouseXCoordinate + xRandom.nextInt(ORIGIN, INCLUSIVE_BOUND),
                    (int) mouseYCoordinate + yRandom.nextInt(ORIGIN, INCLUSIVE_BOUND)
            );
        }
    }
}
