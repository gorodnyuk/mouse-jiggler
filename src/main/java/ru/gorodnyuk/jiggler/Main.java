package ru.gorodnyuk.jiggler;

import ru.gorodnyuk.jiggler.worker.Jiggler;
import ru.gorodnyuk.jiggler.worker.JigglerImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("Mouse jigger is working...");

        Jiggler jiggler = new JigglerImpl(5);

        jiggler.jiggle();
    }
}