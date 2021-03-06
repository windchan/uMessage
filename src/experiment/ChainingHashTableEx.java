package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Supplier;

import cse332.interfaces.misc.Dictionary;

public class ChainingHashTableEx {

    public static void main(String[] args) {
        // bst
        try {
            ChainingHashTable<String, Integer> table = new ChainingHashTable<String, Integer>(
                    bstConstructor());
            long insertTime = testDicInsert(table);
            System.out.println("BST insertion: " + insertTime);
            
            long findTime = testDicFind(table);
            System.out.println("BST find: " + findTime);
        } catch (FileNotFoundException e) {

        }

        // avl
        try {
            ChainingHashTable<String, Integer> table = new ChainingHashTable<String, Integer>(
                    avlConstructor());
            long insertTime = testDicInsert(table);
            System.out.println("AVL insertion: " + insertTime);
            
            long findTime = testDicFind(table);
            System.out.println("AVL find: " + findTime);
        } catch (FileNotFoundException e) {

        }

        // mtf
        try {
            ChainingHashTable<String, Integer> table = new ChainingHashTable<String, Integer>(
                    mtfConstructor());
            long insertTime = testDicInsert(table);
            System.out.println("MTF insertion: " + insertTime);
            
            long findTime = testDicFind(table);
            System.out.println("MTF find: " + findTime);
        } catch (FileNotFoundException e) {

        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K, V> Supplier<Dictionary<K, V>> bstConstructor() {
        return () -> new BinarySearchTree();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K, V> Supplier<Dictionary<K, V>> avlConstructor() {
        return () -> new AVLTree();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K, V> Supplier<Dictionary<K, V>> mtfConstructor() {
        return () -> new MoveToFrontList();
    }

    public static long testDicInsert(ChainingHashTable<String, Integer> table)
            throws FileNotFoundException {

        Scanner scan = new Scanner(new File("spoken.corpus"));
        long start = System.currentTimeMillis();
        while (scan.hasNext()) {
            table.insert(scan.next(), new Integer("1"));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long testDicFind(ChainingHashTable<String, Integer> table)
            throws FileNotFoundException {
//        Scanner scan = new Scanner(new File("archive.txt"));
//        Scanner scan = new Scanner(new File("random_dic_large.txt"));
        Scanner scan = new Scanner(new File("spoken.corpus"));
//        Scanner scan = new Scanner(new File("one_word.txt"));
        long start = System.currentTimeMillis();
        while (scan.hasNext()) {
            table.find(scan.next());
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
