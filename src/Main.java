
public class Main {
    public static void main(String[] args) {
        AhoCorasick ac = new AhoCorasick();

        ac.addPattern("he");
        ac.addPattern("she");
        ac.addPattern("his");
        ac.addPattern("hers");

        ac.build();

        System.out.println("Text: ushers");
        System.out.println("Matches:");

        for (String match : ac.search("ushers")) {
            System.out.println(match);
        }
    }
}
