package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Locale;

public class InputParsing {

    private String originalInput;

    public InputParsing(String input) {
        originalInput = input.toLowerCase(Locale.ROOT);
    }


    public char playerSelection() {
        return originalInput.charAt(0); // grab the first input
    }

    // returns the seed as a long parsed from the input string
    public long seedNumber() {
        int endIndex = originalInput.indexOf('s');
        if (endIndex < 0 || endIndex > originalInput.length()) {
            throw new IllegalArgumentException("Must add s after inputing seed");
        }
        // if seed.length == 1 parse long for 1 char lenght string
        else if ( endIndex == 2) {
            String substr = originalInput.substring(1, endIndex);
            return Long.parseLong(substr);
        }
        return Long.parseLong(originalInput.substring(1, endIndex - 1));
    }

    // returns substring of player
    public String playerCommands() {
        int moveStartIndex = 0;

        if (playerSelection() == 'n') {
            moveStartIndex = originalInput.indexOf('s') + 1; // start index = first position after seed inputed following 's'
        }
        else if (playerSelection() == 'l' && originalInput.length() > 1) {
            moveStartIndex = moveStartIndex + 1;
        }

        return originalInput.substring(moveStartIndex);
    }




}