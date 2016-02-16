package p2.clients;

import java.io.IOException;
import java.util.function.Supplier;

import cse332.interfaces.misc.BString;
import cse332.interfaces.misc.Dictionary;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import experiment.MoveToFrontList;
import p2.wordsuggestor.WordSuggestor;

public class NGramTester {
    public static <A extends Comparable<A>, K extends BString<A>, V> Supplier<Dictionary<K, V>> trieConstructor(Class<K> clz) {
        return () -> new HashTrieMap<A, K, V>(clz);
    }

    public static <K, V> Supplier<Dictionary<K, V>> hashtableConstructor(
            Supplier<Dictionary<K, V>> constructor) {
        return () -> new ChainingHashTable<K, V>(constructor);
    }
    
    public static <K, V> Supplier<Dictionary<K, V>> mtfListConstructor() {
        return () -> new MoveToFrontList<K, V>();
    }
    
    public static void main(String[] args) {
        try {
            WordSuggestor suggestions = new WordSuggestor("spoken.corpus", 2, -1,
                    NGramTester.trieConstructor(NGram.class),
                    NGramTester.trieConstructor(AlphabeticString.class));
            System.out.println(suggestions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
