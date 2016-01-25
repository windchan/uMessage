import datastructures.worklists.CircularArrayFIFOQueue;
public class circularArrayPersonalTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CircularArrayFIFOQueue<String> l1 = new CircularArrayFIFOQueue<>(10);
        CircularArrayFIFOQueue<String> l2 = new CircularArrayFIFOQueue<>(10);
        l1.add("a");
        l1.add("b");
        l2.add("a");
        l2.add("b");
        l2.add("c");
        System.out.println(l1.compareTo(l2));
        System.out.println("ab".compareTo("abc"));
    }

}
