/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author manikhanuja
 */
public class QueryLanguage {

    public static String[] readQueryFromUser() {
        System.out.println("Enter a term to search for: ");
        Scanner s = new Scanner(System.in);
        String word = s.nextLine();
        //ArrayList<String> words = new ArrayList<>();
        String[] words;
        //term.add(Arrays.toString(token.split("[-]")));
        words = word.split("[ ]");
        for (String a : words) {
            System.out.println("Print input: " + a);
        }
        if (word.equalsIgnoreCase("q")) {
            System.out.println("Bye!");
            System.exit(0);
        }
        return words;
    }

    public static void freeWordQuery(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        String[] word = readQueryFromUser();
        Set<Integer> tempDocSet = new HashSet<>();
        for (String temp : word) {
            temp = temp.toLowerCase();
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else {

                tempDocSet.addAll(index.getDocumentId(temp));
            }
        }
        System.out.println("New Index: " + tempDocSet);
        freeWordQuery(index);
    }

    public static void phraseWordQuery(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        String[] word = readQueryFromUser();
        HashMap<Integer, List<Integer>> tempPosSet1 = new HashMap<>();
        HashMap<Integer, List<Integer>> tempPosSet2 = new HashMap<>();
        
        for (String temp : word) {
            temp = temp.toLowerCase();
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else if (tempPosSet1.isEmpty()) {
                tempPosSet1 = index.getPostings(temp);
                System.out.println("Temp Doc set with Postings1: " + tempPosSet1);
            } else {
                tempPosSet2 = index.getPostings(temp);
                System.out.println("Temp Doc set with Postings1: " + tempPosSet1);
                System.out.println("Temp Doc set with Postings2: " + tempPosSet2);
                HashMap<Integer, List<Integer>> tempPosSet3 = new HashMap<>();
                for (Integer k : tempPosSet1.keySet()) {
                    ArrayList<Integer> setVal = new ArrayList<>();
                    List<Integer> values = tempPosSet1.get(k);
                    if (tempPosSet2.containsKey(k)) {
                        for (int i = 0; i < values.size(); i++) {
                            if (tempPosSet2.get(k).contains(values.get(i) + 1)) {
                                setVal.add(values.get(i) + 1);
                                System.out.println("print value: " + k + ": [" + setVal + "]");
                            }
                            if (!setVal.isEmpty()) {
                            tempPosSet3.put(k, setVal);
                        }
                        
                        }

                    }

                }
                tempPosSet1 = tempPosSet3;
            }
        }
        System.out.println("New Index: " + tempPosSet1);
        phraseWordQuery(index);
    }
}
