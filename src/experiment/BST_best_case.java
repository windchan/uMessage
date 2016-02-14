package experiment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import experiment.BinarySearchTree.*;
import experiment.AVLTree.*;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ListFIFOQueue;


public class BST_best_case {
    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        AVLTree<String, Integer> avl = new AVLTree<String, Integer>();
        Scanner scan = new Scanner(new File("random_dic_large.txt"));
        while (scan.hasNext()) {
            avl.insert(scan.next(), 1);
        }
        
        PrintWriter pw = new PrintWriter(new File("avl_level.txt"));
        AvlLevel((AVLNode)avl.root, pw);
        
        long start = System.currentTimeMillis();

        scan = new Scanner(new File("avl_level.txt"));
        while (scan.hasNext()) {
            bst.insert(scan.next(), 1);
        }
        
        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
    
    public static void AvlLevel(AVLNode root, PrintWriter pw) {
        if (root != null) {
            WorkList<AVLNode> q = new ListFIFOQueue<AVLNode>();
            q.add(root);
            while (q.size() > 0) {
                if (q.peek().getAVLChildren(0) != null) {
                    q.add(q.peek().getAVLChildren(0));
                }
                if (q.peek().getAVLChildren(1) != null) {
                    q.add(q.peek().getAVLChildren(1));
                }
                pw.write(q.next().key.toString()+"\n");
            }
        }
    }
}
