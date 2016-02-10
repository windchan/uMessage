package datastructures.dictionaries;

import java.util.Iterator;
import java.util.function.Supplier;

import cse332.datastructures.containers.*;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You must implement a generic chaining hashtable. You may not
 *    restrict the size of the input domain (i.e., it must accept 
 *    any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 *    shown in class).
 * 5. HashTable should be able to grow at least up to 200,000 elements. 
 * 6. We suggest you hard code some prime numbers. You can use this
 *    list: http://primes.utm.edu/lists/small/100000.txt 
 *    NOTE: Do NOT copy the whole list!
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private Supplier<Dictionary<K, V>> newChain;  
    private Dictionary<K, V>[] table;
    private int primeIndex;
    private final static int[] PRIMES = {101, 211, 409, 821, 1657, 3299, 6599, 12163, 24391, 48821, 97073, 202309};

    @SuppressWarnings("unchecked")
    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        primeIndex = 0;
        table = new Dictionary[PRIMES[primeIndex]];
    }

    @Override
    public V insert(K key, V value) {
        int index = key.hashCode() % table.length;
        if (table[index] == null) {
            table[index] = newChain.get();
        }
        V oldValue = table[index].find(key);
        table[index].insert(key, value);
        if (size >= table.length) {   
            rehash();
        }
        return oldValue;
    }

    @Override
    public V find(K key) {
        int index = key.hashCode() % table.length;
        if (table[index] == null) {
            return null;
        }
        return table[index].find(key);
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        Set<Item<K, V>> newSet = new HashSet<Item<K, V>>();
        throw new NotYetImplementedException();
    }
    
    private class ChainingHashTableIterator() {
        
    }
    private void rehash() {
        
    }
}
