package machine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidInput {
    public static MachineState validMachineState(String input){
        switch (input.trim().toUpperCase()) {
            case "BUY":
                return MachineState.BUY;
            case "FILL":
                return MachineState.FILL;
            case "TAKE":
                return MachineState.TAKE;
            case "REMAINING":
                return MachineState.REMAINING;
            case "EXIT":
                return MachineState.EXIT;
            default:
                return MachineState.INVALID;
        }
    }

    public static boolean isNotBack(String input) {
        return !input.trim().equalsIgnoreCase("back");
    }

    public static Coffee validCoffee(String input) {
        switch (input.trim()) {
            case "1":
                return Coffee.ESPRESSO;
            case "2":
                return Coffee.LATTE;
            case "3":
                return Coffee.CAPPUCCINO;
            default:
                return null;
        }
    }

    public static int validNumber(String input) {
        if (isIntNumber(input)) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }
    }

    public static boolean isIntNumber(String input) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() && input.length() < 9;
    }
}
