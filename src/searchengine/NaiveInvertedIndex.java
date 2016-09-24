package searchengine;

import java.util.*;

public class NaiveInvertedIndex {
    //private HashMap<String, List<Integer>> mIndex;

    private final HashMap<String, HashMap<String, List<Integer>>> mIndex;

    public NaiveInvertedIndex() {
        mIndex = new HashMap<>();
    }

    public void addTerm(String term, String documentID, int pos) {
        // TO-DO: add the term to the index hashtable. If the table does not have
        // an entry for the term, initialize a new ArrayList<Integer>, add the 
        // docID to the list, and put it into the map. Otherwise add the docID
        // to the list that already exists in the map, but ONLY IF the list does
        // not already contain the docID.
        if (mIndex.get(term) == null) {
            HashMap<String, List<Integer>> docMap = new HashMap<>();
            ArrayList<Integer> termPos = new ArrayList<>();
            termPos.add(pos);
            //docList.add(documentID);
            docMap.put(documentID, termPos);
            mIndex.put(term, docMap);
        } else if (!mIndex.get(term).containsKey(documentID)) {
            ArrayList<Integer> termPos = new ArrayList<>();
            termPos.add(pos);

            (mIndex.get(term)).put(documentID, termPos);
        } else if (!mIndex.get(term).get(documentID).equals(pos)) {
            (mIndex.get(term)).get(documentID).add(pos);
        }

    }

    public HashMap<String, List<Integer>> getPostings(String term) {
        // TO-DO: return the postings list for the given term from the index map.
        return mIndex.get(term);
    }
    
    public Set<String> getDocumentId(String term) {
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
        Map<String, HashMap<String, List<Integer>>> treeMap = new TreeMap<String, HashMap<String, List<Integer>>>(mIndex);
        String temp[] = new String[mIndex.size()];
        int i = 0;
        for (String temp1 : treeMap.keySet()) {
            temp[i] = temp1;
            i++;
        }
        return temp;
    }
}
