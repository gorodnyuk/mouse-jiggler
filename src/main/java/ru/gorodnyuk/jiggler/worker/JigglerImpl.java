package ru.gorodnyuk.jiggler.worker;


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
    private static final int SHUFFLE_TIMEOUT = 100;

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
            shuffle(pointerInfo.getLocation());
        }
    }

    private void shuffle(Point mouseCoordinates) {
        double mouseXCoordinate = mouseCoordinates.getX();
        double mouseYCoordinate = mouseCoordinates.getY();
        double newMouseXCoordinate = mouseXCoordinate + xRandom.nextInt(ORIGIN, INCLUSIVE_BOUND);
        double newMouseYCoordinate = mouseYCoordinate + yRandom.nextInt(ORIGIN, INCLUSIVE_BOUND);

        robot.mouseMove((int) newMouseXCoordinate, (int) newMouseYCoordinate);
        try {
            TimeUnit.MILLISECONDS.sleep(SHUFFLE_TIMEOUT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        robot.mouseMove((int) mouseXCoordinate, (int) mouseYCoordinate); // возврат на исходную позицию
    }
}
