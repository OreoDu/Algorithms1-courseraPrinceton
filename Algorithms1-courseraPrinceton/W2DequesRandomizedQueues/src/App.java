import java.util.Scanner;
import java.util.ArrayList;

public class App {

    public static void winner(String[] lines) {
        int r = -1;
        int w = -1;
        char rm,wm;

        for (int i = 0, l = lines.length; i < l; i++) {
            char flag = lines[i].charAt(0);
            char move = lines[i].charAt(1);

            if (flag == 'W') {
                if (move == 'U') w++;
                else w--;
            }else {
                if (move == 'U') r++;
                else r--;
            }
            if (w == -2 || w == 1 || r == -2 || r == 1) {
                System.out.println("Alice");
                return;
            }
        }
        System.out.println("Simon");
        if(r == 0) rm = 'U';
        else rm = 'D';
        if(w == 0) wm = 'U';
        else wm = 'D';

        String output = String.format("%s%s",rm, wm);
        System.out.println(output);
    }

    public static void main(String[] args) {
        // このコードは標準入力と標準出力を用いたサンプルコードです。
        // このコードは好きなように編集・削除してもらって構いません。
        // ---
        // This is a sample code to use stdin and stdout.
        // Edit and remove this code as you like.

        String[] lines = {"WU","RU","WD","WU","RD","WD","RU","RD","RU"};
        winner(lines);
    }
/*
    private static String[] getStdin() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }

 */
}