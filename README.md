# Aho-Corasick Algorithm

## 1. Project Description

This project implements the Aho-Corasick string matching algorithm in Java.

The algorithm searches for multiple patterns in one text at the same time.

The project also compares Aho-Corasick with the KMP algorithm.

## 2. Implemented Algorithms

### Aho-Corasick

The implementation uses:

- Trie for storing multiple patterns.
- Failure links for moving between states.
- Output lists for storing matched patterns.
- Breadth-First Search (BFS) for building failure links.

### KMP

KMP searches for one pattern at a time.

It uses an LPS array to avoid unnecessary character comparisons.

## 3. Experimental Setup

Experiments were performed using:

- Java 17
- System.nanoTime()
- 5 runs for each test case
- Average execution time

Number of patterns: 10, 100, 1000.

Text lengths: 1,000, 10,000, 100,000 characters.

## 4. Experimental Results

Average execution time (milliseconds):

| Patterns | Text Length | Aho-Corasick | KMP |
|---:|---:|---:|---:|
| 10 | 1,000 | 1.09 | 2.56 |
| 10 | 10,000 | 2.39 | 3.36 |
| 10 | 100,000 | 7.65 | 12.45 |
| 100 | 1,000 | 1.30 | 1.37 |
| 100 | 10,000 | 1.24 | 12.25 |
| 100 | 100,000 | 5.09 | 117.40 |
| 1,000 | 1,000 | 5.07 | 12.92 |
| 1,000 | 10,000 | 2.83 | 117.57 |
| 1,000 | 100,000 | 7.61 | 1133.49 |

Both algorithms returned the same number of matches in all experiments.

## 5. Complexity Analysis

Let:

- S = total length of all patterns.
- N = length of the text.
- Z = total number of matches.

### Aho-Corasick

- Trie construction: O(S) expected time.
- Failure link construction: depends on failure-link traversals and output-list copying in this implementation.
- Searching: O(N + Z) expected time.
- Space: O(S + Zmax), where Zmax represents the total stored output entries across trie states.

The search algorithm processes the text in one pass.

### KMP

For one pattern of length M:

- LPS construction: O(M).
- Searching: O(N).
- Total time: O(N + M).

For P patterns:

O(P × N + S)

KMP searches for every pattern separately.

## 6. Comparison

Aho-Corasick is suitable for searching many patterns because it processes the text in one pass.

KMP is simpler and works well when searching for one pattern.

Aho-Corasick requires more memory because it stores a trie and failure links.

KMP uses less additional memory but becomes slower when the number of patterns increases.

## 7. Conclusion

The experiments show that Aho-Corasick performs better when searching for many patterns.

In the largest experiment, with 1,000 patterns and 100,000 text characters, Aho-Corasick was approximately 149 times faster than KMP.

Therefore, Aho-Corasick is a good choice for multiple-pattern searching.

## 8. How to Run

1. Open the project in IntelliJ IDEA.
2. Use JDK 17.
3. Run Main.java.
4. Experimental results will be saved in results/results.csv.
