
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmTests {

    public static void main(String[] args) {
        test("aaaa", new String[]{"a", "aa", "aaa"});
        test("ushers", new String[]{"he", "she", "hers"});
        test("abcabc", new String[]{"abc", "abc"});
        test("abcdef", new String[]{"xyz"});
        test("abc", new String[]{"abcdef"});

        System.out.println("\nAll edge case tests passed!");
    }

    private static void test(String text, String[] patterns) {
        AhoCorasick ac = new AhoCorasick();

        for (String pattern : patterns) {
            ac.addPattern(pattern);
        }

        ac.build();

        List<String> ahoResults = ac.search(text);
        List<String> kmpResults = new ArrayList<>();

        for (String pattern : patterns) {
            for (int index : KMP.search(text, pattern)) {
                kmpResults.add(pattern + " at index " + index);
            }
        }

        Collections.sort(ahoResults);
        Collections.sort(kmpResults);

        if (!ahoResults.equals(kmpResults)) {
            throw new RuntimeException(
                    "Test failed! Text: " + text
            );
        }

        System.out.println("PASS: " + text
                + " | Matches: " + ahoResults.size());
    }
}
