import java.util.HashMap;

public class CommandTable {
    public static HashMap<String, String> comtab;

    // sets up the predefined symbols and values into the table
    public CommandTable() {

        comtab = new HashMap<String, String>();

        comtab.put("add", "C_ARITHMETIC");
        comtab.put("sub", "C_ARITHMETIC");
        comtab.put("neg", "C_ARITHMETIC");
        comtab.put("eq", "C_ARITHMETIC");
        comtab.put("gt", "C_ARITHMETIC");
        comtab.put("lt", "C_ARITHMETIC");
        comtab.put("and", "C_ARITHMETIC");
        comtab.put("or", "C_ARITHMETIC");
        comtab.put("not", "C_ARITHMETIC");
        comtab.put("push", "C_PUSH");
        comtab.put("pop", "C_POP");
        comtab.put("label", "C_LABEL");
        comtab.put("goto", "C_GOTO");
        comtab.put("if-goto", "C_IF");
        comtab.put("function", "C_FUNCTION");
        comtab.put("call", "C_CALL");
        comtab.put("return", "C_RETURN");

    }

    //addds entry to the table
//    //public void addEntry(String symbol, String address) {
//        if (!this.contains(symbol)) {
//            System.out.println("addEntry recieves the symbol:" +symbol);
//            System.out.println("And adds it to the table with address:" +address );
//            comtab.put(symbol, address);
//        }
//    }

    //checks if symbol exsits and returns true. False if it does not exsis.
    public boolean contains(String symbol) {
        return comtab.containsKey(symbol);
    }

    //returns the integer value (address) of the symbol from the table
    public String getComType(String symbol) {
        return comtab.get(symbol);
    }
}