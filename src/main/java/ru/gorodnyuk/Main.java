package ru.gorodnyuk;

public class Main {

    public static void main(String[] args) {
        System.out.println("Mouse jigger is working...");

        Jiggler jiggler = new JigglerImpl();

        jiggler.jiggle(5);
    }
}