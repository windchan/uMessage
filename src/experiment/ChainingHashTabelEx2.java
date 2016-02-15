package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Supplier;

import cse332.interfaces.misc.Dictionary;

public class ChainingHashTabelEx2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ChainingHashTable<CircularArrayFIFOQueue<Character>, Integer> table = new ChainingHashTable<>(
                avlConstructor());
        try {
            long insertTime = testDicInsert(table);
            System.out.println("AVL insertion: " + insertTime);

            long findTime = testDicFind(table);
            System.out.println("AVL find: " + findTime);
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

    public static long testDicInsert(
            ChainingHashTable<CircularArrayFIFOQueue<Character>, Integer> table)
                    throws FileNotFoundException {

        Scanner scan = new Scanner(new File("random_dic_large.txt"));
        long start = System.currentTimeMillis();
        while (scan.hasNext()) {
            table.insert(makeStr(scan.next()), new Integer("1"));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long testDicFind(
            ChainingHashTable<CircularArrayFIFOQueue<Character>, Integer> table)
                    throws FileNotFoundException {
        Scanner scan = new Scanner(new File("archive.txt"));
        // Scanner scan = new Scanner(new File("spoken.corpus"));
        // Scanner scan = new Scanner(new File("one_word.txt"));
        long start = System.currentTimeMillis();
        while (scan.hasNext()) {
            table.find(makeStr(scan.next()));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static CircularArrayFIFOQueue<Character> makeStr(String next) {
        CircularArrayFIFOQueue<Character> str = new CircularArrayFIFOQueue<>(100);
        for (int i = 0; i < next.length(); i++) {
            str.add(next.charAt(i));
        }
        return str;
    }
}
