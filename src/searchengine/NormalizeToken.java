/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

/**
 *
 * @author manikhanuja
 */
public class NormalizeToken {
    public static String normalizeToken(String token) {
        //convert the token to lower case
        //String[] term = new String[]{};
        token = token.toLowerCase();
        // remove all non-alphanumeric characters from begining
        token = token.replaceAll("^[^a-zA-Z0-9\\s]*", "");
        System.out.println("Matched Non alphanumeric at begining: " + token);
        // remove all non-alphanumeric characters from end
        token = token.replaceAll("[^a-zA-Z0-9\\s]*$", "");
        System.out.println("Matched Non alphanumeric at end: " + token);
        // remove all apostropes (single quotes)
        token = token.replaceAll("[']", "");
        System.out.println("Remove all single quotes: " + token);

        if (token.contains("-")) {
            String[] term = token.split("[-]");
            for (String s : term) {

                System.out.println("print tokens: " + s);
            }
            token = token.replaceAll("[-]", "");
        }
        return token;
    }
}
