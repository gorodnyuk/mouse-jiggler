package ru.gorodnyuk;


import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JigglerImpl implements Jiggler {

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
            robot.mouseMove(xRandom.nextInt(100), yRandom.nextInt(100));
        }
    }
}
