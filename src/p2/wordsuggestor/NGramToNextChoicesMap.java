package p2.wordsuggestor;

import java.util.Comparator;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.Dictionary;
import cse332.misc.LargeValueFirstItemComparator;
import cse332.sorts.InsertionSort;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import p2.sorts.QuickSort;
import p2.sorts.TopKSort;

public class NGramToNextChoicesMap {
    private final Dictionary<NGram, Dictionary<AlphabeticString, Integer>> map;
    private final Supplier<Dictionary<AlphabeticString, Integer>> newInner;

    public NGramToNextChoicesMap(
            Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> newOuter,
            Supplier<Dictionary<AlphabeticString, Integer>> newInner) {
        this.map = newOuter.get();
        this.newInner = newInner;
    }

    /**
     * Increments the count of word after the particular NGram ngram.
     */
    public void seenWordAfterNGram(NGram ngram, String word) {
        Dictionary<AlphabeticString, Integer> dict = this.map.find(ngram);
        if (dict == null) {
            this.map.insert(ngram, dict = newInner.get());
        }
        if (dict.find(new AlphabeticString(word)) ==  null) {
            dict.insert(new AlphabeticString(word), 0);
        }
        int count = dict.find(new AlphabeticString(word));
        dict.insert(new AlphabeticString(word), count + 1);
    }

    /**
     * Returns an array of the DataCounts for this particular ngram. Order is
     * not specified.
     *
     * @param ngram
     *            the ngram we want the counts for
     * 
     * @return An array of all the Items for the requested ngram.
     */
    @SuppressWarnings("unchecked")
    public Item<String, Integer>[] getCountsAfter(NGram ngram) {
        Dictionary<AlphabeticString, Integer> dict = this.map.find(ngram);
        if (dict != null) {
            System.out.println(dict.size());
            Item<String, Integer>[] itArr = (Item<String, Integer>[])new Item[dict.size()];
            int index = 0;
            for (Item<AlphabeticString, Integer> it : dict) {
                String newKey = it.key.toString();
                Item<String, Integer> newIt = new Item<String, Integer>(newKey, it.value);
                itArr[index] = newIt;
                index++;
            }
            return itArr;
        }
        return (Item<String, Integer>[])(new Item[0]);
    }

    public String[] getWordsAfter(NGram ngram, int k) {
        Item<String, Integer>[] afterNGrams = getCountsAfter(ngram);

        Comparator<Item<String, Integer>> comp = new LargeValueFirstItemComparator<String, Integer>();
        if (k < 0) {
            QuickSort.sort(afterNGrams, comp);
        }
        else {
            TopKSort.sort(afterNGrams, k, comp);
        }

        String[] nextWords = new String[k < 0 ? afterNGrams.length : k];
        for (int l = 0; l < afterNGrams.length && l < nextWords.length
                && afterNGrams[l] != null; l++) {
            nextWords[l] = afterNGrams[l].key;
        }
        return nextWords;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
