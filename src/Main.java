import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> temp = new ArrayList<String>();
        String str = "push constant 5";
        for (int i = 0; i < str.split(" ").length; i++) {
            temp.add(str.split(" ")[i]);
        }
        System.out.println(temp);
    }
}