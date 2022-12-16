import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> temp = new ArrayList<String>();
        String str = "    add these words to the shit";
        for (int i = 0; i < str.split(" ").length; i++) {
            temp.add(str.split(" ")[i]);
        }
        System.out.println(temp);
    }
}