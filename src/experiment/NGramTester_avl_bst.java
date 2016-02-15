package experiment;

import java.io.IOException;
import java.util.function.Supplier;

import cse332.interfaces.misc.BString;
import cse332.interfaces.misc.Dictionary;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import p2.wordsuggestor.WordSuggestor;
import cse332.datastructures.trees.BinarySearchTree;

public class NGramTester_avl_bst {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K,V> Supplier<Dictionary<K,V>> bstConstructor() {
        return () -> new BinarySearchTree();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K,V> Supplier<Dictionary<K, V>> avlConstructor() {
        return () -> new AVLTree();
    }
    
    public static <A extends Comparable<A>, K extends BString<A>, V> Supplier<Dictionary<K, V>> trieConstructor(Class<K> clz) {
        return () -> new HashTrieMap<A, K, V>(clz);
    }

    public static <K, V> Supplier<Dictionary<K, V>> hashtableConstructor(
            Supplier<Dictionary<K, V>> constructor) {
        return () -> new ChainingHashTable<K, V>(constructor);
    }

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor("sorted_dic_large.txt", 2, -1,
                    NGramTester_avl_bst.avlConstructor(),
                    NGramTester_avl_bst.avlConstructor());
//            suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
