package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;


import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.BString;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */

public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Dictionary<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            Supplier<Dictionary<K, V>> newChain = () -> new MoveToFrontList<K, V>();
            this.pointers = new ChainingHashTable(newChain);
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
        this.size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V insert(K key, V value) {
    	if(key == null || value == null)
    		throw new IllegalArgumentException();
        HashTrieNode curr = (HashTrieNode) this.root;
        for (A ch : key) {
        	if (!(curr.pointers.containsKey(ch))) {
        		curr.pointers.put(ch, new HashTrieNode());
        	}
        	curr = curr.pointers.get(ch);
        }
    	V previous = curr.value;
    	curr.value = value;
    	if (previous == null) {
    	    this.size++;
    	}
    	return previous;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V find(K key) {
    	if(key == null)
    		throw new IllegalArgumentException();
        HashTrieNode curr = (HashTrieNode)this.root;
        for (A ch : key) {
        	if (curr.pointers.containsKey(ch)) {
        		curr = curr.pointers.get(ch);
        	} else {
        		return null;
        	}
        }
        return curr.value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean findPrefix(K key) {
    	if(key == null)
    		throw new IllegalArgumentException();
    	HashTrieNode curr = (HashTrieNode)this.root;
        for (A ch : key) {
        	if (curr.pointers.containsKey(ch)) {
        		curr = curr.pointers.get(ch);
        	} else {
        		return false;
        	}
        }
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void delete(K key) {
    	if (key == null)
    		throw new IllegalArgumentException();
    	this.root = delete((HashTrieNode)this.root, key.iterator());
    	if (this.root == null)
    		this.root = new HashTrieNode();
    }
    
    private HashTrieNode delete(HashTrieNode root, Iterator<A> ite) {
    	if (!(ite.hasNext())){
//    		if (root.pointers.containsKey(null)) {
//    			root.pointers.remove(null);
//    		}
    		if (root.pointers.isEmpty()){
    			size--;
    			return null;
    		} else {
    			if (root.value != null) {
    				size--;
    				root.value = null;
    			}
        		return root;
    		}
    	} else {
    		A nextChar = ite.next();
    		if (!root.pointers.containsKey(nextChar))
    			return root;
    		HashTrieNode temp = delete(root.pointers.get(nextChar), ite);
    		if (temp == null)
				root.pointers.remove(nextChar);
    		if (root.pointers.size() > 0) {
    			return root;
    		} else {
    			if(root.value != null)
    				return root;
    			else 
    				return null;
    		}
    	}
    }

    @Override
    public void clear() {
        this.root = new HashTrieNode();;
        this.size = 0;
    }
}
