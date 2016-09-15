package porterstemmer;

import java.util.regex.*;

public class PorterStemmer {

    // a single consonant
    private static final String CONSONANT_INTERIM = "[^aeiou]";
    // a single VOWEL_AFTER_CONSONANT
    private static final String VOWEL_INTERIM = "[aeiouy]";

    // a sequence of consonants; the second/third/etc consonant cannot be 'y'
    private static final String CONSONANT = CONSONANT_INTERIM + "[^aeiouy]*";
    // a sequence of vowels; the second/third/etc cannot be 'y'
    private static final String VOWEL = VOWEL_INTERIM + "[aeiou]*";

    // this regex pattern tests if the token has measure > 0 [at least one VC].
    private static final Pattern MEASURE_GR0 = Pattern.compile("^(" + CONSONANT + ")?" + VOWEL + CONSONANT);

    // add more Pattern variables for the following patterns:
    // m equals 1: token has measure == 1
    private static final Pattern MEASURE_EQ1 = Pattern.compile("^(" + CONSONANT + ")?" + VOWEL + CONSONANT + "$");
// m greater than 1: token has measure > 1
    private static final Pattern MEASURE_GR1 = Pattern.compile("^(" + CONSONANT + ")?" + VOWEL + CONSONANT + VOWEL + CONSONANT);
    // VOWEL_AFTER_CONSONANT: token has a VOWEL_AFTER_CONSONANT after the first (optional) CONSONANT
    private static final Pattern VOWEL_AFTER_CONSONANT = Pattern.compile("^(" + CONSONANT + ")?" + VOWEL);

    // double consonant: token ends in two consonants that are the same, 
    //			unless they are L, S, or Z. (look up "backreferencing" to help 
    //			with this)
    private static final Pattern DOUBLE_CONSONANT = Pattern.compile("([^aeiouylsz])\\1" + "$");
    // m equals 1, cvc: token is in Cvc form, where the last CONSONANT_INTERIM is not w, x,
    //			or y.
    private static final Pattern MEASURE_EQ1_NOT_WXY = Pattern.compile("^(" + CONSONANT + ")?" + VOWEL + "([^aeiouwxy]|" + "(" + CONSONANT + "[^aeiouyxy]))" + "$");

    private static enum stepName {
        stepTwo, stepThree, stepFour
    };
    private static final String[][] STEP_TWO_PAIRS = {
        new String[]{"ational", "ate"},
        new String[]{"tional", "tion"},
        new String[]{"enci", "ence"},
        new String[]{"anci", "ance"},
        new String[]{"izer", "ize"},
        new String[]{"bli", "ble"},
        new String[]{"alli", "al"},
        new String[]{"entli", "ent"},
        new String[]{"eli", "e"},
        new String[]{"ousli", "ous"},
        new String[]{"ization", "ize"},
        new String[]{"ation", "ate"},
        new String[]{"ator", "ate"},
        new String[]{"alism", "al"},
        new String[]{"iveness", "ive"},
        new String[]{"fulness", "ful"},
        new String[]{"ousness", "ous"},
        new String[]{"aliti", "al"},
        new String[]{"iviti", "ive"},
        new String[]{"biliti", "ble"}
    };
    private static final String[][] STEP_THREE_PAIRS = {
        new String[]{"icate", "ic"},
        new String[]{"ative", ""},
        new String[]{"alize", "al"},
        new String[]{"iciti", "ic"},
        new String[]{"ical", "ic"},
        new String[]{"ful", ""},
        new String[]{"ness", ""}
    };
    private static final String[][] STEP_FOUR_PAIRS = {
        new String[]{"al", ""},
        new String[]{"ance", ""},
        new String[]{"ence", ""},
        new String[]{"er", ""},
        new String[]{"ic", ""},
        new String[]{"able", ""},
        new String[]{"ible", ""},
        new String[]{"ant", ""},
        new String[]{"ement", ""},
        new String[]{"ment", ""},
        new String[]{"ent", ""},
        new String[]{"sion", "s"},
        new String[]{"tion", "t"},
        new String[]{"ou", ""},
        new String[]{"ism", ""},
        new String[]{"ate", ""},
        new String[]{"iti", ""},
        new String[]{"ous", ""},
        new String[]{"ive", ""},
        new String[]{"ize", ""}
    };

    public static String processToken(String token) {
        if (token.length() < 3) {
            return token; // token must be at least 3 chars
        }
        // step 1a.1
        if (token.endsWith("sses")) {
            token = token.substring(0, token.length() - 2);
        }
        // step 1a.2
        if (token.endsWith("ies")) {
            token = token.substring(0, token.length() - 2);
        }
        // step 1a.3 - token ends with s and not ss
        if (token.endsWith("s")) {
            if (token.endsWith("ss")) {
            } else {
                token = token.substring(0, token.length() - 1);
            }
        }

        // step 1b
        boolean doStep1bb = false;
        //		step 1b
        if (token.endsWith("eed")) { // 1b.1
            // token.substring(0, token.length() - 3) is the stem prior to "eed".
            // if that has m>0, then remove the "d".
            String stem = token.substring(0, token.length() - 3);
            if (MEASURE_GR0.matcher(stem).find()) { // if the pattern matches the stem
                token = stem + "ee";
            }
        }
        // program the rest of 1b. set the boolean doStep1bb to true if Step 1b* 
        // should be performed.
        if (token.endsWith("ed")) {
            if (token.endsWith("eed")) {
            } else {
                String stem = token.substring(0, token.length() - 2);
                if (VOWEL_AFTER_CONSONANT.matcher(stem).find()) {
                    token = stem;
                    doStep1bb = true;
                }
            }
        }
        if (token.endsWith("ing")) {
            String stem = token.substring(0, token.length() - 3);
            if (VOWEL_AFTER_CONSONANT.matcher(stem).find()) {
                token = stem;
                doStep1bb = true;
            }
        }
        // step 1b*, only if the 1b.2 or 1b.3 were performed.
        if (doStep1bb) {
            if (token.endsWith("at") || token.endsWith("bl")
                    || token.endsWith("iz")) {

                token = token + "e";
            }
            // Pending: use the regex patterns you wrote for 1b*.4 and 1b*.5
        }

        // step 1c
        // program this step. test the suffix of 'y' first, then test the 
        // condition *VOWEL_INTERIM* on the stem.
        if (token.endsWith("y")) {
            String stem = token.substring(0, token.length() - 1);
            if (VOWEL_AFTER_CONSONANT.matcher(stem).find()) {
                token = stem + "i";
            }
        }

        // step 2
        // program this step. for each suffix, see if the token ends in the 
        // suffix. 
        //    * if it does, extract the stem, and do NOT test any other suffix.
        //    * take the stem and make sure it has m > 0.
        //        * if it does, complete the step and do not test any others.
        //          if it does not, attempt the next suffix.
        // you may want to write a helper method for this. a matrix of
        // "suffix"/"replacement" pairs might be helpful. It could look like
        // string[][] STEP_TWO_PAIRS = {  new string[] {"ational", "ate"}, 
        //										new string[] {"tional", "tion"}, ....
        //String stepName = "stepTwo";
        String stepTworesult = helperMatrixStep(token, STEP_TWO_PAIRS, stepName.stepTwo);
        if (!stepTworesult.isEmpty()) {
            System.out.println("step2 result: " + stepTworesult);
            token = stepTworesult;
        }

        // step 3
        // program this step. the rules are identical to step 2 and you can use
        // the same helper method. you may also want a matrix here.
        String stepThreeResult = helperMatrixStep(token, STEP_THREE_PAIRS, stepName.stepThree);
        if (!stepThreeResult.isEmpty()) {
            token = stepThreeResult;
        }
        // step 4
        // program this step similar to step 2/3, except now the stem must have
        // measure > 1.
        // note that ION should only be removed if the suffix is SION or TION, 
        // which would leave the S or T.
        // as before, if one suffix matches, do not try any others even if the 
        // stem does not have measure > 1.

        String step4result = helperMatrixStep(token, STEP_FOUR_PAIRS, stepName.stepFour);
        if (!step4result.isEmpty()) {
            token = step4result;
        }
        // step 5
        // program this step. you have a regex for m=1 and for "Cvc", which
        // you can use to see if m=1 and NOT Cvc.
        // all your code should change the variable token, which represents
        // the stemmed term for the token.
        return token;
    }

    public static String helperMatrixStep(String token, String[][] steppairs, stepName step) {
        String result = "";
        for (String[] steppair : steppairs) {
            if (token.endsWith(steppair[0])) {
                String stem = token.substring(0, token.length() - steppair[0].length());
                if (step == stepName.stepTwo | step == stepName.stepThree) {
                    if (MEASURE_GR0.matcher(stem).find()) {
                        result = stem + steppair[1];
                        break;
                    }
                }
                if (step == stepName.stepFour) {
                    if (MEASURE_GR1.matcher(stem).find()) {
                        result = stem + steppair[1];
                        break;
                    }
                }
            }
        }
        return result;
    }

}