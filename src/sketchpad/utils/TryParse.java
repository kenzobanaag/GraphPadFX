package sketchpad.utils;

public abstract class TryParse {

    public static Double tryParse(String input) {
        double output = 30.0;
        try {
            output = Double.parseDouble(input);
        }
        catch (NumberFormatException err) {
            return output;
        }
        return output;
    }

    public static Integer tryParseInt(String input) {
        int output = -1;
        try {
            output = Integer.parseInt(input);
        }
        catch (NumberFormatException err) {
            return output;
        }
        return output;
    }
}
