package searchengine;

import java.util.*;

public class NaiveInvertedIndex {
    //private HashMap<String, List<Integer>> mIndex;

    private HashMap<String, HashMap<Integer, List<Integer>>> mIndex;

    public NaiveInvertedIndex() {
        mIndex = new HashMap<String, HashMap<Integer, List<Integer>>>();
    }

    public void addTerm(String term, Integer documentID, int pos) {
        // TO-DO: add the term to the index hashtable. If the table does not have
        // an entry for the term, initialize a new ArrayList<Integer>, add the 
        // docID to the list, and put it into the map. Otherwise add the docID
        // to the list that already exists in the map, but ONLY IF the list does
        // not already contain the docID.
        if (mIndex.get(term) == null) {
            HashMap<Integer, List<Integer>> docMap = new HashMap<Integer, List<Integer>>();
            ArrayList<Integer> termPos = new ArrayList<Integer>();
            termPos.add(pos);
            //docList.add(documentID);
            docMap.put(documentID, termPos);
            mIndex.put(term, docMap);
        } else if (!mIndex.get(term).containsKey(documentID)) {
            ArrayList<Integer> termPos = new ArrayList<Integer>();
            termPos.add(pos);

            (mIndex.get(term)).put(documentID, termPos);
        } else if (!mIndex.get(term).get(documentID).equals(pos)) {
            (mIndex.get(term)).get(documentID).add(pos);
        }
    }

    public HashMap<Integer, List<Integer>> getPostings(String term) {
        // TO-DO: return the postings list for the given term from the index map.
        return mIndex.get(term);
    }

    public Set<Integer> getDocumentId(String term) {
        // TO-DO: return the postings list for the given term from the index map.
        return mIndex.get(term).keySet();
    }
   
    public int getTermCount() {
        // TO-DO: return the number of terms in the index.

        return mIndex.size();
    }

    public String[] getDictionary() {
        // TO-DO: fill an array of Strings with all the keys from the hashtable.
        // Sort the array and return it.
        Map<String, HashMap<Integer, List<Integer>>> treeMap = new TreeMap<String, HashMap<Integer, List<Integer>>>(mIndex);
        String temp[] = new String[mIndex.size()];
        int i = 0;
        for (String temp1 : treeMap.keySet()) {
            temp[i] = temp1;
            i++;
        }
        return temp;
    }
}
