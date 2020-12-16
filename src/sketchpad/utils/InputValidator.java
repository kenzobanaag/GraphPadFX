package sketchpad.utils;

import sketchpad.constants.Sizes;

public abstract class InputValidator {
    public static Double validateY(double n) {
        if(n < Sizes.Node.RADIUS || n > (Sizes.Canvas.HEIGHT - Sizes.Node.RADIUS)) {
            return 30.0;
        }
        return n;
    }

    public static Double validateX(double n) {
        if(n < Sizes.Node.RADIUS || n > (Sizes.Canvas.WIDTH - Sizes.Node.RADIUS)) {
            return 30.0;
        }
        return n;
    }
}
