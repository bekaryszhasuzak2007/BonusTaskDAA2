
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "ushers";

        String[] patterns = {"he", "she", "his", "hers"};

        // Test Aho-Corasick
        AhoCorasick ac = new AhoCorasick();

        for (String pattern : patterns) {
            ac.addPattern(pattern);
        }

        ac.build();

        System.out.println("=== Aho-Corasick ===");

        List<String> acResults = ac.search(text);

        for (String result : acResults) {
            System.out.println(result);
        }

        // Test KMP
        System.out.println("\n=== KMP ===");

        for (String pattern : patterns) {
            List<Integer> results = KMP.search(text, pattern);

            for (int index : results) {
                System.out.println(pattern + " at index " + index);
            }
        }

        System.out.println("\nTests completed!");
    }
}
