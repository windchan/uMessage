import java.util.Arrays;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.Dictionary;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import datastructures.dictionaries.HashTrieMap;
import p2.wordsuggestor.NGramToNextChoicesMap;

public class NGramToNextChoicesMapPeronsalTest {
    private static Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> newOuter =
            () -> new HashTrieMap<String, NGram, Dictionary<AlphabeticString, Integer>>(NGram.class);

    private static Supplier<Dictionary<AlphabeticString, Integer>> newInner = 
            () -> new HashTrieMap<Character, AlphabeticString, Integer>(AlphabeticString.class);
    
    public static void main(String[] args) {
        System.out.println(testOneWordPerNGram());
        System.out.println(testMultipleWordsPerNGram());
    }
    
    public static int testOneWordPerNGram() {
        NGramToNextChoicesMap map = init();
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"a", "s", "d"})
        };
        
        String[] words = new String[]{"bop", "fum", "f"};
        for (int i = 0; i < ngrams.length; i++) {
            map.seenWordAfterNGram(ngrams[i], words[i]);
        }
        for (int i = 0; i < ngrams.length; i++) {
            Item<String, Integer>[] items = map.getCountsAfter(ngrams[i]);
            if (items.length != 1) return 0;
            Item<String, Integer> item = items[0];
            if (!item.key.equals(words[i])) return 0;
            if (!item.value.equals(1)) return 0;
        }
        
        return 1;
    }
    
    protected static NGramToNextChoicesMap init() {
        return new NGramToNextChoicesMap(newOuter, newInner);
    }
    
    public static int testMultipleWordsPerNGram() {
        NGramToNextChoicesMap map = init();
        NGram[] ngrams = new NGram[]{
                new NGram(new String[]{"foo", "bar", "baz"}),
                new NGram(new String[]{"fee", "fi", "fo"}),
                new NGram(new String[]{"four", "score", "and"}),
                new NGram(new String[]{"3", "2", "2"}),
                new NGram(new String[]{"a", "s", "d"})
        };
        
        String[][] words = new String[][] {
            new String[]{"bip", "bop", "bzp"},
            new String[]{"fum", "giants"},
            new String[]{"ago", "seven", "years"},
            new String[]{"new", "thrown", "uuu", "zzz"},
            new String[]{"do", "for", "while"}
        };
        
        for (int i = 0; i < ngrams.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                map.seenWordAfterNGram(ngrams[i], words[i][j]);
            }
            
        }
        for (int i = 0; i < ngrams.length; i++) {
            Item<String, Integer>[] items = map.getCountsAfter(ngrams[i]);
            String[] answer = words[i];
            if (items.length != answer.length) return 0;
            String[] itemsWithoutCounts = new String[items.length];
            for (int j = 0; j < answer.length; j++) {
                if (items[j].value != 1) return 0;
                itemsWithoutCounts[j] = items[j].key;
            }
            Arrays.sort(itemsWithoutCounts);
            for (int j = 0; j < answer.length; j++) {
                if (!itemsWithoutCounts[j].equals(answer[j])) return 0;
            }
        }
        
        return 1;
    }
}
