package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Supplier;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.Dictionary;

public class ChainingHashTableEx {

    public static void main(String[] args) {
        // bst
        try {
            long time = testDic(bstConstructor());
            System.out.println("BST: " + time);
        } catch (FileNotFoundException e) {
            
        }
        
        //avl
        try {
            long time = testDic(avlConstructor());
            System.out.println("AVL: " + time);
        } catch (FileNotFoundException e) {
            
        }
        
        //mtf
        try {
            long time = testDic(mtfConstructor());
            System.out.println("MTF: " + time);
        } catch (FileNotFoundException e) {
            
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K,V> Supplier<Dictionary<K,V>> bstConstructor() {
        return () -> new BinarySearchTree();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <K,V> Supplier<Dictionary<K, V>> avlConstructor() {
        return () -> new AVLTree();
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <K, V> Supplier<Dictionary<K, V>> mtfConstructor() {
        return () -> new MoveToFrontList();
    }
    
    public static long testDic(Supplier<Dictionary<String, Integer>> sup) throws FileNotFoundException {
        ChainingHashTable<String, Integer> table = new ChainingHashTable<String, Integer>(sup);
        Scanner scan = new Scanner(new File("random_dic_large.txt"));
        long start = System.currentTimeMillis();
        while (scan.hasNext()) {
            table.insert(scan.next(), new Integer("1"));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
