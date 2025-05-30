package ru.gorodnyuk.jiggler;

import ru.gorodnyuk.jiggler.utils.CLIKeys;
import ru.gorodnyuk.jiggler.worker.Jiggler;
import ru.gorodnyuk.jiggler.worker.JigglerImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("Mouse jiggler is working...");

        Integer delay = null;
        try {
            delay = getDelayFromArguments(args);
        } catch (NumberFormatException e) {
            //
        }

        Jiggler jiggler = new JigglerImpl();
        jiggler.jiggle(delay);
    }

    private static Integer getDelayFromArguments(String[] args) {
        for (String arg : args) {
            if (arg.contains(CLIKeys.DELAY_SHORT) || arg.contains(CLIKeys.DELAY_LONG)) {
                return parseArguments(arg);
            }
        }
        return null;
    }

    private static Integer parseArguments(String arg) {
        String[] splitted = arg.split("=");
        String delayValueStr = splitted[1];
        return Integer.valueOf(delayValueStr);
    }
}