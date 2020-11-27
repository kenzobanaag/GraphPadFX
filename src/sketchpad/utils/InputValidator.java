package sketchpad.utils;

import sketchpad.constants.Sizes;

public abstract class InputValidator {
    public static Double validateAdd(double n) {
        if(n < 0 || n > Sizes.Console.HEIGHT) {
            return 30.0;
        }
        return n;
    }
}
