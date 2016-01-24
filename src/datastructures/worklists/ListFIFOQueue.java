package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private ListNode front;
    private ListNode current;
    private int size;
    
    private class ListNode {
    	public E work;
    	public ListNode next;
    	 
    	public ListNode(E work) {
    		this.work = work;
    	}
    }
    
    public ListFIFOQueue() {
    	clear();
    }

    @Override
    public void add(E work) {
        if(front == null) {
        	front = new ListNode(work);
        	current = front;
        } else {
        	current.next = new ListNode(work);
        	current = current.next;
        }
        
        size++;
    }

    @Override
    public E peek() {
        if(!hasWork()) {
        	throw new NoSuchElementException();
        }
        return front.work;
    }

    @Override
    public E next() {
    	if(!hasWork()) {
        	throw new NoSuchElementException();
        }
    	ListNode temp = front;
    	front = front.next;
    	size--;
    	return temp.work; 
    }

    @Override
    public int size() {
    	return size;
    }

    @Override
    public void clear() {
    	front = null;
    	current = front;
    	size = 0;
    }
}
