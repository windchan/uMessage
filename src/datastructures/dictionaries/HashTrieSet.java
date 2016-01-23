package datastructures.dictionaries;

import cse332.interfaces.trie.BString;
import cse332.interfaces.trie.TrieSet;
import datastructures.HashTrieMap;

public class HashTrieSet<A, E extends BString<A>> extends TrieSet<A, E> {    
    /* Note: You should not be adding any methods to this class...you only need to implement
     *       the constructor!  */

    public HashTrieSet(Class<E> Type) {
        // Call the correct super constructor...that's it!
    	super(new HashTrieMap<A, E, Boolean>(Type));
    }
}
