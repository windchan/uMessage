package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.interfaces.trie.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */


public class HashTrieMap<A, K extends BString<A>, V> extends TrieMap<A, K, V> {
	
   public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
      public HashTrieNode() {
         this(null);
      }
   
      public HashTrieNode(V value) {
         this.pointers = new HashMap<A, HashTrieNode>();
         this.value = value;
      }
   
      @Override
      public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
         return pointers.entrySet().iterator();
      }
   }

   public HashTrieMap(Class<K> KClass) {
      super(KClass);
      this.root = new HashTrieNode();
      this.size = 0;
      
   }

   @Override
    public V insert(K key, V value) {
	   	if (key == null || value == null){
	   		throw new IllegalArgumentException();
	   	}
	   	@SuppressWarnings("unchecked")
		HashTrieNode current = (HashTrieNode) this.root;
	   	for (A character : key){
	   		if (current.pointers.get(character) == null){
	   			current.pointers.put(character, new HashTrieNode());
	   		}
	   		current = current.pointers.get(character);
	   	}
	   	//Takes advantage of the fact that the value of HashTrieNode is initialized to null
	   	//If a new HashTrieNode is created, no previous mapping and its value will be null
	   	//If no new HashTrieNode created, previous mapping exists.
	   	V previous = current.value;
	   	current.value = value;
	   	if (previous == null){
	   		this.size++;
	   	}
	   	return previous;
   }

   @Override
   public V find(K key) {
	   if(key == null) {
		   throw new IllegalArgumentException();
	   }
	   @SuppressWarnings("unchecked")
	   HashTrieNode current = (HashTrieNode)this.root;
	   for(A letter: key) {
		   current = current.pointers.get(letter);
		   if(current == null) {
			   return null;
		   }
	   }
	   return current.value;
   }

   @Override
   public boolean findPrefix(K key) {
	   if(key == null) {
		   throw new IllegalArgumentException();
	   }
	   @SuppressWarnings("unchecked")
	   HashTrieNode current = (HashTrieNode)this.root;
	   for (A letter: key) {
		   current = current.pointers.get(letter);
		   if (current == null) {
			   return false;
		   }
	   }
	   return true;
   }
   
   //delete the node according to the structure.
   //if it has further node, only delete this one.
   @Override
   public void delete(K key) {
	   	boolean validKey = true;
	   	if(key == null) {
	   		throw new IllegalArgumentException();
	   	}
	  	@SuppressWarnings("unchecked")
	  	HashTrieNode current = (HashTrieNode)this.root;
   		if (key.isEmpty()){
   			if (current.value != null) {
   				current.value = null;
	   			size--;
   			}	   			
   		} else {
   			//Last node that is for certain necessary and can't be deleted
   			@SuppressWarnings("unchecked")
   			HashTrieNode previous = (HashTrieNode)this.root;
   			Iterator<A> iter = key.iterator();
   			/*Analogous to chopping off a branch of a tree. Initialized to the first character
   			 * of the key, since this is the branch to cut off if all nodes following are unnecessary.*/
   			A cutBranch = (A) iter.next();
   			for (A character : key){
   				/* Keeps track of the last node with more than one child or a valid value, since this
   				 * node is necessary and can't be deleted*/
   				if (current.pointers.get(character) != null) {
   					if (current.pointers.size() >= 2 || current.value != null){
	   					previous = current;
	   					// Also keeps track of the character in the key after reaching this node
	   					cutBranch = character;
	   				}
	   				current = current.pointers.get(character);
   				} else {
   					validKey = false;
   					break;
   				}
   			}
   			if (validKey) {
   				if (current.pointers.isEmpty()){
	   				// leaf node
	   				// Removes the key after the last necessary node. Everything after that is deleted.
	   				previous.pointers.remove(cutBranch);
	   				size--;
	   			} else {
	   				// Not a leaf node. Node is necessary and can't be removed.
	   				if (current.value != null) {
	   					current.value = null;
	   					size--;
	   				}	
	   			}	
   			}
   		}
   	}

   	@Override
   	public void clear() {
   		this.root = new HashTrieNode();
   		this.size = 0;
   	}
}


