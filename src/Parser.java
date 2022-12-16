import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    private Scanner scanner;
    private ArrayList<String> currIns = new ArrayList<String>(); // an array list that will hold the current instruction as separate strings to be parsed
    private CommandTable comTab = new CommandTable();

    //sets the scanner to read from the input file to be translated to a hack program
    public Parser(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    //returns true if the file has another line to read
    public boolean hasMoreLine() {
        return scanner.hasNextLine();
    }

    public void advance() {
        if (hasMoreLine()) {
            String str = scanner.nextLine();
            if (str.equals("\n") || str.equals(" ")) {
                advance();
            } else if (str.startsWith("//")) {
                advance();
            } else if (str.contains("//")) {
                str = str.split("//")[0];
                str = str.trim();
                for (int i = 0; i < str.split(" ").length; i++) {
                    currIns.add(str.split(" ")[i]);
                }
            }
        }
    }

    public String commandType() {
        if (currIns.get(0) != null) {
            return comTab.getComType(currIns.get(0));
        }
        return "you made a mistake biyache. where is my string"; //do we return null if the command is "empty"?
    }

    public String arg1(ArrayList<String> curr) {
        if (comTab.getComType(curr.get(0)).equals("C_ARITHMETIC")) {
            return curr.get(0);
        } else {
            return curr.get(1);
        }
    }

    public int arg2(ArrayList<String> curr) {
        return Integer.parseInt(curr.get(2));
    }

}
