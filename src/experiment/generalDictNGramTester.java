package experiment;

import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

import cse332.interfaces.misc.BString;
import cse332.interfaces.misc.Dictionary;
import cse332.misc.WordReader;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import datastructures.dictionaries.HashTrieMap;
import p2.wordsuggestor.WordSuggestor;

public class generalDictNGramTester {
    
    public static final String FILE_NAME = "random_dic_large.txt";

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K, V> Supplier<Dictionary<K, V>> bstConstructor() {
        return () -> new BinarySearchTree();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K, V> Supplier<Dictionary<K, V>> avlConstructor() {
        return () -> new AVLTree();
    }

    public static <A extends Comparable<A>, K extends BString<A>, V> Supplier<Dictionary<K, V>> trieConstructor(
            Class<K> clz) {
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
        preload();

        trietrie();
        triehash();
        trieavl();
        triebst();
        triemtf();
        
        mtftrie();
        mtfhash();
        mtfavl();
        mtfbst();
        mtfmtf();
        
        hashtrie();
        hashhash();
        hashavl();
        hashbst();
        hashmtf();
        
        bsttrie();
        bsthash();
        bstavl();
        bstbst();
        bstmtf();
        
        avltrie();
        avlhash();
        avlavl();
        avlbst();
        avlmtf();
   }

    
//    public static void preload() {
//        String str;
//        try {
//            WordReader wr = new WordReader(new FileReader(FILE_NAME));
//            while (wr.hasNext()) {
//                str = wr.next();
//            }
//            wr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void preload() {
        try {
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    mtfListConstructor());
            System.out.println("Preload finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void trietrie() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    trieConstructor(AlphabeticString.class));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("trietrie:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void triehash() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    hashtableConstructor(mtfListConstructor()));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("triehash:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    public static void triemtf() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    mtfListConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("triemtf:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void trieavl() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    avlConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("trieavl:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void triebst() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    trieConstructor(NGram.class),
                    bstConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("triebst:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mtftrie() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    mtfListConstructor(),
                    trieConstructor(AlphabeticString.class));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("mtftrie:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mtfmtf() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    mtfListConstructor(),
                    mtfListConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("mtfmtf:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mtfavl() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    mtfListConstructor(),
                    avlConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("mtfavl:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mtfbst() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    mtfListConstructor(),
                    bstConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("mtfbst:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mtfhash() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    mtfListConstructor(),
                    hashtableConstructor(mtfListConstructor()));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("mtfhash:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void hashhash() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    hashtableConstructor(mtfListConstructor()),
                    hashtableConstructor(mtfListConstructor()));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("hashhash:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void hashtrie() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    hashtableConstructor(mtfListConstructor()),
                    trieConstructor(AlphabeticString.class));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("hashtrie:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void hashavl() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    hashtableConstructor(mtfListConstructor()),
                    avlConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("hashavl:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void hashbst() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    hashtableConstructor(mtfListConstructor()),
                    bstConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("hashbst:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void hashmtf() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    hashtableConstructor(mtfListConstructor()),
                    mtfListConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("hashmtf:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bstbst() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    bstConstructor(),
                    bstConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("bstbst:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bsttrie() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    bstConstructor(),
                    trieConstructor(AlphabeticString.class));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("bsttrie:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bstavl() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    bstConstructor(),
                    avlConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("bstavl:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bstmtf() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    bstConstructor(),
                    mtfListConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("bstmtf:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bsthash() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    bstConstructor(),
                    hashtableConstructor(mtfListConstructor()));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("bsthash:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void avltrie() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    avlConstructor(),
                    trieConstructor(AlphabeticString.class));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("avltrie:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void avlavl() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    avlConstructor(),
                    avlConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("avlavl:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void avlhash() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    avlConstructor(),
                    hashtableConstructor(mtfListConstructor()));
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("avlhash:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void avlbst() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    avlConstructor(),
                    bstConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("avlbst:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void avlmtf() {
        try {
            long startTime = System.currentTimeMillis();
            WordSuggestor suggestions = new WordSuggestor(FILE_NAME, 3, -1,
                    avlConstructor(),
                    mtfListConstructor());
            // suggestions.toString();
            long endTime = System.currentTimeMillis();
            System.out.println("avlmtf:"+(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
