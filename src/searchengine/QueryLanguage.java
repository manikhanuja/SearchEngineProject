/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.util.Arrays;
import java.util.HashSet;
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
        Set<String> tempSet = new HashSet<>();
        for (String temp : word) {
            temp = temp.toLowerCase();
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else {

                  tempSet.addAll(index.getDocumentId(temp));
            }
        }
        System.out.println("New Index: " + tempSet);
        freeWordQuery(index);
    }

  /* public static void phraseWordQuery(NaiveInvertedIndex index) {
        String token[] = index.getDictionary();
        String[] word = readQueryFromUser();
        Set<String> tempSet = new HashSet<>();
        for (String temp : word) {
            int y = Arrays.binarySearch(token, temp);
            if (y < 0) {
                System.out.println("Word does not present ");
                System.exit(0);
            } else {
                tempSet.addAll(index.getPostings(temp));
            }
        }
        System.out.println("New Index: " + tempSet);
        phraseWordQuery(index);
    }*/
}
