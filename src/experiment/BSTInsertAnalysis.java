package experiment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import experiment.BinarySearchTree.*;
import experiment.AVLTree.*;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ListFIFOQueue;


public class BSTInsertAnalysis {
    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        AVLTree<String, Integer> avl = new AVLTree<String, Integer>();
        Scanner scan = new Scanner(new File("random_dic_large.txt"));
        while (scan.hasNext()) {
            avl.insert(scan.next(), 1);
        }
        PrintStream ps = new PrintStream("avl_level.txt");
        AvlPreOrder((AVLNode)avl.root, ps);
        long start = System.currentTimeMillis();
        
        //scan = new Scanner(new File("avl_level.txt"));    // best case
        scan = new Scanner(new File("sorted_dic_large.txt"));     // worst case
        while (scan.hasNext()) {
            bst.insert(scan.next(), 1);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
    
    public static void AvlPreOrder(AVLNode root, PrintStream ps) {
        if (root != null) {
            ps.println(root.key);
            AvlPreOrder(root.getAVLChildren(0), ps);
            AvlPreOrder(root.getAVLChildren(1), ps);
        }
    }
}
