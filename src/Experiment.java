
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiment {

    private static final int[] PATTERN_COUNTS = {10, 100, 1000};
    private static final int[] TEXT_SIZES = {1000, 10000, 100000};
    private static final int RUNS = 5;

    public static void run() throws Exception {
        File folder = new File("results");
        folder.mkdirs();

        try (PrintWriter writer = new PrintWriter(
                new File(folder, "results.csv"))) {

            writer.println("patterns,textLength,ahoAverageNs,kmpAverageNs,ahoMatches,kmpMatches");

            for (int patternCount : PATTERN_COUNTS) {
                for (int textSize : TEXT_SIZES) {

                    Random random = new Random(42 + patternCount + textSize);

                    String text = generateText(textSize, random);
                    List<String> patterns =
                            generatePatterns(patternCount, random);

                    // Warm-up
                    runAho(text, patterns);
                    runKMP(text, patterns);

                    long ahoTotal = 0;
                    long kmpTotal = 0;

                    int ahoMatches = 0;
                    int kmpMatches = 0;

                    for (int i = 0; i < RUNS; i++) {
                        long start = System.nanoTime();
                        ahoMatches = runAho(text, patterns);
                        ahoTotal += System.nanoTime() - start;

                        start = System.nanoTime();
                        kmpMatches = runKMP(text, patterns);
                        kmpTotal += System.nanoTime() - start;
                    }

                    if (ahoMatches != kmpMatches) {
                        throw new RuntimeException("Results do not match!");
                    }

                    long ahoAverage = ahoTotal / RUNS;
                    long kmpAverage = kmpTotal / RUNS;

                    writer.println(patternCount + "," + textSize + ","
                            + ahoAverage + "," + kmpAverage + ","
                            + ahoMatches + "," + kmpMatches);

                    System.out.println(
                            "Patterns: " + patternCount
                                    + ", Text: " + textSize
                                    + ", Aho: " + ahoAverage
                                    + " ns, KMP: " + kmpAverage + " ns");
                }
            }
        }

        System.out.println("\nExperiments completed!");
        System.out.println("Results saved to results/results.csv");
    }

    private static int runAho(String text, List<String> patterns) {
        AhoCorasick ac = new AhoCorasick();

        for (String pattern : patterns) {
            ac.addPattern(pattern);
        }

        ac.build();

        return ac.search(text).size();
    }

    private static int runKMP(String text, List<String> patterns) {
        int count = 0;

        for (String pattern : patterns) {
            count += KMP.search(text, pattern).size();
        }

        return count;
    }

    private static String generateText(int size, Random random) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < size; i++) {
            text.append((char) ('a' + random.nextInt(4)));
        }

        return text.toString();
    }

    private static List<String> generatePatterns(
            int count, Random random) {

        List<String> patterns = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            StringBuilder pattern = new StringBuilder();

            for (int j = 0; j < 8; j++) {
                pattern.append((char) ('a' + random.nextInt(4)));
            }

            patterns.add(pattern.toString());
        }

        return patterns;
    }
}
