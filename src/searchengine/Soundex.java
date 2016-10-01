/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

/**
 *
 * @author manikhanuja
 *
 */
public class Soundex {

    public static String soundex(String sname) {
        char[] coder = sname.toUpperCase().toCharArray();
        char firstCharacter = coder[0];

        /* conversion of letters in name to alpha-numeric code
        only consonants in the name are converted to the code, so that names with similar
        sound can have the same code. for eg: Mani or Money will return the same code.
        */
        for (int i = 0; i < coder.length; i++) {
            switch (coder[i]) {
                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    coder[i] = '1';
                    break;
                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    coder[i] = '2';
                    break;
                case 'D':
                case 'T':
                    coder[i] = '3';
                    break;
                case 'L':
                    coder[i] = '4';
                    break;
                case 'M':
                case 'N':
                    coder[i] = '5';
                    break;
                case 'R':
                    coder[i] = '6';
                    break;
                default:
                    coder[i] = '0';
                    break;
            }
        }
        // remove any dulipcate items
        String output = "" + firstCharacter;
        for (int i = 1; i < coder.length; i++) {
            if (coder[i] != coder[i - 1] && coder[i] != '0') {
                output += coder[i];
            }
        }
        // padding with 0's or truncate
        output = output + "0000";
        return output.substring(0, 4);
    }
}
