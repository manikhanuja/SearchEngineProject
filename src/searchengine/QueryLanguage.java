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
import java.util.regex.Pattern;

/**
 *
 * @author manikhanuja
 */
public class QueryLanguage {

    public static void readQueryFromUser(NaiveInvertedIndex index) {

        System.out.println("Main Menu");
       /* System.out.println(":index - for indexing and querying");
        System.out.println(":stem - for normalizing and then stemming the token");
        System.out.println(":vocab - for printing the vocabulary");
        System.out.println(":q - to quit the application");*/
        String query = readQueryFromUser();
        // implementing special queries
        // :q to exit
        if (query.equalsIgnoreCase(":q")) {
            System.out.println("Bye!");
            System.exit(0);
        }
        // :stem token take the token string, stem it and then print the stemmed term
        if (query.equalsIgnoreCase(":stem")) {
            callStemmer();
        }
        //Pending
        // :index directoryname - index the folder specified by directoryname and then begins querying it
        
        //vocab - print all the terms in the vocabulary of the corpus, one term per line. Then print the count of the total number of vocabulary terms
        if (query.equalsIgnoreCase(":vocab")) {
            getVocabulary(index);
            System.exit(0);
        }
        
        if(query.equalsIgnoreCase(":index")){
            //if(query.contains(" ")){
                AndWordQuery(index);
            
        }
    }
    
public static String readQueryFromUser() {
        System.out.println("Enter User Query: ");
        Scanner s = new Scanner(System.in);
        String query = s.nextLine();
        return query;
    }

    public static void freeWordQuery(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        String query = readQueryFromUser();
        String [] word = query.split(Pattern.quote(" "));
       // String [] word = strSplit(query, "\\+");
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

    public static void AndWordQuery(NaiveInvertedIndex index) {
        System.out.println("I am AND Query");
        String token[] = index.getDictionary();
        String query = readQueryFromUser();
        String [] word = query.split("[ ]");
        Set<Integer> tempDocSet = new HashSet<>();
        for (String temp : word) {
            temp = temp.toLowerCase();
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else if (tempDocSet.isEmpty()) {
                tempDocSet = index.getDocumentId(temp);
                System.out.println("Temp Doc set with Postings1: " + tempDocSet);
            } else {
                tempDocSet.retainAll(index.getDocumentId(temp));
            }
        }
        System.out.println("New Index: " + tempDocSet);
        AndWordQuery(index);
    }

    /*public static void phraseWordQuery(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        String[] word = readQueryFromUser();
        HashMap<Integer, List<Integer>> tempPosSet1 = new HashMap<>();
        HashMap<Integer, List<Integer>> tempPosSet2;

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
    } */
    public static void callStemmer()
    {
        Scanner scan = new Scanner(System.in);
            System.out.println("Enter token");
            while (scan.hasNext()) {
                String token = scan.next();
                String processToken;
                if (token.equalsIgnoreCase(":q")) {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                ArrayList<String> normalizeToken;
                normalizeToken = NormalizeToken.normalizeToken(token);
                System.out.println("Normalized Token: " + normalizeToken);
                for (String tkn : normalizeToken) {
                    System.out.println("we are on " + tkn);
                    processToken = PorterStemmer.processToken(tkn);
                    System.out.println("stem: " + processToken);
                }
            }
    }
    public static void getVocabulary(NaiveInvertedIndex index)
    {
        String token[] = index.getDictionary();
            System.out.println("Vocabulary! ");
            for (String vocabTerm : token) {
                System.out.println(vocabTerm);
            }
            System.out.println("Count of total vocabulary terms: " + index.getTermCount());
    }
}
