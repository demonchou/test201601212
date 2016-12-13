package excise;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String s = "abcdaebijkd";
//List<> list = new ArrayList<>();
        ArrayList list = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c, i + 1) > -1) {
                Character ch = new Character(c);
                if (!list.contains(ch))
                    list.add(ch);
            }
        }
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + ",");
    }
}