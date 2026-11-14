
import java.util.*;

public class AhoCorasick {

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail;
        List<Integer> output = new ArrayList<>();
    }

    private final Node root = new Node();
    private final List<String> patterns = new ArrayList<>();

    // Add a pattern to the Trie
    public void addPattern(String pattern) {
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }

        int id = patterns.size();
        patterns.add(pattern);

        Node current = root;

        for (char c : pattern.toCharArray()) {
            current.children.putIfAbsent(c, new Node());
            current = current.children.get(c);
        }

        current.output.add(id);
    }

    // Build failure links using BFS
    public void build() {
        Queue<Node> queue = new ArrayDeque<>();
        root.fail = root;

        for (Node child : root.children.values()) {
            child.fail = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char c = entry.getKey();
                Node child = entry.getValue();

                Node failure = current.fail;

                while (failure != root && !failure.children.containsKey(c)) {
                    failure = failure.fail;
                }

                if (failure.children.containsKey(c)) {
                    child.fail = failure.children.get(c);
                } else {
                    child.fail = root;
                }

                child.output.addAll(child.fail.output);
                queue.add(child);
            }
        }
    }

    // Find all occurrences of all patterns
    public List<String> search(String text) {
        List<String> matches = new ArrayList<>();
        Node current = root;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (current != root && !current.children.containsKey(c)) {
                current = current.fail;
            }

            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            }

            for (int id : current.output) {
                int start = i - patterns.get(id).length() + 1;
                matches.add(patterns.get(id) + " at index " + start);
            }
        }

        return matches;
    }
}
