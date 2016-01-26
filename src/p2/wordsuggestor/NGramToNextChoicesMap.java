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
        if (this.map.find(ngram) == null) {
            this.map.insert(ngram, newInner.get());
        }
        if (this.map.find(ngram).find(new AlphabeticString(word)) ==  null) {
            this.map.find(ngram).insert(new AlphabeticString(word), 0);
        }
        int count = this.map.find(ngram).find(new AlphabeticString(word));
        this.map.find(ngram).insert(new AlphabeticString(word), count + 1);
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
        if (this.map.find(ngram) != null) {
            System.out.println(this.map.find(ngram).size());
            Item<String, Integer>[] itArr = (Item<String, Integer>[])new Item[this.map.find(ngram).size()];
            int index = 0;
            for (Item<AlphabeticString, Integer> it : this.map.find(ngram)) {
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
            InsertionSort.sort(afterNGrams, comp);
        }
        else {
            // You must fix this line toward the end of the project
            throw new NotYetImplementedException();
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
