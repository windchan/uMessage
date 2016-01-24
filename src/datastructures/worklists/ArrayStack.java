package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */

public class ArrayStack<E> extends LIFOWorkList<E> {
	private E[] arr;
	private static final int DEFAULT_SIZE = 10;
	private int currentIndex;
	
    public ArrayStack() {
    	clear();
    }

 
    /**
     * Adds work to the worklist.  This method should conform to any additional contracts that
     * the particular type of worklist has.
     * 
     * @param work the work to add to the worklist
     */
    @Override
    public void add(E work) {
    	if (currentIndex >= arr.length) {
    		@SuppressWarnings("unchecked")
    		E[] largerArr = (E[])new Object[arr.length * 2];
    		for(int i = 0; i < arr.length; i++) {
    			largerArr[i] = arr[i];
    		}
    		arr = largerArr;
    	}
    	arr[currentIndex] = work;
    	currentIndex++;
    }

    /**
     * Returns a view to the next element of the worklist.
     * 
     * @precondition hasWork() is true
     * @postcondition return(peek()) is return(next())
     * @postcondition the structure of this worklist remains unchanged.
     * @throws NoSuchElementException if hasWork() is false 
     * @return the next element in this worklist 
     */
    @Override
    public E peek() {
    	if(!hasWork()) {
    		throw new NoSuchElementException();
    	}
    	return arr[currentIndex - 1];
    }

    /**
     * Returns and removes the next element of the worklist 
     * 
     * @precondition hasWork() is true
     * @postcondition return(next()) + after(next()) == before(next())
     * @postcondition after(size()) + 1 == before(size()) 
     * @throws NoSuchElementException if hasWork() is false 
     * @return the next element in this worklist
     */
    @Override
    public E next() {
    	if(!hasWork()) {
    		throw new NoSuchElementException();
    	}
    	E temp = arr[currentIndex - 1];
    	arr[currentIndex - 1] = null;
    	currentIndex--;
    	return temp;
    }

    /**
     * Returns the number of elements of work remaining in this worklist
     * 
     * @return the size of this worklist
     */
    @Override
    public int size() {
    	return currentIndex;
    }

    /**
     * Resets this worklist to the same state it was in right after construction. 
     */
    @Override
    public void clear() {
    	@SuppressWarnings("unchecked")
    	E[] newArr =(E[])new Object[DEFAULT_SIZE];
    	arr = newArr;
        currentIndex = 0;
    }
}
