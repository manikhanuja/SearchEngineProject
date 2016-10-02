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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import static searchengine.Soundex.soundex;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *
 * @author manikhanuja
 */
public class QueryLanguage {

    public static void readQueryFromUser(NaiveInvertedIndex index, List docId) {

        System.out.println("Main Menu");
        System.out.println(":index - for indexing and querying");
        System.out.println(":stem - for normalizing and then stemming the token");
        System.out.println(":vocab - for printing the vocabulary");
        System.out.println(":soundex - for calling soundex algorithm");
        System.out.println(":q - to quit the application");
        String query = readQueryFromUser();
        // implementing special queries
        // :q to exit
        if (query.equalsIgnoreCase(":q")) {
            System.out.println("Bye!");
            System.exit(0);
        }
        // :stem token take the token string, stem it and then print the stemmed term
        if (query.equalsIgnoreCase(":stem")) {
            callStemmer(index,docId);
        }

        //vocab - print all the terms in the vocabulary of the corpus, one term per line. Then print the count of the total number of vocabulary terms
        if (query.equalsIgnoreCase(":vocab")) {
            getVocabulary(index);
        }
        //Pending
        // :index directoryname - index the folder specified by directoryname and then begins querying it

        if (query.equalsIgnoreCase(":index")) {
            int counter = 0;
            System.out.println("Enter Query or :q to return to Main Menu: ");
            while (counter == 0) {
              String query1 = readQueryFromUser();
                //queryParser(index, query1,docId);
                notWordQuery(index,query1,docId);
                
            if (query1.equalsIgnoreCase(":q")) {
            System.out.println("Exit index, return to the main menu!");
            readQueryFromUser(index,docId);
        }
            }
        }
        if (query.equalsIgnoreCase(":soundex")) {
            String code1 = soundex("Mani");
            String code2 = soundex("Money");
            System.out.println(code1 + ": " + "Mani");
            System.out.println(code2 + ": " + "Money");
        }
        readQueryFromUser(index,docId);
    }

    public static void queryParser(NaiveInvertedIndex index, String query, List docId) {
        List<String> phraseList = new ArrayList<>();
        String pharseIdentifier = "\"";
// Prepare a final outPut List.
        String input = query;
        input = input.toLowerCase();
        int lastPhraseIndex = input.lastIndexOf(pharseIdentifier);
        int firstPhraseIndex = input.indexOf(pharseIdentifier);
        String remainderString = null;
        if (lastPhraseIndex > -1) {
            String strictPhrase = input.substring(firstPhraseIndex+1, lastPhraseIndex);
//list.add output of function search passing strictPhrase.
            Set<String> phraseSet;
            
            phraseSet = phraseWordQuery(index, strictPhrase);
            phraseList = new ArrayList<>(phraseSet);
            remainderString = input.substring(lastPhraseIndex + 1, input.length());

        } else {
            remainderString = input;
        }

        StringTokenizer orTokenizer = new StringTokenizer(remainderString, "+");
        List<List<String>> resultList = new ArrayList<>();
        while (orTokenizer.hasMoreTokens()) {
            String andTokens = orTokenizer.nextToken();
            StringTokenizer andTokensizer = new StringTokenizer(andTokens, " ");
            Set<String> andTokensResultSet = new TreeSet<>();
            List<String> andTokensResults = new ArrayList<>();
            
            while (andTokensizer.hasMoreTokens()) {
               if(andTokensResultSet.isEmpty()){
                andTokensResultSet = wordQuery(index, andTokensizer.nextToken());
                } else{
                   andTokensResultSet.retainAll(index.getDocumentId(andTokensizer.nextToken()));
                }
               if(!phraseList.isEmpty() && !andTokensResultSet.isEmpty()){
                andTokensResultSet.retainAll(phraseList);
                }
                /*if (andTokensizer.countTokens() >= 1) {
                    andTokensResultSet.retainAll(index.getDocumentId(andTokensizer.nextToken()));
                    
                }*/
                //System.out.println("Temp Doc for " + temp + tempDocSet);
                andTokensResults = new ArrayList<>(andTokensResultSet);
// do a function search passing andTokensizer.nextToken and store it in andTokensResults.
// do a intersection of  the results of other token with andTokensResults and store the same in andTokensResults.
            }
// store the results of all the andTokens into the result lsit.
            
            resultList.add(andTokensResults);
        }
        List<String> finalResultList = new ArrayList<>();
        Set<String> set = new HashSet<>();

// Now traverse the resultList and do the union of each list stored.
        for (List a : resultList) {
            set.addAll(a);
        }
        finalResultList = new ArrayList<>(set);

// finally add the result of strictPhrase into the same and return your result.
        if (finalResultList.isEmpty()) {
            set.addAll(phraseList);
        }
        finalResultList = new ArrayList<>(set);
        Iterator iterator = finalResultList.iterator();
        while (iterator.hasNext()) {
            System.out.println("Query Parser Index: " + iterator.next());
        }
    }
    
    public static String readQueryFromUser() {
        Scanner s = new Scanner(System.in);
        String query = s.nextLine();
        return query;
    }

    public static Set wordQuery(NaiveInvertedIndex index, String word) {
        System.out.println("I am General Query");
        String token[] = index.getDictionary();
        Set<Integer> tempDocSet = new HashSet<>();
        word = word.toLowerCase();
        int y = Arrays.binarySearch(token, word); 
        if (y < 0) {
            System.out.println("Word does not present, enter query again or :q to quit ");
        } else {

            tempDocSet = index.getPostings(word).keySet();
        }
        return tempDocSet;
    }
    public static List notWordQuery(NaiveInvertedIndex index, String word,List docId) {
        System.out.println("I am a NOT Query");
            String[] words = word.split("[ ]");
            Set<Integer> tempDocSet = new HashSet<>();
            List<Integer> documentIds = new ArrayList<>();
            documentIds.addAll(docId);
            if(words.length>1)
                    {
                        tempDocSet = phraseWordQuery(index,word);
                    } else{
                tempDocSet = wordQuery(index,word);
            }
            documentIds.removeAll(tempDocSet);
        
        for(int a : documentIds)
        {
            System.out.println("Word is not present in: " + a);
        }
        return documentIds;
    }
    
    public static Set phraseWordQuery(NaiveInvertedIndex index, String query) {
        System.out.println("I am PHRASE Query");
        String token[] = index.getDictionary();
        HashMap<Integer, List<Integer>> tempPosSet1 = new HashMap<>();
        HashMap<Integer, List<Integer>> tempPosSet2;
        String[] word = query.split("[ ]");
        for (String temp : word) {
            temp = temp.toLowerCase();
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else if (tempPosSet1.isEmpty()) {
                tempPosSet1 = index.getPostings(temp);
                //System.out.println("Temp Doc set with Postings1: " + tempPosSet1);
            } else {
                tempPosSet2 = index.getPostings(temp);
                //System.out.println("Temp Doc set with Postings1: " + tempPosSet1);
                //System.out.println("Temp Doc set with Postings2: " + tempPosSet2);
                HashMap<Integer, List<Integer>> tempPosSet3 = new HashMap<>();
                for (Integer k : tempPosSet1.keySet()) {
                    ArrayList<Integer> setVal = new ArrayList<>();
                    List<Integer> values = tempPosSet1.get(k);
                    if (tempPosSet2.containsKey(k)) {
                        for (int i = 0; i < values.size(); i++) {
                            if (tempPosSet2.get(k).contains(values.get(i) + 1)) {
                                setVal.add(values.get(i) + 1);
                                //System.out.println("print value: " + k + ": [" + setVal + "]");
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
        System.out.println("Phrase Query Index: " + tempPosSet1);
        //phraseWordQuery(index);
        return tempPosSet1.keySet();
    }

    public static void callStemmer(NaiveInvertedIndex index,List docId) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter token");
        while (scan.hasNext()) {
            String token = scan.next();
            String processToken;
            if (token.equalsIgnoreCase(":q")) {
                System.out.println("Exit Stemmer");
                readQueryFromUser(index,docId);
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

    public static void getVocabulary(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        System.out.println("Vocabulary! ");
        for (String vocabTerm : token) {
            System.out.println(vocabTerm);
        }
        System.out.println("Count of total vocabulary terms: " + index.getTermCount());
        System.out.println("Exit vocabulary and return to Main Menu!");
    }
}
